package com.k3c1.fitFood.domain.member.repository;

import com.k3c1.fitFood.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
