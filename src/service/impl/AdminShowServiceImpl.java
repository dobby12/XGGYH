package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AdminShowDao;
import dao.impl.AdminShowDaoImpl;
import dto.XFile;
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
	
	@Override
	public XShow getShowno(HttpServletRequest req) {
		
		XShow showno = new XShow();
		
		String param = req.getParameter("showno");
		if(param != null && !"".equals(param)) {

			showno.setShowNo((Integer.parseInt(param)));
		}
		
		return showno;
	}
	
	@Override
	public XShow getShowDetail(XShow viewShow) {
		
		return adminShowDao.selectShowByShowno(JDBCTemplate.getConnection(), viewShow);
	}
	
	@Override
	public String getKind(XShow viewShow) {
		
		return adminShowDao.selectKindByKindno(JDBCTemplate.getConnection(), viewShow);
	}

	@Override
	public String getGenre(XShow viewShow) {

		return adminShowDao.selectGenrebyGenreno(JDBCTemplate.getConnection(), viewShow);
	}
	
	@Override
	public String getHallname(XShow viewShow) {

		return adminShowDao.selectHallnameByHallno(JDBCTemplate.getConnection(), viewShow);
	}

	@Override
	public XFile getFile(XShow viewShow) {
	
		return adminShowDao.selectFile(JDBCTemplate.getConnection(), viewShow);
	}
	
	
}
