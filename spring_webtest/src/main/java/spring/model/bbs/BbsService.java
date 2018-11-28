package spring.model.bbs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.db.webtest.DBClose;
import spring.db.webtest.DBOpen;

@Service
public class BbsService {
	@Autowired
	private BbsDAO dao;

	@Autowired
	private ReplyDAO rdao;

	public void delete(int bbsno) throws Exception {

		// 댓글 모두 삭제
		rdao.deleteAll(bbsno);
		dao.delete(bbsno);
	}

	public void reply(BbsDTO dto) throws Exception {

		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum", dto.getAnsnum());

		dao.upAnsnum(map);
		dao.replyCreate(dto);

	}
}
