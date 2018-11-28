package spring.model.memo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.db.webtest.DBClose;
import spring.db.webtest.DBOpen;

@Repository
public class MemoService{
		@Autowired
		private  MemoDAO dao;
		
		public boolean reply(MemoDTO dto) {
			boolean flag = false;
					
			try {
					
				Map map = new HashMap();
				map.put("grpno", dto.getGrpno());
				map.put("ansnum", dto.getAnsnum());
				
				dao.upAnsnum(map);
				
				flag = dao.replyCreate(dto);
								
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
								
			}finally {
				
			}
			
			return flag;
		}

}
