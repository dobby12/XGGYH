package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AdminReviewDao;
import dao.impl.AdminReviewDaoImpl;
import dto.XFile;
import dto.XReview;
import service.face.AdminReviewService;
import util.Paging;

public class AdminReviewServiceImpl implements AdminReviewService {
	
	private AdminReviewDao adminReviewDao = new AdminReviewDaoImpl(); 

	@Override
	public Paging getPaging(HttpServletRequest req) {

		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}

		//XReview 테이블의 총 게시글 수를 조회한다
		int totalCount = adminReviewDao.selectCntReviewAll(JDBCTemplate.getConnection());

		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);

		return paging;

	}

	@Override
	public List<XReview> getReviewList(Paging paging) {

		//게시글 전체 조회 처리 - paging있음!
		return adminReviewDao.selectReviewAll(JDBCTemplate.getConnection(), paging);
	}
	
	@Override
	public XReview getReviewno(HttpServletRequest req) {

		XReview reviewno  = new XReview();

		//review_no 전달파라미터 검증 - null, ""
		String param = req.getParameter("review_no");
		if(param!=null && !"".equals(param)) {

			reviewno.setReview_no((Integer.parseInt(param)));
		}

		//결과 객체 반환
		return reviewno;
	}
	
	@Override
	public XReview getReviewDetail(XReview reviewno) {
		
		//게시글 조회
		XReview review = adminReviewDao.selectReviewbyReviewno(JDBCTemplate.getConnection(), reviewno);
		
		return review;
	}
	
	@Override
	public String getNick(XReview viewReview) {
		
		return adminReviewDao.selectNickByMemid(JDBCTemplate.getConnection(), viewReview);
	}
	
	@Override
	public String getTitle(XReview viewReview) {
	
		return adminReviewDao.selectShowTitleByShowno(JDBCTemplate.getConnection(), viewReview);
	}
	
	@Override
	public XFile getFile(XReview viewReview) {		
		return adminReviewDao.selectFile(JDBCTemplate.getConnection(), viewReview);
	}

	@Override
	public void setReviewDelete(XReview reviewno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if(adminReviewDao.deleteReviewFile(conn, reviewno) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if(adminReviewDao.deleteReview(conn, reviewno) > 0 ) {
			JDBCTemplate.commit(conn);
		} else { 
			JDBCTemplate.rollback(conn);
		}
	}
	

}
