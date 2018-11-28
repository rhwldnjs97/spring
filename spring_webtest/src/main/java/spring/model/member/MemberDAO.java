package spring.model.member;

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
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
		
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}

	public boolean create(MemberDTO dto) {
		boolean flag = false;

			int cnt = mybatis.insert("member.create", dto);

			if (cnt > 0) {
				flag = true;
			}
		
		return flag;
	}

	public boolean update(MemberDTO dto) {
		boolean flag = false;

			int cnt = mybatis.update("member.update", dto);

			if (cnt > 0) {
				flag= true;
			}
			return flag;
	}

	public boolean delete(String id) {
		boolean flag = false;

			int cnt = mybatis.delete("member.delete", id);

			if (cnt > 0) {
				flag = true;
			}
		return flag;
	}

	public MemberDTO read(String id) {
		return mybatis.selectOne("member.read", id); 
	}

	public List<MemberDTO> list(Map map) {
		return mybatis.selectList("member.list", map);
	}

	public int total(Map map) {
		return mybatis.selectOne("member.total", map);
	}

	public boolean duplicateId(String id) {
		boolean flag = false;

				int cnt = mybatis.selectOne("member.duplicateId", id);
				if (cnt > 0) {
					flag = true;
				}
				
		return flag;
	}

	public boolean duplicateEmail(String email) {
		boolean flag = false;

				int cnt = mybatis.selectOne("member.duplicateEmail", email);
				if (cnt > 0) {
					flag = true;
				}
		return flag;
	}

	public boolean updateFile(Map map) {
		boolean flag = false;
		
			int cnt = mybatis.update("member.updateFile", map);

			if (cnt > 0) {
				flag = true;
			}
		return flag;
	}

	public boolean updatePasswd(Map map) {
		boolean flag = false;
		
			int cnt = mybatis.update("member.updatePasswd", map);

			if (cnt > 0) {
				flag = true;
			}

		return flag;
	}

	public boolean passwdCheck(Map map) {
		boolean flag = false;	
			int cnt = mybatis.selectOne("passwdCheck", map);
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
//		String id = (String) map.get("id");
//		String mname = (String) map.get("mname");
//		String tel = (String) map.get("tel");
//		String email = (String) map.get("email");
//		String zipcode = (String) map.get("zipcode");
//		String address1 = (String) map.get("address1");
//		String address2 = (String) map.get("address2");
//		String job = (String) map.get("job");
//
//		StringBuffer sql = new StringBuffer();
//
//		sql.append(" update member ");
//		sql.append(" set mname = ?, tel = ?, email = ?, zipcode = ?, address1 = ?, address2 = ?, job = ? ");
//		sql.append(" where id = ? ");
//
//		try {
//			pstmt = con.prepareStatement(sql.toString());
//
//			pstmt.setString(1, mname);
//			pstmt.setString(2, tel);
//			pstmt.setString(3, email);
//			pstmt.setString(4, zipcode);
//			pstmt.setString(5, address1);
//			pstmt.setString(6, address2);
//			pstmt.setString(7, job);
//			pstmt.setString(8, id);
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

	public String getFname(String id) {
		return mybatis.selectOne("member.getFname", id);
	}

	public boolean loginCheck(Map map) {
		boolean flag = false;

				int cnt = mybatis.selectOne("member.loginCheck", map);
				if(cnt>0) {
					flag = true;
				}
		
		return flag;
	}
	
	public String getGrade(String id) {
		return mybatis.selectOne("member.getGrade", id);
	}

	public String getIdFind(Map map) {
		return mybatis.selectOne("member.getIdFind", map);
	}
	public String getPasswdFind(Map map) {
		return mybatis.selectOne("member.getPasswdFinde", map);
	}
}
