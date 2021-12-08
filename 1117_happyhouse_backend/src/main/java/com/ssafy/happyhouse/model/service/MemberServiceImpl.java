package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}

	@Override
	public boolean deleteComment(String userid) throws Exception {
	      return sqlSession.getMapper(MemberMapper.class).deleteComment(userid) == true;
	}

	@Override
	public boolean deleteBoard(String userid) throws Exception {
	      return sqlSession.getMapper(MemberMapper.class).deleteBoard(userid) == true;
	}

	@Override
	public boolean deleteWishList(String userid) throws Exception {
	      return sqlSession.getMapper(MemberMapper.class).deleteWishList(userid) == true;
	}

	@Override
	public boolean deleteNotice(String userid) throws Exception {
	      return sqlSession.getMapper(MemberMapper.class).deleteNotice(userid) == true;
	}
	
	@Override
	   public boolean deleteMember(String userid) throws Exception {
	      return sqlSession.getMapper(MemberMapper.class).deleteMember(userid) == true;
	   }

	@Override
	public boolean registerMember(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).registerMember(memberDto) == true;
	}

	@Override
	public boolean updateMember(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).updateMember(memberDto) == 1;
	}

	@Override
	public boolean idCheck(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).idCheck(userid) == 1;
	}


}
