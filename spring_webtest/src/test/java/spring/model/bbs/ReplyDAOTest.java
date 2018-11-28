package spring.model.bbs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ReplyDAOTest {

	private static ReplyDAO dao;
	private static BeanFactory beans;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
Resource resource = new ClassPathResource("daoTest.xml");
		
		beans = new XmlBeanFactory(resource);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		dao = (ReplyDAO)beans.getBean("reply");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	@Ignore
	public void testRcount() {
		int bbsno=3;
		int cnt = dao.rcount(bbsno);
		assertEquals(cnt, 3);
	}

	@Test
	@Ignore
	public void testCreate() {
		ReplyDTO dto = new ReplyDTO();
		dto.setContent("3만원이나");
		dto.setId("user2");
		dto.setBbsno(16);
		assertTrue(dao.create(dto));
	}

	@Test
	@Ignore
	public void testList() {
		Map map = new HashMap();
		map.put("bbsno", 3);
		map.put("sno", 1);
		map.put("eno", 10);
		
		List<ReplyDTO> list = dao.list(map);
		
		assertEquals(list.size(), 3);
	}

	@Test
	@Ignore
	public void testUpdate() {
		ReplyDTO dto = dao.read(7);
		dto.setContent("인생역전");
		assertTrue(dao.update(dto));
	}

	@Test
	public void testDelete() {
		int rnum=2;
		assertTrue(dao.delete(rnum));
	}

	@Test
	public void testDeleteAll() {
		int bbsno = 16;
		assertTrue(dao.deleteAll(bbsno));
	}

	@Test
	@Ignore
	public void testRead() {
		ReplyDTO dto = dao.read(7);
		assertNotNull(dto);
	}

	@Test
	@Ignore
	public void testTotal() {
		int bbsno = 3;
		int total = dao.total(bbsno);
		assertEquals(total, 3);
	}

}
