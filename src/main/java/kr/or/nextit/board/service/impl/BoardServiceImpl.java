package kr.or.nextit.board.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.nextit.board.mapper.BoardMapper;
import kr.or.nextit.board.model.Board;
import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.mybatis.MybatisSqlSessionFactory;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;

	SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
	
	@Override
	public int getBoardCount(Map<String, Object> paramMap) throws Exception {
		
		return boardMapper.selectBoardCount(paramMap);
	}

	@Override
	public List<Board> getBoardList(Map<String, Object> paramMap) throws Exception {
		
		return boardMapper.selectBoardList(paramMap);
	}

	@Override
	public Board getBoard(int bo_seq_no) throws Exception {
		
		boardMapper.updateHitCnt(bo_seq_no);
		
		Board board = boardMapper.selectBoard(bo_seq_no);
		
		return board;
	}

	@Override
	public int insertBoard(Board board) throws Exception {
		
		int updCnt = boardMapper.insertBoard(board);
		
		return updCnt;
	}

	@Override
	public int updateBoard(Board board) throws Exception {
		
		return boardMapper.updateBoard(board);
	}

	@Override
	public int deleteBoard(Map<String, Object> paramMap) throws Exception {
		
		return boardMapper.deleteBoard(paramMap);
	}
}



