package org.galapagos.mapper;

import static org.junit.Assert.fail;

import java.util.List;

import org.galapagos.config.RootConfig;
import org.galapagos.criteria.Criteria;
import org.galapagos.domain.TravelVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j

public class TravelMapperTest {
	
	
	@Autowired
	private TravelMapper mapper;

//	@Test
	public void testGetTotalCount() {
		Criteria cri = new Criteria();
		
		int total = mapper.getTotalCount(cri);
		log.info("total: " + total);
		
		cri.setType("RTD");
		cri.setKeyword("해수욕장");
		total = mapper.getTotalCount(cri);
		log.info("total: " + total);
	
	}

//	@Test
	public void testGetList() {
		Criteria cri = new Criteria();
		
		List<TravelVO> list = mapper.getList(cri);
		for(TravelVO travel: list) {
			log.info(travel);
		}
		
		// 검색
		cri.setType("RTD");
		cri.setKeyword("해수욕장");
		list = mapper.getList(cri);
		for(TravelVO travel : list) {
			log.info(travel);
		}
			
		}
	

//	@Test
	public void testInsert() {
		TravelVO travel = new TravelVO();
		travel.setRegion("수도권");
		travel.setTitle("코엑스");
		travel.setDescription("코엑스에는 다양한 즐길거리가 있어요");
		travel.setAddress("네이버 검색");
		travel.setPhone("02-3432-9342");
		
		mapper.insert(travel);
		log.info(travel);
	
	}

//	@Test
	public void testRead() {
		TravelVO travel = mapper.read(10L);
		log.info(travel);
		
	}

	@Test
	public void testDelete() {
		mapper.delete(113L);
	}

//	@Test
	public void testUpdate() {
		TravelVO travel = mapper.read(116L);
		travel.setTitle("신사옥 주꾸미");
		travel.setDescription("여기 주꾸미 존맛탱이에요우..");
		travel.setAddress("부산광역시 사상구 터미널 근처");
		travel.setPhone("051-392-3473");
		
		mapper.update(travel);
		log.info(travel);
	}
	
	

}
