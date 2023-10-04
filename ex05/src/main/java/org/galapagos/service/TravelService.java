package org.galapagos.service;

import java.util.List;

import org.galapagos.criteria.Criteria;
import org.galapagos.domain.TravelVO;

public interface TravelService {

	public int getTotal(Criteria cri);

	public List<TravelVO> getList(Criteria cri);
	
	public List<TravelVO> getRandom(int count);

	public TravelVO get(Long no);

	public void register(TravelVO travel);

	public boolean modify(TravelVO travel);

	public boolean remove(Long no);

}
