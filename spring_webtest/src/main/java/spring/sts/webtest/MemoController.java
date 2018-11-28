package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.bbs.BbsDTO;
import spring.model.memo.MemoDAO;
import spring.model.memo.MemoDTO;
import spring.model.memo.MemoService;
import spring.utility.webtest.utility;

@Controller
public class MemoController {

	@Autowired
	private MemoDAO dao;
	
	@Autowired
	private MemoService svc;
	
	@RequestMapping(value="/memo/reply", method=RequestMethod.POST)
	public String reply(int memono, HttpServletRequest request,
			MemoDTO dto, Model model) {
		
		model.addAttribute("word", request.getParameter("word"));
		model.addAttribute("col", request.getParameter("col"));
		model.addAttribute("nowPage", request.getParameter("nowPage"));
		if (svc.reply(dto))
			return "redirect:/memo/list";
		else
			return "/error/error";
		
		
	}
	
	@RequestMapping(value="/memo/reply", method=RequestMethod.GET)
	public String reply(int memono, Model model) {
		MemoDTO dto = dao.replyRead(memono);
		model.addAttribute("dto", dto);
		
		return "/memo/reply";
	}
	
	@RequestMapping(value="/memo/delete", method=RequestMethod.POST)
	public String delete(int memono, Model model, HttpServletRequest request) {
		model.addAttribute("word", request.getParameter("word"));
		model.addAttribute("col", request.getParameter("col"));
		model.addAttribute("nowPage", request.getParameter("nowPage"));
		
		if(dao.delete(memono))
			return "redirect:/memo/list";
		else
			return "/error/error";
		
	}
	
	@RequestMapping(value="/memo/delete", method=RequestMethod.GET)
	public String delete(int memono) {
		
		if(dao.checkRefnum(memono))
			return "/error/delError";
		else
			return "/memo/delete";
	}
	
	@RequestMapping(value="/memo/update", method=RequestMethod.POST)
	public String update(int memono, MemoDTO dto,
			Model model, HttpServletRequest request) {
		
		model.addAttribute("word", request.getParameter("word"));
		model.addAttribute("col", request.getParameter("col"));
		model.addAttribute("nowPage", request.getParameter("nowPage"));
		
		if(dao.update(dto))
			return "redirect:/memo/list";
		else
			return "/error/error";
		
				
	}
	
	@RequestMapping(value="/memo/update", method=RequestMethod.GET)
	public String update(int memono, Model model) {
		MemoDTO dto = dao.read(memono);
		model.addAttribute("dto", dto);
		return "/memo/update";		
	}
	
	@RequestMapping("/memo/read")
	public String read(Model model, int memono) {
		
		dao.updateviewcnt(memono);
		MemoDTO dto = dao.read(memono);
		
		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");
		
		dto.setContent(content);
		model.addAttribute("dto", dto);
		
		return "/memo/read";
	}
	
	@RequestMapping(value="/memo/create", method=RequestMethod.POST)
	public String create(MemoDTO dto, Model model) {
		
		if(dao.create(dto)) {
			return "redirect:/memo/list";
		} else {
			return "/error/error";
		}
		
	}
	
	@RequestMapping(value="/memo/create", method=RequestMethod.GET)
	public String create() {
		return "/memo/create";
	}
	
	@RequestMapping("/memo/list")
	public String list(HttpServletRequest request) {
		
		//검색 관련
				String col = utility.checkNull(request.getParameter("col"));
				String word = utility.checkNull(request.getParameter("word"));

				if(col.equals("total")){
					word="";	
				}
				
				// paging 관련
				
				int nowPage = 1;
				int recordPerPage = 10;
				if(request.getParameter("nowPage")!=null){
					nowPage = Integer.parseInt(request.getParameter("nowPage"));
				}
				
				//DB에서 가져올 순번을 생성
				
				int sno = ((nowPage-1)*recordPerPage)+1;
				int eno = nowPage * recordPerPage;
				
				Map map = new HashMap();
				map.put("col", col);
				map.put("word", word);
				map.put("sno", sno);
				map.put("eno", eno);
				
				List<MemoDTO> list = dao.list(map);
				int totalRecord = dao.total(map);
				
				String paging = utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
				
				request.setAttribute("list", list);
				request.setAttribute("paging", paging);
				request.setAttribute("col", col);
				request.setAttribute("word", word);
				request.setAttribute("nowPage", nowPage);
		
		return "/memo/list";
	}
}
