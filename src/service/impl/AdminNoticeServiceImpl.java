package service.impl;

import java.util.List;

import common.JDBCTemplate;
import dao.face.AdminNoticeDao;
import dao.impl.AdminNoticeDaoImpl;
import dto.XFile;
import dto.XNotice;
import service.face.AdminNoticeService;

public class AdminNoticeServiceImpl implements AdminNoticeService {

	private AdminNoticeDao adminNoticeDao = new AdminNoticeDaoImpl();

	@Override
	public List<XNotice> getNoticeList() {
		return adminNoticeDao.selectNoticeAll(JDBCTemplate.getConnection());
	}

	@Override
	public XNotice getNoticeDetail(int noticeno) {
		return adminNoticeDao.selectNoticeByNoticeno(JDBCTemplate.getConnection(), noticeno);
	}

	@Override
	public XFile getFile(int noticeno) {
		return adminNoticeDao.selectFileByFileno(JDBCTemplate.getConnection(), noticeno);
	}
	
}
