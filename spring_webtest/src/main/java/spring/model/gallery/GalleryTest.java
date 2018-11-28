package spring.model.gallery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class GalleryTest {

	public static void main(String[] args) {
		
		Resource resource = new ClassPathResource("daoTest.xml");
		
		BeanFactory factory = new XmlBeanFactory(resource);
		
		GalleryDAO dao = (GalleryDAO)factory.getBean("gallery");
		
		
		//delete(dao);
//		checkRefnum(dao);
		//create(dao);
		//replyCreate(dao);
		//read(dao);
		//update(dao);
		//delete(dao);
//		list(dao);
		//total(dao);
		//increaseCnt(dao);
		//checkPasswd(dao);
//		readReply(dao);
		//upAnsnum(dao);
		readList(dao);
	}

private static void readList(GalleryDAO dao) {
		List list = dao.readList(3);
		
		
		
	}

//private static void readReply(GalleryDAO dao) {
//		GalleryDTO dto = dao.replyRead(512);
//		p("Galleryno:"+dto.getGalleryno());
//		p("Galleryno:"+dto.getTitle());
//		p("Galleryno:"+dto.getGrpno());
//		p("Galleryno:"+dto.getIndent());
//		p("Galleryno:"+dto.getAnsnum());
//		
//	}

//private static void upAnsnum(GalleryDAO dao) {
//	Map map = new HashMap();
//	map.put("grpno", 6);
//	map.put("ansnum", 2);
//	dao.upAnsnum(map);
//		
//	}
//
//private static void list(GalleryDAO dao) {
//		List list = new ArrayList();
//		
//		Map map = new HashMap();
//		map.put("col", "");
//		map.put("word", "");
//		map.put("sno", 1);
//		map.put("eno", 10);
//		list = dao.list(map);
//		GalleryDTO dto = new GalleryDTO();
//		for(int i = 0; i<list.size(); ++i) {
//			dto=(GalleryDTO)list.get(i);
//			p("-----------------------");
//			p(dto);
//		}
//		
//	}
//
//private static void total(GalleryDAO dao) {
//	Map map = new HashMap();
//	map.put("col", "title");
//	map.put("word", "1");
//	int total = dao.total(map);
//	System.out.println(total);
//		
//	}
//
//private static void delete(GalleryDAO dao) {
//		if(dao.delete(10))
//			p("삭제완료");
//		else
//			p("삭제불가");
//		
//	}
//
//private static void checkRefnum(GalleryDAO dao) {
//		if(dao.checkRefnum(1))
//			p("존재");
//		else
//			p("없음");
//		
//	}

//	private static void readReply(GalleryDAO dao) {
//		GalleryDTO dto= dao.replyRead(1);
//		p("번호" + dto.getGalleryno());
//		p("ref" + dto.getGrpno());
//		p("indent" + dto.getIndent());
//		p("ansnum" + dto.getAnsnum());
//	}
//	private static void checkPasswd(GalleryDAO dao) {
//		Map map = new HashMap();
//		map.put("num", 1);
//		map.put("passwd", "1234");
//		
//		if(dao.passCheck(map))
//			p("비밀번호 일치");
//		else
//			p("비밀번호 불일치");
//	}
//
//
//	private static void increaseCnt(GalleryDAO dao) {
//		dao.viewCount(1);
//		GalleryDTO dto = dao.read(1);
//		System.out.println("조회수 : "+dto.getCount());
//	}
//
//	private static void total(GalleryDAO dao) {
//		Map map = new HashMap();
//		map.put("word", "");
//		map.put("col", "name");
//		System.out.println(dao.getTotal(map));
//		
//		
//	}
//
//	private static void list(GalleryDAO dao) {
//		Map map = new HashMap();
//		map.put("word", "");
//		map.put("col", "wname");
//		map.put("sno", 1);
//		map.put("eno", 10);
///*		p(dao.getList(map));*/
//		
//		List<GalleryDTO> list = dao.list(map);
//		
//		Iterator<GalleryDTO> iter = list.iterator();
//		
//		while(iter.hasNext()) {
//			GalleryDTO dto = iter.next();
//			p(dto);
//			p("==========================");
//		}
//		
//	}
//
///*	private static void p(List<GalleryDTO> list) {
//		for(int i = 0; i<list.size(); i++) {
//			GalleryDTO dto = new GalleryDTO();
//			System.out.println("-----------------------");
//			System.out.println(dto.getNum());
//			System.out.println(dto.getName());
//			System.out.println(dto.getSubject());
//			System.out.println(dto.getRegdate());
//			System.out.println(dto.getIndent());
//			System.out.println(dto.getCount());
//			System.out.println(dto.getFilename());
//			System.out.println("-----------------------");
//		}
//		
//	}
//	*/
//	
//
//	private static void delete(GalleryDAO dao) {
//		if(dao.delete(2))
//			p("성공");
//		else
//			p("실패");
//	}
//
//	private static void update(GalleryDAO dao) {
//		GalleryDTO dto = new GalleryDTO();
//		dto.setName("임사장");
//		dto.setSubject("장미빛 인생");
//		dto.setContent("인생역전");
//		dto.setFilename("sdf.dc");
//		dto.setFilesize(10);
//		dto.setNum(1);
//		
//		if(dao.update(dto)) {
//			p("썽공");
//		} else {
//			p("실패");
//			
//		}
//	}	
//	
//	private static void read(GalleryDAO dao) {
//		p(dao.read(1));
//	}
//	
//	
//
//	private static void p(GalleryDTO dto) {
//		System.out.println(dto.getGalleryno());
//		System.out.println(dto.getWname());
//		System.out.println(dto.getTitle());
//		System.out.println(dto.getContent());
//		System.out.println(dto.getWdate());
//		System.out.println(dto.getViewcnt());
//		System.out.println(dto.getFilename());
//		System.out.println(dto.getFilesize());
//		
//	}
//
//	private static void create(GalleryDAO dao) {
//		GalleryDTO dto = new GalleryDTO();
//		dto.setWname("오사장");
//		dto.setTitle("떼 돈 벌다");
//		dto.setContent("100만원이나");
//		dto.setPasswd("1234");
//		dto.setFilename("ddfd.cdc");
//		dto.setFilesize(30);
//		if(dao.create(dto))
//			p("등록 성공");
//		else
//			p("등록 실패");
//	}
//	private static void replyCreate(GalleryDAO dao) {
//		GalleryDTO dto = dao.replyRead(1);
//		dto.setWname("오사장");
//		dto.setTitle("떼 돈 벌다");
//		dto.setContent("100만원이나");
//		dto.setPasswd("1234");
//		dto.setFilename("ddfd.cdc");
//		dto.setFilesize(30);
//		
//		if(dao.replyCreate(dto))
//			p("등록 성공");
//		else
//			p("등록 실패");
//	}


	private static void p(String string) {
		System.out.println(string);
		
	}
//	private static void p(GalleryDTO dto) {
//		p("번호:"+dto.getGalleryno());
//		p("제목:"+dto.getTitle());
//		p("내용:"+dto.getContent());
//		p("날짜:"+dto.getWdate());
//		p("조회수:"+dto.getViewcnt());
//		
//		
//	}
//


}
