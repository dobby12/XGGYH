package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.AdminNoticeDao;
import dao.face.FileDao;
import dao.impl.AdminNoticeDaoImpl;
import dao.impl.FileDaoImpl;
import dto.XFile;
import dto.XNotice;
import service.face.AdminNoticeService;

public class AdminNoticeServiceImpl implements AdminNoticeService {

	private AdminNoticeDao adminNoticeDao = new AdminNoticeDaoImpl();
	private FileDao fileDao = new FileDaoImpl();

	@Override
	public List<XNotice> getNoticeList() {
		return adminNoticeDao.selectNoticeAll(JDBCTemplate.getConnection());
	}

	@Override
	public XNotice getNoticeDetail(int noticeno) {
		return adminNoticeDao.selectNoticeByNoticeno(JDBCTemplate.getConnection(), noticeno);
	}

	@Override
	public XFile getFile(int noticeno) {
		return adminNoticeDao.selectFileByFileno(JDBCTemplate.getConnection(), noticeno);
	}

	@Override
	public void setNotice(HttpServletRequest req) {
		
		XNotice notice = null;	//@@@삽입하려는 게시판 객체로 바꾸기 ex) XReview review = null;
		XFile file = null;
		
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		if(!isMultipart) {
			System.out.println("!!!ERROR!!! AdminNoticeWriteController에 Post방식으로 전달된 parameter가 multipart/form-data형식이 아닙니다.");
			return;
		}
		notice = new XNotice();	//@@@위에 맞춰서 수정 ex) = new XReview();
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
				
				if("title".equals(key)) {			//@@@"title"로 전달받은 게 맞으면 수정할 필요 없음
					notice.setNoticeTitle(value);	//@@@ex) review.setReviewTitle(value);
				} else if("content".equals(key)) {	//@@@"content"로 전달받은 게 맞으면 수정할 필요 없음
					notice.setNoticeContent(value);	//@@@ex) review.setReviewContent(value);
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

		//게시글 번호 생성
		int no = adminNoticeDao.selectNextNoticeno(conn);	//@@@삽입하려는 게시판에 맞게 변수명과 dao메소드 생성
																//dao메소드는 DUAL테이블에서 게시판번호를 table_seq.nextval을 이용해서 만들어 오는 기능
																//모르겠으면 지태한테 문의 or AdminNoticeDaoImpl.java에 있는 selectNextNoticeno(conn) 확인

		//첨부파일 정보가 있을 경우 (아래의 게시글 처리보다 위에 있어야만 합니다! PK, FK 관계 때문에)
		if(file != null) {
			int fileno = fileDao.selectNextFileno(conn);
			file.setFileNo(fileno);
			notice.setFileNo(fileno);	//@@@삽입하려는 게시판에 맞게 ex) review.setFileNo(fileno);
			if(fileDao.insertFile(conn, file) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//게시글 정보가 있을 경우 (있겠지만 혹시나)
		if(notice!=null) {
			notice.setAdminId((String)req.getSession().getAttribute("adminid"));	//@@@현재 로그인되어 있는 adminid를 불러와서 이 게시판의 작성자에 입력시키는 코드
																					//사용자 입장일 경우 adminid 대신 memid를 입력하고 setMemId로 변경
			notice.setNoticeNo(no);	//@@@위에서 만든 게시글번호 no를 삽입 ex)review.setReviewNo(no);  
			if(notice.getNoticeTitle()==null||"".equals(notice.getNoticeTitle())) {	//@@@각 게시판에 맞게 수정 필요
				notice.setNoticeTitle("(제목 없음)");	//@@@각 게시판에 맞게
			}
			if(adminNoticeDao.insertNotice(conn, notice)>0) {	//@@@각 게시판에 맞게 dao 메소드 생성
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
				
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void setNoticeUpdate(HttpServletRequest req) {

		XNotice notice = null;	//@@@삽입하려는 게시판 객체로 바꾸기 ex) XReview review = null;
		XFile file = null;
		
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		if(!isMultipart) {
			System.out.println("!!!ERROR!!! AdminNoticeUpdateController에 Post방식으로 전달된 parameter가 multipart/form-data형식이 아닙니다.");
			return;
		}
		notice = new XNotice();	//@@@위에 맞춰서 수정 ex) = new XReview();
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
				
				if("title".equals(key)) {			//@@@"title"로 전달받은 게 맞으면 수정할 필요 없음
					notice.setNoticeTitle(value);	//@@@ex) review.setReviewTitle(value);
				} else if("content".equals(key)) {	//@@@"content"로 전달받은 게 맞으면 수정할 필요 없음
					notice.setNoticeContent(value);	//@@@ex) review.setReviewContent(value);
				} else if("no".equals(key)) {
					notice.setNoticeNo(Integer.parseInt(value));
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
			notice.setFileNo(fileno);	//@@@삽입하려는 게시판에 맞게 ex) review.setFileNo(fileno);
			if(fileDao.insertFile(conn, file) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//게시글 정보가 있을 경우 (있겠지만 혹시나)
		if(notice!=null) {
			notice.setNoticeNo(notice.getNoticeNo());
			if(notice.getNoticeTitle()==null||"".equals(notice.getNoticeTitle())) {	//@@@각 게시판에 맞게 수정 필요
				notice.setNoticeTitle("(제목 없음)");	//@@@각 게시판에 맞게
			}
			if(adminNoticeDao.updateNotice(conn, notice)>0) {	//@@@각 게시판에 맞게 dao 메소드 생성
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
				
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	@Override
	public void setNoticeDelete(int noticeno) {

		Connection conn = JDBCTemplate.getConnection();
		
		int fileno = getFile(noticeno).getFileNo();
		
		if(adminNoticeDao.deleteNotice(conn, noticeno) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if(fileDao.deleteFile(conn, fileno) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		
	}
	
}
