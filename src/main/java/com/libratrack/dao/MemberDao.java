package com.libratrack.dao;

import com.libratrack.model.Member;

/** In-memory storage of members. */
public class MemberDao extends InMemoryDao<Member> {

    @Override
    protected long idOf(Member member) {
        return member.getId();
    }
}
