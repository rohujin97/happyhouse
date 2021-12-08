package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public boolean deleteComment(String userid) throws SQLException;
	public boolean deleteBoard(String userid) throws SQLException;
	public boolean deleteWishList(String userid) throws SQLException;
	public boolean deleteNotice(String userid) throws SQLException;
	public boolean deleteMember(String userid) throws SQLException;
	public boolean registerMember(MemberDto memberDto) throws SQLException;
	public int updateMember(MemberDto memberDto) throws SQLException;
	public int idCheck(String userid) throws SQLException;
}
