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
	public XReview getReview_no(HttpServletRequest req) {
		
		XReview review_no = new XReview();
		
		String param = req.getParameter("review_no");
		if(param!=null && !"".equals(param)) {
			
			review_no.setReview_no( Integer.parseInt(param) );
		}
		
		return review_no;
	}

	@Override
	public XReview view(XReview review_no) {

		Connection conn = JDBCTemplate.getConnection();

		if( reviewDao.updateReview_hit(conn, review_no) == 1 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		XReview review = reviewDao.selectReviewByReview_no(conn, review_no); 
		
		return review;
	}

	@Override
	public String getMem_nick(XReview viewReview) {
		return reviewDao.selectNickByMem_id(JDBCTemplate.getConnection(), viewReview);
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
					review.setReview_title( value );
				} else if( "review_content".equals(key) ) {
					review.setReview_content( value );
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
				
				XFile = new XFile();
				XFile.setFile_origin_name(origin);
				XFile.setFile_stored_name(stored);
				XFile.setFile_size( (int)item.getSize() );
				
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		int review_no = reviewDao.selectNextReview_no(conn);
		
		if(review != null) {
			
			review.setMem_id( (String)req.getSession().getAttribute("mem_id") );

			review.setReview_no(review_no); //PK
			
			if(review.getReview_title()==null || "".equals(review.getReview_title())) {
				review.setReview_title("(제목없음)");
			}
			
			if( reviewDao.insert(conn, review) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		if(reviewFile != null) {
			reviewFile.setReview_no(review_no); //게시글 번호 입력 (FK)
			
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

				if( "review_no".equals(key) ) {
					review.setReview_no( Integer.parseInt(value) );
				} else if( "review_title".equals(key) ) {
					review.setReview_title( value );
				} else if( "review_content".equals(key) ) {
					review.setReview_content( value );
				}
				
			} //if( item.isFormField() ) end
			
			
			if( !item.isFormField() ) {
				
				UUID uuid = UUID.randomUUID();
				String uid = uuid.toString().split("-")[0];
				
				File upFolder = new File(req.getServletContext().getRealPath("upload"));
				upFolder.mkdir();
				
				String origin = item.getName()
				String stored = origin + "_" + uid;
				File up = new File(upFolder, stored);
				
				try {
					item.write(up);
					item.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}

				reviewFile = new ReviewFile();
				reviewFile.setOriginname(origin);
				reviewFile.setStoredname(stored);
				reviewFile.setFilesize( (int)item.getSize() );
				
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
			reviewFile.setReview_no(review.getReview_no()); //게시글 번호 입력 (FK)
			
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