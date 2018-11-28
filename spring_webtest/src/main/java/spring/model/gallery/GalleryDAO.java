package spring.model.gallery;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class GalleryDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}

//	public boolean updateFile(Map map) {
//		boolean flag = false;
//		Connection con = DBOpen.open();
//		PreparedStatement pstmt = null;
//
//		String fname = (String) map.get("fname");
//		int gno = (Integer) map.get("gno");
//
//		StringBuffer sql = new StringBuffer();
//
//		sql.append(" update gallery ");
//		sql.append(" set fname = ? ");
//		sql.append(" where gno = ? ");
//
//		try {
//			pstmt = con.prepareStatement(sql.toString());
//
//			pstmt.setString(1, fname);
//			pstmt.setInt(2, gno);
//
//			int cnt = pstmt.executeUpdate();
//
//			if (cnt > 0) {
//				flag = true;
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return flag;
//	}
	
	public boolean delete(int gno) {
		boolean flag = false;
	
			int cnt = mybatis.delete("gallery.delete", gno);
			
			if(cnt > 0 ) flag = true;
		
		return flag;
	}
	
	public GalleryDTO read(int gno) {
		return mybatis.selectOne("gallery.read", gno);
	}

	public void upViewCnt(int gno) {
		mybatis.update("gallery.upViewCnt", gno);
	}

	public List readList(int gno) {
		List list = new ArrayList();
			Map map = mybatis.selectOne("gallery.readList", gno);
			
				int[] lGno = {
						((BigDecimal)map.get("PRE_GNO2")).intValue(),
						((BigDecimal)map.get("PRE_GNO1")).intValue(),
						((BigDecimal)map.get("GNO")).intValue(),
						((BigDecimal)map.get("NEX_GNO1")).intValue(),
						((BigDecimal)map.get("NEX_GNO2")).intValue(),
				};
				
				String[] lFname = { 
						(String)map.get("PRE_FNAME2"),
						(String)map.get("PRE_FNAME1"),
						(String)map.get("FNAME"),
						(String)map.get("NEX_FNAME1"),
						(String)map.get("NEX_FNAME2"),
				};
				
				list.add(lGno);
				list.add(lFname);
			
		return list;
	}

	/*public boolean passwdCheck(Map map) {
		boolean flag = false;

		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int gno = (Integer) map.get("gno");
		String oldpw = (String) map.get("oldpw");

		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT COUNT(*) ");
		sql.append(" FROM gallery ");
		sql.append(" where gno = ? and pw = ? ");

		try {
			pstmt = con.prepareStatement(sql.toString());

			pstmt.setInt(1, gno);
			pstmt.setString(2, oldpw);

			rs = pstmt.executeQuery();
			rs.next();

			int check = rs.getInt(1);

			if (check == 1) {
				flag = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}

		return flag;
	}*/
	public boolean passwdCheck(Map map) {
		boolean flag = false;
		int cnt = mybatis.selectOne("gallery.passwdCheck", map);
		if (cnt > 0)
			flag = true;
		
		return flag;
	}


	public boolean updatePasswd(Map map) {
		boolean flag = false;
		
		int cnt = mybatis.update("gallery.updatePasswd", map);
			if (cnt > 0) {
				flag = true;
			}
		return flag;
	}


//	public boolean updateInfo(Map map) {
//		boolean flag = false;
//		Connection con = DBOpen.open();
//		PreparedStatement pstmt = null;
//
//		int gno = (Integer)map.get("gno");
//		String title = (String)map.get("title");
//		String writer = (String)map.get("writer");
//		String content = (String)map.get("content");
//		String fname = (String)map.get("fname");
//		
//		StringBuffer sql = new StringBuffer();
//
//		sql.append(" update gallery ");
//		sql.append(" set fname = ?, title = ?, writer = ?, content = ? ");
//		sql.append(" where gno = ? ");
//
//		try {
//			pstmt = con.prepareStatement(sql.toString());
//
//			pstmt.setString(1, fname);
//			pstmt.setString(2, title);
//			pstmt.setString(3, writer);
//			pstmt.setString(4, content);
//			pstmt.setInt(5, gno);
//			
//			int cnt = pstmt.executeUpdate();
//
//			if (cnt > 0) {
//				flag = true;
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return flag;
//	}


public boolean update(GalleryDTO dto) {
		boolean flag = false;
			int cnt = mybatis.update("gallery.update", dto);
			
			if(cnt>0) {
				flag = true;
			}
		return flag;
	}
	
	public boolean create(GalleryDTO dto) {
		boolean flag = false;
		
			int cnt = mybatis.insert("gallery.create", dto);
			if(cnt>0) flag = true;
	
		return flag;
	}
	
		
	public List<GalleryDTO> list(Map map) {

		return mybatis.selectList("gallery.list", map);
	}
	
	public int total(Map map) {
		
		return mybatis.selectOne("gallery.total", map);
	}
}
	
