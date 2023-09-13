package org.galapagos.mapper;

import java.util.List;

import org.galapagos.criteria.Criteria;
import org.galapagos.domain.BoardVO;


public interface BoardMapper {
	
	//@Select("select * from tbl_board")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board); // insert 후에 bno를 알 수 없다
	
	public void insertSelectKey(BoardVO board); // insert 후에 bno를 추출한다.

	public BoardVO read(Long bno);
	
	public int delete(Long bno);

	public int update(BoardVO board);
	
	public int getTotalCount(Criteria cri);
}
