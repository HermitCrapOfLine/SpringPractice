package org.galapagos.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.galapagos.domain.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/test")
@Log4j
public class TestController {
	private int[] years = {2000, 2001, 2002, 2003}; // 메서드를 사용하지 않은경우
	
	private String[] hobbies = {"테니스", "육상", "수영"};
	
	private String[] genders = {"남자", "여자"}; 
	
	@ModelAttribute("years")
	public List<Integer> years() { // 자동으로 메서드 호출됨, 리턴값이 모델에 추가됨
		List<Integer> years = new ArrayList<>();
		
		for (int i = 1950; i <= 2023; i++) {
			years.add(i);
		}
		
		return years;
	}
	
	
	@GetMapping("/join")
	public void join(
			@ModelAttribute("member") MemberVO member, Model model
			) {
		//model.addAttribute("years", years);
		model.addAttribute("genders", genders);
	}
	
	@PostMapping("/join")
	public String join(
				@Valid
				@ModelAttribute("member") MemberVO member,
				Errors errors, Model model) {
		
		if(!member.getPassword().equals(member.getPassword2())) {
			errors.rejectValue("password2", "비밀번호 확인 에러",
								"비밀번호가 일치하지 않습니다.");
		}
		
		
		if(errors.hasErrors()) {
			model.addAttribute("genders", genders);
		
			return "test/join";		
		}
		// return "test/join 의미
		// forwarding --> form을 다시 입력하도록 한다.(이전 값으로 복원)
		// 해당 기법을 사용하려면 GET에서도 모델 객체를 넘겨주어야한다.
		
		
		
		// DB처리 작업
		return "redirect:/";
	}
	
	@ModelAttribute("roles")
	public Map<String, String> getRoles() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("ROLE_ADMIN", "Admin");
		map.put("ROLE_MANAGER", "관리자");
		map.put("ROLE_MEMBER", "일반회원");
		
		return map;
	}
	
	@ModelAttribute("hobbies")
	public Map<String, String> getHobbies() {
		Map<String, String> hobbies = new LinkedHashMap<>();
		hobbies.put("TENNIS", "마라톤");
		hobbies.put("SWIMMING", "수영");
		hobbies.put("CYCLE", "사이클");
		
		return hobbies;
		
	}
	

}
