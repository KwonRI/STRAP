package com.kh.strap.member.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.strap.member.domain.Member;

public interface MemberStore {

	int memberIdCheck(SqlSession session, String memberId);

	int inserMember(SqlSession session, Member member);

	int memberLogin(SqlSession session, Member member);

	int IdCheckByEmail(SqlSession session, String memberEmail);

//	List<String> findIdByEmail(SqlSession session, String memberEmail);
	List<Member> findIdByEmail(SqlSession session, String memberEmail);

	int idEmailCheck(SqlSession session, Member member);

}
