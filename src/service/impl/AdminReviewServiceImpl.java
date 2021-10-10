package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AdminReviewDao;
import dao.impl.AdminReviewDaoImpl;
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

}
