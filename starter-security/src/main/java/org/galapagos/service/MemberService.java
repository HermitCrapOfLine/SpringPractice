package org.galapagos.service;

import org.galapagos.domain.MemberVO;

public interface MemberService {
	
	public MemberVO get(String username);
	
	public void resgister(MemberVO member);
}
