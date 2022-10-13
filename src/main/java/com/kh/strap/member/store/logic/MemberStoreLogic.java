package com.kh.strap.member.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.strap.member.domain.Member;
import com.kh.strap.member.store.MemberStore;


@Repository
public class MemberStoreLogic implements MemberStore{

	@Override
	public int memberIdCheck(SqlSession session, String memberId) {
		int result = session.selectOne("MemberMapper.memberIdCheck", memberId);
		return result;
	}

	@Override
	public int inserMember(SqlSession session, Member member) {
		int result = session.insert("MemberMapper.inserMember", member);
		return result;
	}

	@Override
	public int memberLogin(SqlSession session, Member member) {
		int result = session.selectOne("MemberMapper.memberLogin", member);
		return result;
	}

	@Override
	public int IdCheckByEmail(SqlSession session, String memberEmail) {
		int result = session.selectOne("MemberMapper.IdCheckByEmail", memberEmail);
		return result;
	}

//	@Override
//	public List<String> findIdByEmail(SqlSession session, String memberEmail) {
//		List<String> sList = session.selectList("MemberMapper.findIdByEmail", memberEmail);
//		return sList;
//	}
	@Override
	public List<Member> findIdByEmail(SqlSession session, String memberEmail) {
		List<Member> sList = session.selectList("MemberMapper.findIdByEmail", memberEmail);
		return sList;
	}

	@Override
	public int idEmailCheck(SqlSession session, Member member) {
		int result = session.selectOne("MemberMapper.idEmailCheck", member);
		return result;
	}

}
