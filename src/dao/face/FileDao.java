package dao.face;

import java.sql.Connection;

import dto.XFile;

public interface FileDao {

	/**
	 * 
	 * @param conn
	 * @return
	 */
	public int selectNextFileno(Connection conn);

	/**
	 * 
	 * @param conn
	 * @param file
	 * @return
	 */
	public int insertFile(Connection conn, XFile file);
	
}
