package service.impl;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AdminDao;
import dao.impl.AdminDaoImpl;
import dto.XAdmin;
import service.face.AdminService;

public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao = new AdminDaoImpl();
	
	@Override
	public XAdmin getLoginAdmin(HttpServletRequest req) {
//		try {
//			req.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		XAdmin admin = new XAdmin();
		admin.setAdminId(req.getParameter("adminid"));
		admin.setAdminPw(req.getParameter("adminpw"));
		return admin;
	}

	@Override
	public boolean loginAdmin(XAdmin admin) {
		if(adminDao.selectCntAdminByAdminidAdminpw(JDBCTemplate.getConnection(), admin)==1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public XAdmin getAdmin(XAdmin admin) {
		return adminDao.selectAdminByAdminid(JDBCTemplate.getConnection(), admin);
	}
	
}
