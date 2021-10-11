package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.AdminShowDao;
import dao.impl.AdminShowDaoImpl;
import dto.XShow;
import service.face.AdminShowService;
import util.Paging;

public class AdminShowServiceImpl implements AdminShowService {

	AdminShowDao adminShowDao = new AdminShowDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<XShow> getShowList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

}
