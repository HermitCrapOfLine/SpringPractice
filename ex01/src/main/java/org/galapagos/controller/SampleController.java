package org.galapagos.controller;

import java.util.ArrayList;
import java.util.Date;

import org.galapagos.domain.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/")
@Log4j
public class SampleController {

	@RequestMapping("")
	public void basic() {
		log.info("basic.........");
	}
	
	@RequestMapping (value="/basic",
			method= {RequestMethod.GET, RequestMethod.POST })
	public void basicGet() {
		log.info("basic get..........");
		// return "sample/basic" 
		// void 자료형 메소드는 Mapping 값이 view의 이름이 된다.
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get.........");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto,
						@RequestParam("address") String address) {
		log.info("" + dto);
		log.info("address: " +  address);
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name,
						@RequestParam("age") int age){
		log.info("name: " + name);
		log.info("age: " + age);
		
		return "ex02";
	}
	
	@GetMapping("/ex02List")
	public String ex02List (@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids: " + ids);
		
		return "ex02List";
	}
	
	@GetMapping("/ex04")
	public String ex04(Model model,
			@ModelAttribute("sample") SampleDTO dto,
			@ModelAttribute("page") int page) {
		
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		model.addAttribute("now", new Date());
		
		return "ex04";
	} 
}
