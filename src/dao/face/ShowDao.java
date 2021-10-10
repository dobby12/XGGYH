package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XShow;

public interface ShowDao {

	public List<XShow> selectShowAll(Connection conn);
}
