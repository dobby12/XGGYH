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
		
//		HttpSession session = req.getSession();
//		String title = req.getParameter("title");
//		String content = req.getParameter("content");
//		String id = (String)session.getAttribute("adminid");
//		System.out.println(title);
//		System.out.println(content);
//		System.out.println(id);
		
		XNotice notice = null;
		XFile noticeFile = null;
		
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		if(!isMultipart) {
			System.out.println("!!!ERROR!!! AdminNoticeWriteController에 Post방식으로 전달된 parameter가 multipart/form-data형식이 아닙니다.");
			return;
		}
		notice = new XNotice();
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
				
				if("title".equals(key)) {
					notice.setNoticeTitle(value);
				} else if("content".equals(key)) {
					notice.setNoticeContent(value);
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
				noticeFile = new XFile();
				noticeFile.setFileOriginName(origin);
				noticeFile.setFileStoredName(stored);
				noticeFile.setFileSize(Long.toString(item.getSize()));	//@@@ DB, DTO 만들 때 varchar2, String으로 해놔서 임시
				
			}
			
		}
		
		Connection conn = JDBCTemplate.getConnection();

		//게시글 번호 생성
		int noticeno = adminNoticeDao.selectNextNoticeno(conn);

		//첨부파일 정보가 있을 경우
		if(noticeFile != null) {
			int fileno = fileDao.selectNextFileno(conn);
			noticeFile.setFileNo(fileno);
			notice.setFileNo(fileno);
			if(fileDao.insertFile(conn, noticeFile) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//게시글 정보가 있을 경우 (있겠지만 혹시나)
		if(notice!=null) {
			notice.setAdminId((String)req.getSession().getAttribute("adminid"));
			notice.setNoticeNo(noticeno);
			if(notice.getNoticeTitle()==null||"".equals(notice.getNoticeTitle())) {
				notice.setNoticeTitle("(제목 없음)");
			}
			if(adminNoticeDao.insertNotice(conn, notice)>0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
				
		}
		
	}
	
}
