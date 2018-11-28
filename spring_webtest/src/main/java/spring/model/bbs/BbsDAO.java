package spring.model.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.db.webtest.DBClose;
import spring.db.webtest.DBOpen;

@Repository
public class BbsDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	
	public int total(Map map) {						
			
		return mybatis.selectOne("memo.total", map);
	}

	public boolean checkRefnum(int bbsno) {
		boolean flag = false;

		
			int cnt = mybatis.selectOne("bbs.checkRefnum", bbsno);

			if (cnt > 0) {
				flag = true;
			}

	
		return flag;
	}

	public boolean replyCreate(BbsDTO dto) {
		boolean flag = false;

		int cnt = mybatis.insert("bbs.replyCreate", dto);
		if(cnt>0) flag =true;
			
		
		return flag;
	}

	public void upAnsnum(Map map) {
		mybatis.update("bbs.upAnsnum", map);
	}

	public BbsDTO replyRead(int bbsno) {
		return mybatis.selectOne("bbs.replyRead", bbsno);
	}

	public boolean create(BbsDTO dto) {

		boolean flag = false;
		
		int cnt = mybatis.insert("bbs.create", dto);
		
		if(cnt>0) flag = true;
		
		return flag;
	}

	public boolean delete(int bbsno) {
		boolean flag = false;

			int cnt = mybatis.delete("bbs.delete", bbsno);

			if (cnt > 0) 
				flag = true;
		
		return flag;
	}

	public BbsDTO read(int bbsno) {
		BbsDTO dto = new BbsDTO();

		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT bbsno, wname, title, content, viewcnt, wdate, grpno, indent, ansnum, filename, filesize ");
		sql.append(" FROM bbs ");
		sql.append(" WHERE bbsno=? ");

		try {
			pstmt = con.prepareStatement(sql.toString());

			pstmt.setInt(1, bbsno);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto.setBbsno(rs.getInt("bbsno"));
				dto.setWname(rs.getString("wname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setWdate(rs.getString("wdate"));
				dto.setGrpno(rs.getInt("grpno"));
				dto.setIndent(rs.getInt("indent"));
				dto.setAnsnum(rs.getInt("ansnum"));
				dto.setFilename(rs.getString("filename"));
				dto.setFilesize(rs.getInt("filesize"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}

		return dto;
	}

	public List<BbsDTO> list(Map map){
	
		return mybatis.selectList("bbs.list", map);
	}

	public void updateviewcnt(int bbsno) {

		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;

		StringBuffer sql = new StringBuffer();

		sql.append(" UPDATE bbs ");
		sql.append(" SET viewcnt = viewcnt + 1 ");
		sql.append(" WHERE bbsno = ? ");

		try {
			pstmt = con.prepareStatement(sql.toString());

			pstmt.setInt(1, bbsno);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
	}

	public boolean passCheck(Map map) {
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int bbsno = (Integer) map.get("bbsno");
		String passwd = (String) map.get("passwd");

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT COUNT(bbsno) as cnt ");
		sql.append(" FROM bbs ");
		sql.append(" WHERE bbsno=? AND passwd=? ");

		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			pstmt.setString(2, passwd);

			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");

			if (cnt > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		return flag;
	}

	public boolean update(BbsDTO dto) {
		
		return false;
	}
}
