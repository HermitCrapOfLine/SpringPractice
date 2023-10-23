package org.galapagos.controller;

import org.galapagos.domain.BasketballVO;
import org.galapagos.domain.TitanicVO;
import org.galapagos.service.BasketballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basketball")
public class BasketballController {
	
	
	
	@Autowired
	BasketballService service;
	
	
	// 처음 화면이 띄워질 때 모델 어트리뷰트로 겟 매핑 메서드가 들어감.
	@GetMapping("/predict")
	public void getPredic(TitanicVO vo) {
		
	}
	
	@PostMapping("/predict")
	public void postPredic(BasketballVO vo, Model model) {
		String result = service.predict(vo);
		model.addAttribute("result", result);
	}
	
}
