package org.galapagos.service;

import java.security.Principal;
import java.util.List;

import org.galapagos.criteria.Criteria;
import org.galapagos.domain.TravelVO;
import org.galapagos.domain.kakao.local.LocalResult;
import org.galapagos.mapper.TravelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import retrofit2.Call;
import retrofit2.Response;

@Log4j
@Service
public class TravelServiceImpl implements TravelService {

	@Autowired
	private TravelMapper mapper;

	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<TravelVO> getList(Criteria cri, Principal principal) {
		List<TravelVO> list = mapper.getList(cri);
		if (principal != null) {
			List<Long> hearts = mapper.getHeartsList(principal.getName());
			for (TravelVO travel : list) {
				travel.setMyHeart(hearts.contains(travel.getNo()));
			}
		}
		return list;
	}

	@Override
	public TravelVO get(Long no, Principal principal) {
		TravelVO travel = mapper.read(no);
		if (principal != null) {
			List<Long> hearts = mapper.getHeartsList(principal.getName());
			travel.setMyHeart(hearts.contains(travel.getNo()));
		}
		
		// 주변 검색
		String query = travel.getTitle();
		KakaoService service = KakaoService.getService();
		Call<LocalResult> call = service.searchLocal(query, 10, 1);
		Response<LocalResult> res;
		try {
			res = call.execute();
			if(res.isSuccessful()) {
				LocalResult result = res.body();
				travel.setLocals(result.getLocals());
			} else {
				log.error("호출 실패 ===> " + res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return travel;
	}

	@Override
	public void register(TravelVO travel) {
		// 첨부파일 업로드

		mapper.insert(travel);
	}

	@Override
	public boolean modify(TravelVO travel) {
		return mapper.update(travel) == 1;
	}

	@Override
	public boolean remove(Long no) {
		return mapper.delete(no) == 1;
	}

	@Override
	public List<TravelVO> getRandom(int count) {
		return mapper.getRandom(count);
	}

}
