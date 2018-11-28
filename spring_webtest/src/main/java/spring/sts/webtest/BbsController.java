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

import spring.model.bbs.BbsDAO;
import spring.model.bbs.BbsDTO;
import spring.model.bbs.BbsService;
import spring.model.bbs.ReplyDAO;
import spring.model.bbs.ReplyDTO;
import spring.utility.webtest.utility;

@Controller
public class BbsController {

	@Autowired
	private BbsDAO dao;

	@Autowired
	private ReplyDAO rdao;

	@Autowired
	private BbsService mgr;

	@Autowired
	private BbsService service; // 추가

	@RequestMapping("/bbs/rdelete")
	public String rdelete(ReplyDTO dto, int rnum, Model model, String nowPage, String col, String word, int nPage) {

		int total = rdao.total(dto.getBbsno());
		int totalPage = (int) (Math.ceil((double) total / 3));

		if (rdao.delete(rnum)) {
			if (nPage != 1 && nPage == totalPage && total % 3 == 1) {
				nPage = nPage - 1;
			}
			model.addAttribute("bbsno", dto.getBbsno());
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nPage", nPage);
			return "redirect:/bbs/read";
		} else {
			return "/error/error";
		}
	}

	@RequestMapping("/bbs/rupdate")
	public String rupdate(ReplyDTO dto, Model model, String nowPage, String col, String word, String nPage) {
		if (rdao.update(dto)) {
			model.addAttribute("bbsno", dto.getBbsno());
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nPage", nPage);
			return "redirect:/bbs/read";
		} else {
			return "/error/error";
		}
	}

	@RequestMapping("/bbs/rcreate")
	public String rcreate(ReplyDTO dto, Model model, String nowPage, String col, String word) {
		if (rdao.create(dto)) {
			model.addAttribute("bbsno", dto.getBbsno());
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			return "redirect:/bbs/read";
		} else {
			return "/error/error";
		}
	}

	@RequestMapping(value = "/bbs/delete", method = RequestMethod.POST)
	public String delete(int bbsno, Model model, String oldfile, String passwd, HttpServletRequest request) {

		String upDir = request.getRealPath("/bbs/storage");

		Map map = new HashMap();
		map.put("bbsno", bbsno);
		map.put("passwd", passwd);

		boolean pflag = dao.passCheck(map);

		String str = "redirect:/bbs/list";

		if (pflag) {
			try {
				mgr.delete(bbsno);
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				utility.deleteFile(upDir, oldfile);
				return str;
			} catch (Exception e) {
				e.printStackTrace();
				return "/error/error";
			}
		} else {
			return "/error/passwdError";
		}
		/*
		 * } else if (!pflag) { str = "/error/passwdError"; } else { str =
		 * "/error/error"; }
		 */

	}

	@RequestMapping(value = "/bbs/delete", method = RequestMethod.GET)
	public String delete(int bbsno) {

		boolean flag = dao.checkRefnum(bbsno);

		if (flag)
			return "/error/delError";
		else
			return "/bbs/delete";
	}

	@RequestMapping(value = "/bbs/update", method = RequestMethod.POST)
	public String update(BbsDTO dto, Model model, String oldfile, HttpServletRequest request) {
		String upDir = request.getRealPath("/bbs/storage");

		Map map = new HashMap();
		map.put("bbsno", dto.getBbsno());
		map.put("passwd", dto.getPasswd());

		boolean pflag = dao.passCheck(map);

		boolean flag = false;
		int filesize = (int) dto.getFilenameMF().getSize();
		String filename = "";
		if (pflag) {
			if (filesize > 0) {
				if (oldfile != null) {
					utility.deleteFile(upDir, oldfile);
				}
				filename = utility.saveFileSpring(dto.getFilenameMF(), upDir);
			}

			dto.setFilename(filename);
			dto.setFilesize(filesize);

			flag = dao.update(dto);
		}
		String str = "redirect:/bbs/list";
		if (!pflag)
			str = "/error/passwdError";
		else if (!flag)
			str = "/error/error";

		model.addAttribute("col", request.getParameter("col"));
		model.addAttribute("word", request.getParameter("word"));
		model.addAttribute("nowPage", request.getParameter("nowPage"));

		return str;
	}

	@RequestMapping(value = "/bbs/update", method = RequestMethod.GET)
	public String update(int bbsno, Model model) {

		BbsDTO dto = dao.read(bbsno);
		model.addAttribute("dto", dto);

		return "/bbs/update";
	}

	@RequestMapping(value = "/bbs/reply", method = RequestMethod.POST)
	public String reply(BbsDTO dto, HttpServletRequest request) {

		String basePath = request.getRealPath("/bbs/storage");

		int filesize = (int) dto.getFilenameMF().getSize();
		String filename = "";
		if (filesize > 0)
			filename = utility.saveFileSpring(dto.getFilenameMF(), basePath);

		dto.setFilename(filename);
		dto.setFilesize(filesize);

		try {
			mgr.reply(dto);
		
			return "redirect:/bbs/list";
		}catch(Exception e) {
			e.printStackTrace();
			utility.deleteFile(basePath, dto.getFilename());
			return "/error/error";
		}
	}

	@RequestMapping(value = "/bbs/reply", method = RequestMethod.GET)
	public String reply(int bbsno, Model model) {

		BbsDTO dto = dao.replyRead(bbsno);
		model.addAttribute("dto", dto);

		return "/bbs/reply";
	}

	@RequestMapping("/bbs/read")
	public String read(int bbsno, Model model, HttpServletRequest request) {
		dao.updateviewcnt(bbsno);
		BbsDTO dto = dao.read(bbsno);

		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");

		dto.setContent(content);

		model.addAttribute("dto", dto);

		/* 댓글처리 */
		String url = "read";// 댓글 페이지 매개변수
		int nPage = 1;
		if (request.getParameter("nPage") != null) {
			nPage = Integer.parseInt(request.getParameter("nPage"));
		}

		int recordPerPage = 3;
		int sno = ((nPage - 1) * recordPerPage) + 1;
		int eno = nPage * recordPerPage;

		Map map = new HashMap();
		map.put("nPage", nPage);
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("bbsno", bbsno);

		List<ReplyDTO> rlist = rdao.list(map);
		int total = rdao.total(bbsno);

		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		String col = request.getParameter("col");
		String word = request.getParameter("word");

		String paging = utility.rpaging(total, nowPage, recordPerPage, col, word, nPage, url, bbsno);

		model.addAttribute("rlist", rlist);
		model.addAttribute("nPage", nPage);
		model.addAttribute("paging", paging);
		return "/bbs/read";
	}

	@RequestMapping(value = "/bbs/create", method = RequestMethod.POST)
	public String create(BbsDTO dto, HttpServletRequest request) {

		String upDir = request.getRealPath("/bbs/storage");

		int filesize = (int) dto.getFilenameMF().getSize();
		// Autowired로 객체 생성시 new로 해당 객체를 다시 생성하면 안됨.
		String filename = "";
		if (filesize > 0)
			filename = utility.saveFileSpring(dto.getFilenameMF(), upDir);
		dto.setFilesize(filesize);
		dto.setFilename(filename);

		boolean flag = dao.create(dto);

		if (flag) {
			return "redirect:/bbs/list";
		} else {
			if (!filename.equals("")) {
				utility.deleteFile(upDir, dto.getFilename());
			}
			return "/error/error";
		}

	}

	@RequestMapping(value = "/bbs/create", method = RequestMethod.GET)
	public String create() {

		return "/bbs/create";
	}

	@RequestMapping("/bbs/list")
	public String list(HttpServletRequest request, Model model) {

		String col = utility.checkNull(request.getParameter("col"));
		String word = utility.checkNull(request.getParameter("word"));

		if (col.equals("total")) {
			word = "";
		}

		int nowPage = 1;
		int recordPerPage = 5;

		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage")); // 위험함! numberformatException 가능성
		}

		int sno = ((nowPage - 1) * recordPerPage) + 1; // 공식임 ㅎ
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		List<BbsDTO> list = dao.list(map);
		// 전체 레코드 개수(col, word)
		int totalRecord = dao.total(map);

		String paging = utility.paging3(totalRecord, nowPage, recordPerPage, col, word);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("rdao", rdao);

		return "/bbs/list";
	}

}
