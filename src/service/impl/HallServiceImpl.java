package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.HallDao;
import dao.impl.HallDaoImpl;
import dto.XHall;
import service.face.HallService;

public class HallServiceImpl implements HallService{
	
	//HallService에서 사용할 HallDao 객체
	private HallDao hallDao = new HallDaoImpl();

	@Override
	public int getHallNo(HttpServletRequest req) {
		
		int hallNo = 0;
		
		String param = req.getParameter("hallNo");
		if(param!=null && !"".equals(param)) {

			hallNo = Integer.parseInt(param);
		}
		
		return hallNo;
	}

	@Override
	public XHall getHallInfo(int hallNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 조회
		XHall hallInfo = hallDao.selectHallNameByHallNo(conn, hallNo);
		
		return hallInfo;
	}

}