package org.galapagos.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.galapagos.domain.BoardVO;


public interface BoardMapper {
	
	//@Select("select * from tbl_board")
	public List<BoardVO> getList();
}
