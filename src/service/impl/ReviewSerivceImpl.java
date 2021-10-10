package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ReviewDao;
import dao.impl.ReviewDaoImpl;
import dto.XReview;
import service.face.ReviewService;
import util.Paging;

public class ReviewSerivceImpl implements ReviewService {
	
	private ReviewDao reviewDao = new ReviewDaoImpl();

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
			System.out.println("[Warning] curPage값이 null이거나 비어있습니다");
		}
		
		int totalCount = reviewDao.selectCntAll(JDBCTemplate.getConnection());
		
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public XReview getReviewno(HttpServletRequest req) {
		
		XReview reviewno = new XReview();
		
		String param = req.getParameter("reviewno");
		if(param != null && !"".equals(param)) {
			reviewno.setReview_no( Integer.parseInt(param));
		}
		return reviewno;
	}
}
