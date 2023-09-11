package org.galapagos.controller;

import org.galapagos.domain.PracticeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/practice/")
public class PracticeController {
	
	@GetMapping("/prac01")
	public String prac01(PracticeDTO dto,
						@RequestParam("address") String address) {
		
		return "prac01";
	}
	
}
