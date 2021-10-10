package service.impl;

import java.util.List;

import common.JDBCTemplate;
import dao.face.ShowDao;
import dao.impl.ShowDaoImpl;
import dto.XShow;
import service.face.ShowService;

public class ShowServiceImpl implements ShowService {

	//ShowDao 객체 생성
	private ShowDao showDao = new ShowDaoImpl();
	
	@Override
	public List<XShow> getShowList() {
		return showDao.selectShowAll(JDBCTemplate.getConnection());
	}
}
