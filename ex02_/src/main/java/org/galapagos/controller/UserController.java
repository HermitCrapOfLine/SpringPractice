package org.galapagos.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.galapagos.domain.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")
@Log4j
public class UserController {
	
	@GetMapping("/join")
	public String joinGet() {
		return "user/join"; // foward
	}
	
	
	@PostMapping("/join")
	public String joinPost(UserDTO user)  {
		// DB insert 작업을 함 
		log.info(user);
		
		// "redirect: 이동할 url"
		// post처리를 했다면 반드시 redirect 처리를 해줘야한다
		// insert 혹은 delete하는 경우
		return "redirect:/user/join_result";
	}
	
	@GetMapping("/join_result")
	public String joinResult() {
		return "user/join_result";
	}
}
