package org.zerock.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO extends AbstractDAO {

	public BoardDAO() throws Exception {
		super();
	}

	public List<BoardVO> showList() throws Exception {

		Connection con = dataSource.getConnection();

		System.out.println(con);

		String sql = "select rn, bno, title, writer, regdate, updatedate from (select rownum rn, bno, title, writer, regdate, updatedate from tbl_board order by bno desc) where rn <= 100 and rn >= 1";

		List<BoardVO> list = new ArrayList<>();

		PreparedStatement psmt = con.prepareStatement(sql);

		ResultSet rs = psmt.executeQuery();

		while (rs.next()) {
			BoardVO vo = new BoardVO();

			vo.setBno(rs.getInt("bno"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setWriter(rs.getString("writer"));
			vo.setRegdate(rs.getDate("regdate"));
			vo.setUpdatedate(rs.getDate("updatedate"));

			list.add(vo);
		}

		return list;
	}

	public List<BoardVO> getPage(int page) throws Exception {

		Connection con = dataSource.getConnection();

		System.out.println(con);

		String sql = "select rn, bno, title, writer, regdate, updatedate from (select rownum rn, bno, title, writer, regdate, updatedate from tbl_board order by bno desc) where rn <= 100 and rn >= 1";

		List<BoardVO> list = new ArrayList<>();

		PreparedStatement psmt = con.prepareStatement(sql);

		psmt.setInt(1, page);
		psmt.setInt(2, page);

		psmt.executeUpdate();

		ResultSet rs = psmt.getResultSet();

		while (rs.next()) {
			BoardVO vo = new BoardVO();

			vo.setBno(rs.getInt("bno"));
			vo.setTitle(rs.getString("title"));
			vo.setWriter(rs.getString("writer"));
			vo.setRegdate(rs.getDate("regdate"));
			vo.setUpdatedate(rs.getDate("updatedate"));

			list.add(vo);
		}

		return list;
	}

	public int getListCount() throws Exception {

		Connection con = dataSource.getConnection();

		String sql = "select count(*) from tbl_board";

		int total = -1;

		PreparedStatement psmt = con.prepareStatement(sql);

		ResultSet rs = psmt.executeQuery();

		while (rs.next()) {
			total = rs.getInt(1);
		}

		return total;

	}

}
