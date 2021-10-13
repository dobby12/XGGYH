package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ShowDao;
import dao.impl.ShowDaoImpl;
import dto.XShow;
import service.face.ShowService;
import util.Paging;

public class ShowServiceImpl implements ShowService {

	// ShowDao 객체 생성
	private ShowDao showDao = new ShowDaoImpl();

	@Override
	public List<XShow> getShowList() {
		return showDao.selectShowAll(JDBCTemplate.getConnection());
	}

	@Override
	public List<XShow> getShowList(Paging paging) {
		return showDao.selectShowAll(JDBCTemplate.getConnection(), paging);
	}
	
	@Override
	public List<XShow> getShowList(Paging paging, int kindNo) {
		return showDao.selectShowAllByKindNo(JDBCTemplate.getConnection(), paging, kindNo);
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;

		// 한번에 몇개씩 보여줄건지
		int listCount = 6;

		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}

		int totalCount = showDao.selectCntAll(JDBCTemplate.getConnection());

		Paging paging = new Paging(totalCount, curPage, listCount);

		return paging;
	}
	
	@Override
	public Paging getParameterPaging(HttpServletRequest req, int kindNo) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		
		// 한번에 몇개씩 보여줄건지
		int listCount = 6;
		
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}

		int totalCount = showDao.selectCntBykindNo(JDBCTemplate.getConnection(), kindNo);

		Paging paging = new Paging(totalCount, curPage, listCount);

		return paging;
	}

	@Override
	public XShow getShowNo(HttpServletRequest req) {
		// ShowNo를 저장할 객체 생성
		XShow showNo = new XShow();

		String param = req.getParameter("showNo");
		if (param != null && !"".equals(param)) {

			showNo.setShowNo(Integer.parseInt(param));
		}

		return showNo;
	}

	@Override
	public int getKindNo(HttpServletRequest req) {
		// kindNo를 저장할 객체 생성
		int kindNo = 0;

		String param = req.getParameter("kindNo");
		if (param != null && !"".equals(param)) {

			kindNo = Integer.parseInt(param);
		}

		return kindNo;
	}

	@Override
	public XShow viewShowInfo(XShow showNo) {
		Connection conn = JDBCTemplate.getConnection();

		// 게시글 조회
		XShow showInfo = showDao.selectShowByShowno(conn, showNo);

		return showInfo;
	}
	
	@Override
	public String getkindName(int kindNo) {
		return showDao.selectKindNameByKindNo(JDBCTemplate.getConnection(), kindNo);
	}

	@Override
	public String getGenreName(XShow showInfo) {
		return showDao.selectGenreNameByGenreNo(JDBCTemplate.getConnection(), showInfo);
	}

	@Override
	public String getHallName(XShow showInfo) {
		return showDao.selectHallNameByHallNo(JDBCTemplate.getConnection(), showInfo);
	}
}
