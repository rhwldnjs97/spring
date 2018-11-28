package spring.model.bbs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ReplyTest {

	public static void main(String[] args) {
		
		Resource resource = new ClassPathResource("daoTest.xml");
		
		BeanFactory factory = new XmlBeanFactory(resource);
		
		ReplyDAO dao = (ReplyDAO)factory.getBean("reply");
		
		
		create(dao);
		//read(dao);
		//update(dao);
		//delete(dao);
		//list(dao);
		//total(dao);
		//deleteAll(dao);
	}

	private static void deleteAll(ReplyDAO dao) {
		if(dao.deleteAll(3))
			p("성공");
		else
			p("실패");
	}

	private static void total(ReplyDAO dao) {
		Map map = new HashMap();
		map.put("bbsno", "3");
		System.out.println(dao.total(map));
		
		
	}

	private static void list(ReplyDAO dao) {
		Map map = new HashMap();
		map.put("bbsno", "3");
		map.put("sno", 1);
		map.put("eno", 10);
/*		p(dao.getList(map));*/
		
		List<ReplyDTO> list = dao.list(map);
		
		Iterator<ReplyDTO> iter = list.iterator();
		
		while(iter.hasNext()) {
			ReplyDTO dto = iter.next();
			p(dto);
			p("==========================");
		}
		
	}

/*	private static void p(List<ReplyDTO> list) {
		for(int i = 0; i<list.size(); i++) {
			ReplyDTO dto = new ReplyDTO();
			System.out.println("-----------------------");
			System.out.println(dto.getNum());
			System.out.println(dto.getName());
			System.out.println(dto.getSubject());
			System.out.println(dto.getRegdate());
			System.out.println(dto.getIndent());
			System.out.println(dto.getCount());
			System.out.println(dto.getFilename());
			System.out.println("-----------------------");
		}
		
	}
	*/
	

	private static void delete(ReplyDAO dao) {
		if(dao.delete(2))
			p("성공");
		else
			p("실패");
	}

	private static void update(ReplyDAO dao) {
		ReplyDTO dto = dao.read(1);
		dto.setContent("인생역전");
		
		if(dao.update(dto)) {
			p("썽공");
		} else {
			p("실패");
			
		}
	}	
	
	private static void read(ReplyDAO dao) {
		p(dao.read(1));
	}
	
	

	private static void p(ReplyDTO dto) {
		p("번호 : " + dto.getRnum());
		p("내용 : " + dto.getContent());
		p("날짜 : " + dto.getRegdate());
		p("아이디 : " + dto.getId());
		p("부모글번호 : " + dto.getBbsno());
	}

	private static void create(ReplyDAO dao) {
		ReplyDTO dto = new ReplyDTO();
		dto.setContent("3만원이나");
		dto.setId("user2");
		dto.setBbsno(3);
		if(dao.create(dto))
			p("등록 성공");
		else
			p("등록 실패");
	}


	private static void p(String string) {
		System.out.println(string);
		
	}



}
