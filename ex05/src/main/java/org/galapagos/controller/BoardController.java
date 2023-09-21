package org.galapagos.controller;

import java.util.LinkedHashMap;

import java.util.Map;

import javax.validation.Valid;

import org.galapagos.criteria.Criteria;
import org.galapagos.domain.BoardVO;
import org.galapagos.domain.PageDTO;
import org.galapagos.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board")

public class BoardController {

	@Autowired
	private BoardService service;

	@ModelAttribute("searchTypes")
	public Map<String, String> searchTypes() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "-- 검색대상선택 --");
		map.put("T", "제목");
		map.put("C", "내용");
		map.put("W", "작성자");
		map.put("TC", "제목+내용");
		map.put("TW", "제목+작성자");
		map.put("TWC", "제목+내용+작성자");
		
		
		return map;
	}
	
	
	
	@GetMapping("/list")
	public void list(@ModelAttribute("cri") Criteria cri, Model model) {

		int total = service.getTotal(cri);
		log.info("total: " + total);

		// model.addAttribute("pageMaker", new PageDTO(cri, 274)); // 임의로 123 요청
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, total)); // 임의로 123 요청
		
	}

	@GetMapping("/register")
	public void register(@ModelAttribute("board") BoardVO board) {
		log.info("register");
	}

	@PostMapping("/register")
	public String register(	@Valid
							@ModelAttribute("board") BoardVO board, 
							Errors errors,
							RedirectAttributes rttr) {
		if(errors.hasErrors()) {
			return "board/register";
		}
		
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}

	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cir, Model model) {
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("/modify")
	public String modify(@ModelAttribute("board") BoardVO board,
						Errors errors,
						@ModelAttribute("cri") Criteria cri,
						RedirectAttributes rttr) {
		
		log.info("modify:" + board);
		
		if(errors.hasErrors()) {
			return "board/modify";
		}
		
		if (service.modify(board)) {
			// Flash --> 1회성
			rttr.addFlashAttribute("result", "success");
//			rttr.addAttribute("bno", board.getBno());
//			rttr.addAttribute("pageNum", cri.getPageNum());
//			rttr.addAttribute("amount", cri.getAmount());
//			rttr.addAttribute("type", cri.getType());
//			rttr.addAttribute("keyword", cri.getKeyword());
		}
		return "redirect:" + cri.getLinkWithBno("/board/get", board.getBno());
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
		log.info("remove..." + bno);
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
		}
		return "redirect:" + cri.getLink("/board/list");
	}

}