package dao.face;

import java.sql.Connection;

import dto.XHall;


public interface HallDao {
	
	public XHall selectHallNameByHallNo(Connection conn, int hallNo);
}
