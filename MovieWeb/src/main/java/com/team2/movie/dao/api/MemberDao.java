package com.team2.movie.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team2.movie.dao.dto.Member;

@Repository
public interface MemberDao extends JpaRepository<Member, String>{

}
