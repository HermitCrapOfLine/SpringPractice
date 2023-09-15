package org.galapagos.mapper;

import java.util.List;

import org.galapagos.config.RootConfig;
import org.galapagos.criteria.Criteria;
import org.galapagos.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j

public class BoardMapperTest {

	@Autowired
	private BoardMapper mapper;
	
//	@Test
	public void testGetList() {
		List<BoardVO> list = mapper.getList();
		for (BoardVO b : list) {
			log.info(b);
		}
	}

	
//	@Test
	public void testInsert() {
		
		for(int i = 0; i < 260; i++) {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 " + i);
		board.setContent("새로 작성하는 내용" + i);
		board.setWriter("newbie" + i%5);
		
		mapper.insert(board);
		}
		
//		log.info(board);

	}
	
//	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
		
	}
	
	
//	@Test
	public void testRead() {
		
		BoardVO board = mapper.read(5L);
		
		log.info(board);
	}
	
//	@Test
	public void testDelete() {
		log.info("DELETE COUNT: " + mapper.delete(3L));
	}

//	@Test
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
		board.setBno(5L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");
		
		int count = mapper.update(board);
		
		log.info("UPDATE COUNT: " + count);
		
	}
	
//	@Test
	public void testPaging() {
		
		Criteria cri = new Criteria(1, 5);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		for (BoardVO board : list) {
			log.info(board);
		}
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		
		cri.setKeyword("이해");
		cri.setType("T");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		int count = mapper.getTotalCount(cri);
		for(BoardVO board : list) {
			log.info(board);
		}
	}
	
	
}
