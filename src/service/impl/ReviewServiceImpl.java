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
import dao.face.ReviewDao;
import dao.impl.ReviewDaoImpl;
import dto.XFile;
import dto.XReview;
import service.face.ReviewService;
import util.Paging;

public class ReviewServiceImpl implements ReviewService {

	private ReviewDao reviewDao = new ReviewDaoImpl();
	
	@Override
	public List<XReview> getList() {
		
		return reviewDao.selectAll(JDBCTemplate.getConnection());
		
	}
	
	@Override
	public List<XReview> getList(Paging paging) {

		return reviewDao.selectAll(JDBCTemplate.getConnection(), paging);
		
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
	public XReview getReviewNo(HttpServletRequest req) {
		
		XReview reviewNo = new XReview();
		
		String param = req.getParameter("reviewno");
		if(param!=null && !"".equals(param)) {
			
			reviewNo.setReviewNo( Integer.parseInt(param) );
		}
		
		return reviewNo;
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
	public void write(HttpServletRequest req) {
		
		XReview review = null;
		
		XFile reviewFile = null;
		
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			
			return;
		}
		
		review = new XReview();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(2 * 1024 * 1024); //2MB

		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir();
		factory.setRepository(repository);
		
		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setFileSizeMax(20 * 1024 * 1024); //20MB
		
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		Iterator<FileItem> iter = items.iterator();

		while( iter.hasNext() ) {
			FileItem item = iter.next();

			
			
			if( item.getSize() <= 0 ) {
				continue;
			}
			
			if( item.isFormField() ) {
				String key = item.getFieldName();
				
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

				if( "review_title".equals(key) ) {
					review.setReviewTitle( value );
				} else if( "review_content".equals(key) ) {
					review.setReviewContent( value );
				}
				
			} //if( item.isFormField() ) end
			
			
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
				
				reviewFile = new XFile();
				
				reviewFile.setFileOriginName(origin);
				reviewFile.setFileStoredName(stored);
				
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		int reviewNo = reviewDao.selectNextReviewNo(conn);
		
		if(review != null) {
			
			review.setMemId( (String)req.getSession().getAttribute("memId") );

			review.setReviewNo(reviewNo); //PK
			
			if(review.getReviewTitle()==null || "".equals(review.getReviewTitle())) {
				review.setReviewTitle("(제목없음)");
			}
			
			if( reviewDao.insert(conn, review) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		if(reviewFile != null) {
			reviewFile.setFileNo(reviewNo); //(FK)
			
			if( reviewDao.insertFile(conn, reviewFile) > 0 ) {
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
	public void update(HttpServletRequest req) {
		XReview review = null;
		
		XFile reviewFile = null;
				
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			
			return; //write() 메소드 중단
		}
		
		review = new XReview();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(2 * 1024 * 1024); //2MB

		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir();
		factory.setRepository(repository);
		
		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setFileSizeMax(20 * 1024 * 1024); //20MB

		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		Iterator<FileItem> iter = items.iterator();

		while( iter.hasNext() ) {
			FileItem item = iter.next();

			
			if( item.getSize() <= 0 ) {
				continue;
			}
			
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
				}
				
			} //if( item.isFormField() ) end
			
			
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
				
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
		
		
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		if(review != null) {
			if( reviewDao.update(conn, review) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		if(reviewFile != null) {
			reviewFile.setFileNo(review.getFileNo()); //게시글 번호 입력 (FK)
			
			if( reviewDao.insertFile(conn, reviewFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
	}
	
	@Override
	public void delete(XReview review) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( reviewDao.deleteFile(conn, review) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if( reviewDao.delete(conn, review) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

}