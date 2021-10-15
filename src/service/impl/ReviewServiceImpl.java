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
import dao.face.FileDao;
import dao.face.ReviewDao;
import dao.impl.FileDaoImpl;
import dao.impl.ReviewDaoImpl;
import dto.XFile;
import dto.XReview;
import dto.XShow;
import service.face.ReviewService;
import util.Paging;

public class ReviewServiceImpl implements ReviewService {

	private ReviewDao reviewDao = new ReviewDaoImpl();
	private FileDao fileDao = new FileDaoImpl();
	
//	@Override
//	public List<XReview> getList() {
//		
//		return reviewDao.selectAll(JDBCTemplate.getConnection());
//		
//	}
	
	@Override
	public List<XReview> getList(Paging paging) {

		return reviewDao.selectAll(JDBCTemplate.getConnection(), paging);
		
	}
	
	@Override
	public List<XReview> getListhit(Paging paging) {
		
		return reviewDao.selectAllHit(JDBCTemplate.getConnection(), paging);
		
	}
	
	@Override
	public List<XReview> searchReviewList(HttpServletRequest req, Paging paging) {
		
		//전달 파라미터 얻기 - searchtype, keyword
		String searchtype = (String)req.getParameter("searchtype");
		String keyword = (String)req.getParameter("keyword");

		return reviewDao.selectReviewSearchByReviewTitle(JDBCTemplate.getConnection(), keyword, paging);
	}
	
	@Override
	public Paging getParameterPaging(HttpServletRequest req) {
		
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		String searchtype = (String)req.getParameter("searchtype");
		String keyword = (String)req.getParameter("keyword");

		int totalCount = reviewDao.selectCntSearchReviewAll(JDBCTemplate.getConnection(), searchtype, keyword);

		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}
	
	@Override
	public List<XReview> getReviewListByMemid(Paging paging, String memid) {
		
		return reviewDao.selectAllByMemid(JDBCTemplate.getConnection(), paging, memid);
		
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {

		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		int totalCount = reviewDao.selectCntAll(JDBCTemplate.getConnection());
		
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}
	
	@Override
	public Paging getPagingByMemId(HttpServletRequest req, String memid) {
		
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		int totalCount = reviewDao.selectCntByMemId(JDBCTemplate.getConnection(), memid);
		
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;	
	}

	@Override
	public XReview getReviewNo(HttpServletRequest req) {
		
		XReview reviewNo = new XReview();
		
		String param = req.getParameter("reviewno");
		if(param!=null && !"".equals(param)) {
			
			reviewNo.setReviewNo( Integer.parseInt(param) );
		}
		
		return reviewNo;
	}
	
	@Override
	public XShow getShowNo(HttpServletRequest req) {
		
		XShow showNo = new XShow();
		
		String param = req.getParameter("showno");
		if(param!=null && !"".equals(param)) {
			
			showNo.setShowNo( Integer.parseInt(param) );
		}
		
		return showNo;
	}
	

	@Override
	public XReview view(XReview reviewNo) {

		Connection conn = JDBCTemplate.getConnection();

		if( reviewDao.updateReviewHit(conn, reviewNo) == 1 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		XReview review = reviewDao.selectReviewByReviewNo(conn, reviewNo); 
		
		return review;
	}

	@Override
	public String getMemNick(XReview viewReview) {
		return reviewDao.selectNickByMemId(JDBCTemplate.getConnection(), viewReview);
	}
	
	@Override
	public String getShowTitle(XReview viewReview) {
		return reviewDao.selectShowTitleByShowNo(JDBCTemplate.getConnection(), viewReview);
	}

	@Override
	public XReview getReviewDetail(int reviewno) {
		return reviewDao.selectReviewToReviewno(JDBCTemplate.getConnection(), reviewno);
	}
	
	
	
	
	
	
	@Override
	public void write(HttpServletRequest req) {
		
		//입력한 게시글전달
		XReview review = null;
		XShow show = null;
		
		//업로드한 첨부파일 전달
		XFile reviewFile = null;
		
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			return;
		}
		//입력을 저장할 DTO 생성
		review = new XReview();
		show = new XShow();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(10 * 1024 * 1024); //10MB

		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir();
		factory.setRepository(repository);
		
		//파일 업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		//업로드 용량 제한
		upload.setFileSizeMax(10 * 1024 * 1024); //10MB
		
		//전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		//파싱된 전달파라미터 처리할 반복자
		Iterator<FileItem> iter = items.iterator();
		while( iter.hasNext() ) {
			FileItem item = iter.next();

			//빈파일
			if( item.getSize() <= 0 ) {
				continue;
			}
			
			//form DB삽입
			if( item.isFormField() ) {
				String key = item.getFieldName();
				String value = null;

				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

				if( "reviewTitle".equals(key) ) {
					review.setReviewTitle( value );
				} else if ( "reviewContent".equals(key) ) {
					review.setReviewContent( value );
				} else if ( "reviewScore".equals(key) ) {
					review.setReviewScore( Integer.parseInt(value) );
				} else if ( "showNo".equals(key) ) {
					review.setShowNo( Integer.parseInt(value) );
				}
				
			} //if( item.isFormField() ) end
			
			
			//업로드파일
			if( !item.isFormField() ) {
				
				UUID uuid = UUID.randomUUID();
				String uid = uuid.toString().split("-")[0];
				
				File upFolder = new File(req.getServletContext().getRealPath("upload"));
				upFolder.mkdir(); //폴더 생성
				
				String origin = item.getName();
				String stored = origin + "_" + uid;
				File up = new File(upFolder, stored);
				
				try {
					item.write(up);
					item.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일 정보 저장
				reviewFile = new XFile();
				reviewFile.setFileOriginName(origin);
				reviewFile.setFileStoredName(stored);
				reviewFile.setFileSize( Long.toString(item.getSize()) );
				
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글번호생성
		int reviewNo = reviewDao.selectNextReviewNo(conn);
		
		//첨부파일정보 있을경우
		if(reviewFile != null) {
			int fileno = fileDao.selectNextFileno(conn);
			reviewFile.setFileNo(fileno);
			review.setFileNo(fileno);
			
			if( fileDao.insertFile(conn, reviewFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		//게시글정보있을경우
		if(review != null) {
			review.setMemId((String)req.getSession().getAttribute("memid"));
			
			review.setReviewNo(reviewNo);
			
			if(review.getReviewTitle()==null || "".equals(review.getReviewTitle())) {
				review.setReviewTitle("(제목없음)");
			}
			
			if( reviewDao.insert(conn, review, show) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
	}

	@Override
	public XFile viewFile(XReview viewReview) {
		return reviewDao.selectFile(JDBCTemplate.getConnection(), viewReview);
	}
	
	@Override
	public XFile getFile(int reviewno) {
		return reviewDao.selectFileByFileNo(JDBCTemplate.getConnection(), reviewno);
	}
	
	
	
	
	
	
	
	
	@Override
	public void update(HttpServletRequest req) {
		//게시글정보 DTO
		XReview review = null;
		
		//첨부파일정보 DTO
		XFile reviewFile = null;
		
		//파일업로드가 형식에맞는지
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			
			return; //write() 메소드 중단
		}
		
		//게시글정보 저장 DTO
		review = new XReview();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(1 * 1024 * 1024); //1MB

		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir();
		factory.setRepository(repository);
		
		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setFileSizeMax(10 * 1024 * 1024); //10MB
		
		//파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		//반복자
		Iterator<FileItem> iter = items.iterator();

		while( iter.hasNext() ) {
			FileItem item = iter.next();
			
			//빈파일
			if( item.getSize() <= 0 ) {
				continue;
			}
			
			//form
			if( item.isFormField() ) {
				String key = item.getFieldName();
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

				if( "reviewNo".equals(key) ) {
					review.setReviewNo( Integer.parseInt(value) );
				} else if( "reviewTitle".equals(key) ) {
					review.setReviewTitle( value );
				} else if( "reviewContent".equals(key) ) {
					review.setReviewContent( value );
				} else if( "reviewScore".equals(key) ) {
					review.setReviewScore( Integer.parseInt(value) );
				} else if ( "showNo".equals(key) ) {
					review.setShowNo( Integer.parseInt(value) );
				}
			} //if( item.isFormField() ) end
			
			
			//업로드파일
			if( !item.isFormField() ) {
				
				UUID uuid = UUID.randomUUID();
				String uid = uuid.toString().split("-")[0];
				
				File upFolder = new File(req.getServletContext().getRealPath("upload"));
				upFolder.mkdir();
				
				String origin = item.getName();
				String stored = origin + "_" + uid;
				File up = new File(upFolder, stored);
				
				try {
					item.write(up);
					item.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				reviewFile = new XFile();
				reviewFile.setFileOriginName(origin);
				reviewFile.setFileStoredName(stored);
				reviewFile.setFileSize( Long.toString(item.getSize()) );
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
		
		Connection conn = JDBCTemplate.getConnection();
		
		//첨부파일정보 있을경우
		if(reviewFile != null) {
			int fileno = fileDao.selectNextFileno(conn);
			reviewFile.setFileNo(fileno);
			review.setFileNo(fileno);

			if( fileDao.insertFile(conn, reviewFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		//게시글정보있을경우
		if(review != null) {
			if( reviewDao.update(conn, review) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
	}
	
	@Override
	public void delete(int reviewno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int fileno = getFile(reviewno).getFileNo();
		
		if( fileDao.deleteFile(conn, fileno) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		if( reviewDao.delete(conn, reviewno) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
}