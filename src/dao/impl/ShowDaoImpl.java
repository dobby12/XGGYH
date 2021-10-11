package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ShowDao;
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
}
