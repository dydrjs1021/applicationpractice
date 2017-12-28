package kr.or.nextit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.nextit.board.model.Board;

public interface BoardService {
		
	public int getBoardCount(Map<String, Object> paramMap ) throws Exception;
	
	public List<Board> getBoardList(Map<String, Object> paramMap) throws Exception;
	
	public Board getBoard(int bo_seq_no) throws Exception;
	
	public int insertBoard(Board board) throws Exception;
	
	public int updateBoard(Board board) throws Exception;
	
	public int deleteBoard(Map<String, Object> paramMap) throws Exception;
	

}
