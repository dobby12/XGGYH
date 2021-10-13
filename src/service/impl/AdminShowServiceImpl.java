package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.AdminShowDao;
import dao.face.FileDao;
import dao.impl.AdminShowDaoImpl;
import dao.impl.FileDaoImpl;
import dto.XFile;
import dto.XShow;
import service.face.AdminShowService;
import util.Paging;

public class AdminShowServiceImpl implements AdminShowService {

	AdminShowDao adminShowDao = new AdminShowDaoImpl();
	FileDao fileDao = new FileDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		String param = req.getParameter("curPage");
		
		int curPage = 0;
		
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null입니다.");
		}
		
		int totalCount = adminShowDao.selectCntAll(JDBCTemplate.getConnection());
		
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public List<XShow> getShowList(Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		List<XShow> list = adminShowDao.selectShowAll(conn, paging);
		
		return list;
	}
	
	@Override
	public XShow getShowno(HttpServletRequest req) {
		
		XShow showno = new XShow();
		
		String param = req.getParameter("showno");
		if(param != null && !"".equals(param)) {

			showno.setShowNo((Integer.parseInt(param)));
		}
		
		return showno;
	}
	
	@Override
	public XShow getShowDetail(XShow viewShow) {
		
		return adminShowDao.selectShowByShowno(JDBCTemplate.getConnection(), viewShow);
	}
	
	@Override
	public String getKind(XShow viewShow) {
		
		return adminShowDao.selectKindByKindno(JDBCTemplate.getConnection(), viewShow);
	}

	@Override
	public String getGenre(XShow viewShow) {

		return adminShowDao.selectGenrebyGenreno(JDBCTemplate.getConnection(), viewShow);
	}
	
	@Override
	public String getHallname(XShow viewShow) {

		return adminShowDao.selectHallnameByHallno(JDBCTemplate.getConnection(), viewShow);
	}

	@Override
	public XFile getFile(XShow viewShow) {
	
		return adminShowDao.selectFile(JDBCTemplate.getConnection(), viewShow);
	}
	
	@Override
	public void setShowDelete(XShow showno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if(adminShowDao.deleteShowFile(conn, showno) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if(adminShowDao.deleteShow(conn, showno) > 0 ) {
			JDBCTemplate.commit(conn);
		} else { 
			JDBCTemplate.rollback(conn);
		}
		
	}

	@Override
	public void setShow(HttpServletRequest req) {
		
		 XShow xshow = null;
		 XFile file = null;
		 
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		if(!isMultipart) {
			System.out.println("!!!ERROR!!! AdminNoticeWriteController에 Post방식으로 전달된 parameter가 multipart/form-data형식이 아닙니다.");
			return;
		}
		xshow = new XShow();
		//--------------------------------------------------

		//디스크 기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(10 * 1024 * 1024); //10MB
	
		//임시 저장소 설정
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir();	//임시 저장소 폴더 생성
		factory.setRepository(repository);	//임시 저장소 폴더 지정
		
		//--------------------------------------------------

		//파일 업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//업로드 용량 제한
		upload.setFileSizeMax(10*1024*1024);

		//전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		Iterator<FileItem> iter = items.iterator();
		while(iter.hasNext()) {
			FileItem item = iter.next();
			
			//--- 1) 빈 파일에 대한 처리 ---
			if(item.getSize() <= 0) {
				continue; //빈 파일은 무시하고 다음 FileItem처리로 넘긴다
			}
			
			//--- 2) form-data에 대한 처리 ---
			if(item.isFormField()) {
				String key = item.getFieldName();
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch(UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
			     
			     
				if("showTitle".equals(key)) {
					xshow.setShowTitle(value);	
				} else if("hallNo".equals(key)) {
					xshow.setHallNo(Integer.parseInt(value));
				} else if("kindNo".equals(key)) {
					xshow.setKindNo(Integer.parseInt(value));
				} else if("genreNo".equals(key)) {
					xshow.setGenreNo(Integer.parseInt(value));
				} else if("showAge".equals(key)) {
					xshow.setShowAge(value);
				} else if("showDirector".equals(key)) {
					xshow.setShowDirector(value);
				} else if("showActor".equals(key)) {
					xshow.setShowActor(value);
				} else if("showContent".equals(key)) {
					xshow.setShowContent(value);
				} else if("startDate".equals(key)) {
					try {
						xshow.setShowStart( new SimpleDateFormat("yyyy-MM-dd").parse(value) );
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else if("endDate".equals(key)) {
					try {
						xshow.setShowEnd( new SimpleDateFormat("yyyy-MM-dd").parse(value) );
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
//				else if("startDate".equals(key)) {
//					xshow.setShowStart(date = java.sql.Date.valueOf(value));
//				} else if("endDate".equals(key)) {
//					xshow.setShowEnd(date = java.sql.Date.valueOf(value));;
//				}
			}

			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				//UUID 생성
				UUID uuid = UUID.randomUUID();
				String uid = uuid.toString().split("-")[0];
				
				//로컬 저장소의 업로드 폴더
				File upFolder = new File(req.getServletContext().getRealPath("upload"));
				upFolder.mkdir();
				
				//업로드 파일 객체
				String origin = item.getName();	//원본 파일명
				String stored = origin+"_"+uid;	//원본 파일명_uid
				File up = new File(upFolder, stored);
				
				try {
					item.write(up); //실제 업로드(임시파일을 최종결과파일로 생성함)
					item.delete(); //임시파일을 삭제
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보 저장
				file = new XFile();
				file.setFileOriginName(origin);
				file.setFileStoredName(stored);
				file.setFileSize(Long.toString(item.getSize()));
			}
			
		}
		
		Connection conn = JDBCTemplate.getConnection();

		//게시글 번호 생성
		int no = adminShowDao.selectNextShowno(conn);	
		
		if(file != null) {
			int fileno = fileDao.selectNextFileno(conn);
			file.setFileNo(fileno);
			xshow.setFileNo(fileno);
			if(fileDao.insertFile(conn, file) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//게시글 정보가 있을 경우 (있겠지만 혹시나)
		if(xshow!=null) {
			xshow.setAdminId((String)req.getSession().getAttribute("adminid"));	
			
			xshow.setShowNo(no);
			if(xshow.getShowTitle()==null ||" ".equals(xshow.getShowTitle())) {	//@@@각 게시판에 맞게 수정 필요
				xshow.setShowTitle("공연 제목 없음");
			}
			if(adminShowDao.insertShow(conn, xshow) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
				
		}
	}

	@Override
	public void setShowUpdate(HttpServletRequest req) {
		XShow xshow = null;	
		XFile file = null;
		
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		if(!isMultipart) {
			System.out.println("!!!ERROR!!! AdminShowUpdateController에 Post방식으로 전달된 parameter가 multipart/form-data형식이 아닙니다.");
			return;
		}
		xshow = new XShow();
		//--------------------------------------------------

		//디스크 기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(10 * 1024 * 1024); //10MB
	
		//임시 저장소 설정
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir();	//임시 저장소 폴더 생성
		factory.setRepository(repository);	//임시 저장소 폴더 지정
		
		//--------------------------------------------------

		//파일 업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//업로드 용량 제한
		upload.setFileSizeMax(10*1024*1024);

		//전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		Iterator<FileItem> iter = items.iterator();
		while(iter.hasNext()) {
			FileItem item = iter.next();
			
			//--- 1) 빈 파일에 대한 처리 ---
			if(item.getSize() <= 0) {
				continue; //빈 파일은 무시하고 다음 FileItem처리로 넘긴다
			}
			
			//--- 2) form-data에 대한 처리 ---
			if(item.isFormField()) {
				String key = item.getFieldName();
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch(UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				
				if("showTitle".equals(key)) {
					xshow.setShowTitle(value);	
				} else if("showNo".equals(key)) {
					xshow.setShowNo(Integer.parseInt(value));
				} else if("adminId".equals(key)) {
					xshow.setAdminId(value);
				} else if("hallNo".equals(key)) {
					xshow.setHallNo(Integer.parseInt(value));
				} else if("kindNo".equals(key)) {
					xshow.setKindNo(Integer.parseInt(value));
				} else if("genreNo".equals(key)) {
					xshow.setGenreNo(Integer.parseInt(value));
				} else if("showAge".equals(key)) {
					xshow.setShowAge(value);
				} else if("showDirector".equals(key)) {
					xshow.setShowDirector(value);
				} else if("showActor".equals(key)) {
					xshow.setShowActor(value);
				} else if("showContent".equals(key)) {
					xshow.setShowContent(value);
				} else if("startDate".equals(key)) {
					try {
						xshow.setShowStart( new SimpleDateFormat("yyyy-MM-dd").parse(value) );
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else if("endDate".equals(key)) {
					try {
						xshow.setShowEnd( new SimpleDateFormat("yyyy-MM-dd").parse(value) );
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				//UUID 생성
				UUID uuid = UUID.randomUUID();
				String uid = uuid.toString().split("-")[0];
				
				//로컬 저장소의 업로드 폴더
				File upFolder = new File(req.getServletContext().getRealPath("upload"));
				upFolder.mkdir();
				
				//업로드 파일 객체
				String origin = item.getName();	//원본 파일명
				String stored = origin+"_"+uid;	//원본 파일명_uid
				File up = new File(upFolder, stored);
				
				try {
					item.write(up); //실제 업로드(임시파일을 최종결과파일로 생성함)
					item.delete(); //임시파일을 삭제
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보 저장
				file = new XFile();
				file.setFileOriginName(origin);
				file.setFileStoredName(stored);
				file.setFileSize(Long.toString(item.getSize()));	//@@ DB를 varchar2로 만들었기 때문에 Long.toString() 사용함
				
			}
			
		}
		
		Connection conn = JDBCTemplate.getConnection();

		//첨부파일 정보가 있을 경우 (아래의 게시글 처리보다 위에 있어야만 합니다! PK, FK 관계 때문에)
		if(file != null) {
			int fileno = fileDao.selectNextFileno(conn);
			file.setFileNo(fileno);
			xshow.setFileNo(fileno);
			if(fileDao.insertFile(conn, file) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//게시글 정보가 있을 경우 (있겠지만 혹시나)
		if(xshow!=null) {
			
			if(xshow.getShowTitle()==null || "".equals(xshow.getShowTitle())){
				xshow.setShowTitle("(공연 제목 없음)");
			}
			if(adminShowDao.updateShow(conn,xshow) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
	}

}