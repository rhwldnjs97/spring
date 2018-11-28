package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.model.gallery.GalleryDAO;
import spring.model.gallery.GalleryDTO;
import spring.utility.webtest.utility;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryDAO dao;
	
	@RequestMapping(value="/gallery/updatePasswd", method=RequestMethod.POST)
	public String updatePasswd(int gno, String pw, String newpw) {
		
		Map map = new HashMap();
		map.put("gno", gno);
		map.put("pw", pw);
		map.put("newpw", newpw);
		
		if(dao.passwdCheck(map))
			if(dao.updatePasswd(map))
				return "redirect:/gallery/list";
			else
				return "/error/error";
		else
			return "/error/passwdError";
		
	}
	
	@RequestMapping(value="/gallery/updatePasswd", method=RequestMethod.GET)
	public String updatePasswd(int gno, Model model) {
		
		model.addAttribute("gno", gno);
		
		return "/gallery/updatePasswd";
	}
	
	@RequestMapping(value="/gallery/update", method=RequestMethod.POST)
	public String update(GalleryDTO dto, String pw,
			 Model model, String oldfile, HttpServletRequest request) {
		
		String upDir = request.getRealPath("/gallery/storage");
		String fname = null;
		int filesize = (int) dto.getFnameMF().getSize();
		if (filesize > 0) {
			if(oldfile!=null && oldfile.equals("noimage.jpg"));
				utility.deleteFile(upDir, oldfile);
			fname = utility.saveFileSpring(dto.getFnameMF(), upDir);
		} else {
			fname = oldfile;
		}
		
		dto.setFname(fname);
		
		Map map = new HashMap();
		map.put("gno", dto.getGno());
		map.put("pw", pw);
		
		boolean flag = false;
		boolean pflag = dao.passwdCheck(map);
		if(pflag) {
			flag = dao.update(dto);
		} else {
			return "/error/passwdError";
		}
		
		if(flag) {
			return "redirect:/gallery/list";
		} else {
			return "/error/error";
		}
	}	
	
	@RequestMapping(value="/gallery/update", method=RequestMethod.GET)
	public String update(int gno, Model model) {
	
	GalleryDTO dto = dao.read(gno);
	
	model.addAttribute("dto", dto);
	
	return "/gallery/update";	
	}	
	
	@RequestMapping(value="/gallery/delete", method=RequestMethod.POST)
	public String delete(int gno, String pw,
			String oldfile, HttpServletRequest request) {
		
		String upDir = request.getRealPath("/gallery/storage");
		
		Map map = new HashMap();
		map.put("gno", gno);
		map.put("pw", pw);
		
		boolean pflag= dao.passwdCheck(map);
		
		if(pflag){
			if (dao.delete(gno)) {
				if(!oldfile.equals("noimage.jpg")) {
					utility.deleteFile(upDir, oldfile);
					return "redirect:/gallery/list";
				} else {
					return "redirect:/gallery/list";
				}
			} else {
				return "/error/error";
			}
		} else {
			return "/error/passwdError";
		}
		
		
	}
	
	@RequestMapping(value="/gallery/delete", method=RequestMethod.GET)
	public String delete(int gno, HttpServletRequest request) {
		GalleryDTO dto = dao.read(gno);
		request.setAttribute("gno", gno);
		request.setAttribute("dto", dto);
		return "/gallery/delete";
	}
	
	@RequestMapping(value="/gallery/create", method=RequestMethod.POST)
	public String create(GalleryDTO dto, HttpServletRequest request) {
		
		String basePath = request.getRealPath("/gallery/storage");
		int size = (int)dto.getFnameMF().getSize();
		String fname = null;
		if(size>0){
			fname = utility.saveFileSpring(dto.getFnameMF(), basePath);		
		} else {
			fname = "noimage.jpg";
		}
		
		dto.setFname(fname);
		
		boolean flag = dao.create(dto);
		
		if(flag) {
			return "redirect:/gallery/list";
		} else {
			if(!fname.equals("") && !fname.equals("noimage.jpg")) {
				utility.deleteFile(basePath, fname);
				return "/error/error";
			}
			return "/error/error";
		}
		
		
	}
	
	@RequestMapping(value="/gallery/create", method=RequestMethod.GET)
	public String create() {
		
		return "/gallery/create";
	}
	
	@RequestMapping("/gallery/read")
	public String read(int gno, HttpServletRequest request) {
		
		dao.upViewCnt(gno);
		
		GalleryDTO dto = dao.read(gno);
		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");		
		dto.setContent(content);
		
		List list = dao.readList(gno);
		int[] lGno = (int[]) list.get(0);
		String[] lFname = (String[]) list.get(1);
		
		request.setAttribute("dto", dto);
		request.setAttribute("list", list);
		request.setAttribute("lGno", lGno);
		request.setAttribute("lFname", lFname);
		
		return "/gallery/read";
	}
	
	@RequestMapping("/gallery/list")
	public String list(HttpServletRequest request) {
		
		//검색관련처리
				String col = utility.checkNull(request.getParameter("col"));
				String word = utility.checkNull(request.getParameter("word"));

				if (col.equals("total"))
					word = "";

				//paging관련
				int nowPage = 1;
				int recordPerPage = 5;

				if (request.getParameter("nowPage") != null) {
					nowPage = Integer.parseInt(request.getParameter("nowPage"));
				}

				//DB에서 가져올 레코드의 순번
				int sno = ((nowPage - 1) * recordPerPage) + 1;
				int eno = nowPage * recordPerPage;

				Map map = new HashMap();
				map.put("col", col);
				map.put("word", word);
				map.put("sno", sno);
				map.put("eno", eno);

				int totalRecord = dao.total(map);
				List<GalleryDTO> list = dao.list(map);

				String paging = utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
				
				request.setAttribute("list", list);
				request.setAttribute("col", col);
				request.setAttribute("word", word);
				request.setAttribute("nowPage", nowPage);
				request.setAttribute("paging", paging);
		
		return "/gallery/list";
	}
}
