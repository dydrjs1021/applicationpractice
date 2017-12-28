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
	BoardMapper boardmapper;

	SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
	
	@Override
	public int getBoardCount(Map<String, Object> paramMap) throws Exception {
		
		return boardmapper.selectBoardCount(paramMap);
	}

	@Override
	public List<Board> getBoardList(Map<String, Object> paramMap) throws Exception {
		
		return boardmapper.selectBoardList(paramMap);
	}

	@Override
	public Board getBoard(int bo_seq_no) throws Exception {
		
		return boardmapper.selectBoard(bo_seq_no);
	}

	@Override
	public int insertBoard(Board board) throws Exception {
		
		return boardmapper.insertBoard(board);
	}

	@Override
	public int updateBoard(Board board) throws Exception {
		
		return boardmapper.updateBoard(board);
	}

	@Override
	public int deleteBoard(Map<String, Object> paramMap) throws Exception {
		
		return boardmapper.deleteBoard(paramMap);
	}
}



