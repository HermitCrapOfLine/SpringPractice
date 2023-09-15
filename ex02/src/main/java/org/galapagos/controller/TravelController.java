package org.galapagos.controller;

import org.galapagos.criteria.Criteria;
import org.galapagos.domain.BoardVO;
import org.galapagos.domain.PageDTO;
import org.galapagos.domain.TravelVO;
import org.galapagos.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/travel")

public class TravelController {

	@Autowired
	private TravelService service;
	
	@GetMapping("/list")
	public void list(@ModelAttribute("cri")	Criteria cri,
			Model model) {

		int total = service.getTotal(cri);
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, total)); // 임의로 123 요청
		
	}

	@GetMapping({ "/get", "/modify" })
	public void get(
			@RequestParam("no") Long no,
			@ModelAttribute("cri") Criteria cir,
			Model model) {
		log.info("/get or modify");
		model.addAttribute("travel", service.get(no));
	}
	
	
	@PostMapping("/modify")
	public String modify(TravelVO travel, @ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) {
		
		service.modify(travel);
		
		return "redirect:" + cri.getLink("/travel/get") +
				"&no=" + travel.getNo();
	}
	
	@GetMapping("/register")
	public void register() {
	}

	@PostMapping("/register")
	public String register(TravelVO travel, RedirectAttributes rttr) {
		service.register(travel);
		return "redirect:/travel/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("no") Long no, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
		
		service.remove(no);
		return "redirect:/travel/list" + cri.getLink();
	}
}
