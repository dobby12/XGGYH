package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ShowDao;
import dao.impl.ShowDaoImpl;
import dto.XShow;
import service.face.ShowService;
import util.Paging;

public class ShowServiceImpl implements ShowService {

	//ShowDao 객체 생성
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
	public Paging getPaging(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		
		//한번에 몇개씩 보여줄건지
		int listCount = 6;
		
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		int totalCount = showDao.selectCntAll(JDBCTemplate.getConnection());
		
		Paging paging = new Paging(totalCount, curPage, listCount);
		
		return paging;
	}
}
