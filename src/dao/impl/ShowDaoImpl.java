package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ShowDao;
import dto.XMem;
import dto.XShow;
import util.Paging;

public class ShowDaoImpl implements ShowDao {

	// PreparedStatement 객체, ResultSet 객체 추가
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<XShow> selectShowAll(Connection conn) {
		List<XShow> showList = null;

		String sql = "";
		sql += "SELECT * FROM XSHOW ";
		sql += "ORDER BY show_no";

		showList = new ArrayList<>();

		try {
			// 쿼리 실행
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				// 입력받을 객체
				XShow showInfo = new XShow();

				// 행 하나씩 가져오기
				showInfo.setShowNo(rs.getInt("show_no"));
				showInfo.setFileNo(rs.getInt("file_no"));
				showInfo.setAdminId(rs.getString("admin_id"));
				showInfo.setKindNo(rs.getInt("kind_no"));
				showInfo.setGenreNo(rs.getInt("genre_no"));
				showInfo.setHallNo(rs.getInt("hall_no"));
				showInfo.setShowTitle(rs.getString("show_title"));
				showInfo.setShowContent(rs.getString("show_content"));
				showInfo.setShowDate(rs.getDate("show_date"));
				showInfo.setShowAge(rs.getString("show_age"));
				showInfo.setShowDirector(rs.getString("show_director"));
				showInfo.setShowActor(rs.getString("show_actor"));
				showInfo.setShowStart(rs.getDate("show_start"));
				showInfo.setShowEnd(rs.getDate("show_end"));

				showList.add(showInfo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// rs, ps객체 제거
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return showList;
	}

	@Override
	public List<XShow> selectShowAll(Connection conn, Paging paging) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, X.* FROM (";
		sql += "		SELECT";
		sql += "			show_no, file_no, admin_id, kind_no, genre_no, hall_no, show_title";
		sql += "			, show_content, show_date, show_age, show_director, show_actor, show_start, show_end";
		sql += "		FROM XShow";
		sql += "		ORDER BY show_no DESC";
		sql += "	) X";
		sql += " ) XShow";
		sql += " WHERE rnum BETWEEN ? AND ?";

		// 결과 저장할 List
		List<XShow> showList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				XShow showInfo = new XShow();

				showInfo.setShowNo(rs.getInt("show_no"));
				showInfo.setFileNo(rs.getInt("file_no"));
				showInfo.setAdminId(rs.getString("admin_id"));
				showInfo.setKindNo(rs.getInt("kind_no"));
				showInfo.setGenreNo(rs.getInt("genre_no"));
				showInfo.setHallNo(rs.getInt("hall_no"));
				showInfo.setShowTitle(rs.getString("show_title"));
				showInfo.setShowContent(rs.getString("show_content"));
				showInfo.setShowDate(rs.getDate("show_date"));
				showInfo.setShowAge(rs.getString("show_age"));
				showInfo.setShowDirector(rs.getString("show_director"));
				showInfo.setShowActor(rs.getString("show_actor"));
				showInfo.setShowStart(rs.getDate("show_start"));
				showInfo.setShowEnd(rs.getDate("show_end"));

				// 리스트에 결과값 저장
				showList.add(showInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return showList;
	}

	@Override
	public List<XShow> selectShowAllByKindNo(Connection conn, Paging paging, int kindNo) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, X.* FROM (";
		sql += "		SELECT";
		sql += "			show_no, file_no, admin_id, kind_no, genre_no, hall_no, show_title";
		sql += "			, show_content, show_date, show_age, show_director, show_actor, show_start, show_end";
		sql += "		FROM XShow";
		sql += "		WHERE kind_no = ?";
		sql += "		ORDER BY show_no DESC";
		sql += "	) X";
		sql += " ) XShow";
		sql += " WHERE rnum BETWEEN ? AND ?";

		// 결과 저장할 List
		List<XShow> showList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, kindNo);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				XShow showInfo = new XShow();

				showInfo.setShowNo(rs.getInt("show_no"));
				showInfo.setFileNo(rs.getInt("file_no"));
				showInfo.setAdminId(rs.getString("admin_id"));
				showInfo.setKindNo(rs.getInt("kind_no"));
				showInfo.setGenreNo(rs.getInt("genre_no"));
				showInfo.setHallNo(rs.getInt("hall_no"));
				showInfo.setShowTitle(rs.getString("show_title"));
				showInfo.setShowContent(rs.getString("show_content"));
				showInfo.setShowDate(rs.getDate("show_date"));
				showInfo.setShowAge(rs.getString("show_age"));
				showInfo.setShowDirector(rs.getString("show_director"));
				showInfo.setShowActor(rs.getString("show_actor"));
				showInfo.setShowStart(rs.getDate("show_start"));
				showInfo.setShowEnd(rs.getDate("show_end"));

				// 리스트에 결과값 저장
				showList.add(showInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return showList;
	}

	@Override
	public int selectCntAll(Connection conn) {
		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM XShow";

		// 총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public int selectCntBykindNo(Connection conn, int kindNo) {
		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM XShow ";
		sql += "WHERE kind_no = ?";

		// 총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, kindNo);

			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public int selectCntByshowTitle(Connection conn, String showTitle, int kindNo) {
		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM XShow ";
		sql += "WHERE kind_no = ? AND show_title like ?";

		// 총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, kindNo);
			ps.setString(2, "%" + showTitle + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}
	
	@Override
	public int selectCntByShowTitle(Connection connection, String showTitle) {
		// SQL 작성
				String sql = "";
				sql += "SELECT count(*) FROM XShow ";
				sql += "WHERE show_title like ?";

				// 총 게시글 수
				int count = 0;

				try {
					ps = connection.prepareStatement(sql);
					ps.setString(1, "%" + showTitle + "%");

					rs = ps.executeQuery();

					while (rs.next()) {
						count = rs.getInt(1);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}

				return count;
	}

	@Override
	public int selectCntBymemGenre(Connection conn, int kindNo, int memGenre) {
		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM XShow ";
		sql += "WHERE kind_no = ? AND genre_no = ?";

		// 총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, kindNo);
			ps.setInt(2, memGenre);

			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public XShow selectShowByShowno(Connection conn, XShow showNo) {
		String sql = "";
		sql += "SELECT * FROM XShow";
		sql += " WHERE show_no = ?";

		XShow showInfo = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, showNo.getShowNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				showInfo = new XShow();

				showInfo.setShowNo(rs.getInt("show_no"));
				showInfo.setFileNo(rs.getInt("file_no"));
				showInfo.setAdminId(rs.getString("admin_id"));
				showInfo.setKindNo(rs.getInt("kind_no"));
				showInfo.setGenreNo(rs.getInt("genre_no"));
				showInfo.setHallNo(rs.getInt("hall_no"));
				showInfo.setShowTitle(rs.getString("show_title"));
				showInfo.setShowContent(rs.getString("show_content"));
				showInfo.setShowDate(rs.getDate("show_date"));
				showInfo.setShowAge(rs.getString("show_age"));
				showInfo.setShowDirector(rs.getString("show_director"));
				showInfo.setShowActor(rs.getString("show_actor"));
				showInfo.setShowStart(rs.getDate("show_start"));
				showInfo.setShowEnd(rs.getDate("show_end"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return showInfo;
	}

	@Override
	public String selectKindNameByKindNo(Connection conn, int kindNo) {
		String sql = "";
		sql += "SELECT * FROM XKIND";
		sql += " WHERE kind_no = ?";

		String kindName = "";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, kindNo);

			rs = ps.executeQuery();

			while (rs.next()) {
				kindName = rs.getString("kind_name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return kindName;
	}

	@Override
	public String selectGenreNameByGenreNo(Connection conn, XShow showInfo) {
		String sql = "";
		sql += "SELECT * FROM XGENRE";
		sql += " WHERE genre_no = ?";

		String genreName = "";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, showInfo.getGenreNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				genreName = rs.getString("genre_name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return genreName;
	}

	@Override
	public String selectHallNameByHallNo(Connection conn, XShow showInfo) {
		String sql = "";
		sql += "SELECT * FROM XHALL";
		sql += " WHERE hall_no = ?";

		String hallName = "";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, showInfo.getHallNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				hallName = rs.getString("hall_name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return hallName;
	}

	@Override
	public List<XShow> selectShowSearchByshowTitle(Connection conn, String keyword, int kindNo, Paging paging) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, X.* FROM (";
		sql += "		SELECT";
		sql += "			show_no, file_no, admin_id, kind_no, genre_no, hall_no, show_title";
		sql += "			, show_content, show_date, show_age, show_director, show_actor, show_start, show_end";
		sql += "		FROM XShow";
		sql += "		WHERE kind_no = ? AND show_title like ?";
		sql += "		ORDER BY show_no DESC";
		sql += "	) X";
		sql += " ) XShow";
		sql += " WHERE rnum BETWEEN ? AND ?";

		// 결과 저장할 List
		List<XShow> showList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, kindNo);
			ps.setString(2, "%" + keyword + "%");
			ps.setInt(3, paging.getStartNo());
			ps.setInt(4, paging.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				XShow showInfo = new XShow();

				showInfo.setShowNo(rs.getInt("show_no"));
				showInfo.setFileNo(rs.getInt("file_no"));
				showInfo.setAdminId(rs.getString("admin_id"));
				showInfo.setKindNo(rs.getInt("kind_no"));
				showInfo.setGenreNo(rs.getInt("genre_no"));
				showInfo.setHallNo(rs.getInt("hall_no"));
				showInfo.setShowTitle(rs.getString("show_title"));
				showInfo.setShowContent(rs.getString("show_content"));
				showInfo.setShowDate(rs.getDate("show_date"));
				showInfo.setShowAge(rs.getString("show_age"));
				showInfo.setShowDirector(rs.getString("show_director"));
				showInfo.setShowActor(rs.getString("show_actor"));
				showInfo.setShowStart(rs.getDate("show_start"));
				showInfo.setShowEnd(rs.getDate("show_end"));

				// 리스트에 결과값 저장
				showList.add(showInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return showList;
	}

	@Override
	public List<XShow> selectShowSearchByMemgenre(Connection conn, XMem memInfo, int kindNo, Paging paging) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, X.* FROM (";
		sql += "		SELECT";
		sql += "			show_no, file_no, admin_id, kind_no, genre_no, hall_no, show_title";
		sql += "			, show_content, show_date, show_age, show_director, show_actor, show_start, show_end";
		sql += "		FROM XShow";
		sql += "		WHERE kind_no = ? AND genre_no = ?";
		sql += "		ORDER BY show_no DESC";
		sql += "	) X";
		sql += " ) XShow";
		sql += " WHERE rnum BETWEEN ? AND ?";

		// 결과 저장할 List
		List<XShow> showList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, kindNo);
			ps.setInt(2, memInfo.getGenreNo());
			ps.setInt(3, paging.getStartNo());
			ps.setInt(4, paging.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				XShow showInfo = new XShow();

				showInfo.setShowNo(rs.getInt("show_no"));
				showInfo.setFileNo(rs.getInt("file_no"));
				showInfo.setAdminId(rs.getString("admin_id"));
				showInfo.setKindNo(rs.getInt("kind_no"));
				showInfo.setGenreNo(rs.getInt("genre_no"));
				showInfo.setHallNo(rs.getInt("hall_no"));
				showInfo.setShowTitle(rs.getString("show_title"));
				showInfo.setShowContent(rs.getString("show_content"));
				showInfo.setShowDate(rs.getDate("show_date"));
				showInfo.setShowAge(rs.getString("show_age"));
				showInfo.setShowDirector(rs.getString("show_director"));
				showInfo.setShowActor(rs.getString("show_actor"));
				showInfo.setShowStart(rs.getDate("show_start"));
				showInfo.setShowEnd(rs.getDate("show_end"));

				// 리스트에 결과값 저장
				showList.add(showInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return showList;
	}

	@Override
	public List<XShow> selectAllShowSearchByshowTitle(Connection connection, String keyword, Paging paging) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, X.* FROM (";
		sql += "		SELECT";
		sql += "			show_no, file_no, admin_id, kind_no, genre_no, hall_no, show_title";
		sql += "			, show_content, show_date, show_age, show_director, show_actor, show_start, show_end";
		sql += "		FROM XShow";
		sql += "		WHERE show_title like ?";
		sql += "		ORDER BY show_no DESC";
		sql += "	) X";
		sql += " ) XShow";
		sql += " WHERE rnum BETWEEN ? AND ?";

		// 결과 저장할 List
		List<XShow> showList = new ArrayList<>();

		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				XShow showInfo = new XShow();

				showInfo.setShowNo(rs.getInt("show_no"));
				showInfo.setFileNo(rs.getInt("file_no"));
				showInfo.setAdminId(rs.getString("admin_id"));
				showInfo.setKindNo(rs.getInt("kind_no"));
				showInfo.setGenreNo(rs.getInt("genre_no"));
				showInfo.setHallNo(rs.getInt("hall_no"));
				showInfo.setShowTitle(rs.getString("show_title"));
				showInfo.setShowContent(rs.getString("show_content"));
				showInfo.setShowDate(rs.getDate("show_date"));
				showInfo.setShowAge(rs.getString("show_age"));
				showInfo.setShowDirector(rs.getString("show_director"));
				showInfo.setShowActor(rs.getString("show_actor"));
				showInfo.setShowStart(rs.getDate("show_start"));
				showInfo.setShowEnd(rs.getDate("show_end"));

				// 리스트에 결과값 저장
				showList.add(showInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return showList;
	}
	
	
	@Override
	public List<XShow> selectShowByGenreno(Connection connection, int loginIdGenreno) {

		String sql = "SELECT show_no, file_no, admin_id, kind_no, genre_no, hall_no, show_title, show_content, show_date, show_age, show_director, show_actor, show_start, show_end FROM XShow"
				+ " WHERE GENRE_NO=?";
		List<XShow> showList = new ArrayList<>();

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, loginIdGenreno);

			rs = ps.executeQuery();

			while (rs.next()) {
				XShow showInfo = new XShow();

				showInfo.setShowNo(rs.getInt("show_no"));
				showInfo.setFileNo(rs.getInt("file_no"));
				showInfo.setAdminId(rs.getString("admin_id"));
				showInfo.setKindNo(rs.getInt("kind_no"));
				showInfo.setGenreNo(rs.getInt("genre_no"));
				showInfo.setHallNo(rs.getInt("hall_no"));
				showInfo.setShowTitle(rs.getString("show_title"));
				showInfo.setShowContent(rs.getString("show_content"));
				showInfo.setShowDate(rs.getDate("show_date"));
				showInfo.setShowAge(rs.getString("show_age"));
				showInfo.setShowDirector(rs.getString("show_director"));
				showInfo.setShowActor(rs.getString("show_actor"));
				showInfo.setShowStart(rs.getDate("show_start"));
				showInfo.setShowEnd(rs.getDate("show_end"));

				// 리스트에 결과값 저장
				showList.add(showInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return showList;
	}
}
