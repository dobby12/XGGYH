package service.impl;

import dao.face.AdminNoticeDao;
import dao.impl.AdminNoticeDaoImpl;
import service.face.AdminNoticeService;

public class AdminNoticeServiceImpl implements AdminNoticeService {

	private AdminNoticeDao adminNoticeDao = new AdminNoticeDaoImpl();
	
}
