package com.example.jpa_practice.infrastructure;

import com.example.jpa_practice.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager entityManager;

    @Test
    @DisplayName("1차 캐시와 동일성")
    void findById() {
        // given
        Member member = new Member("시안", "백엔드");
        memberRepository.save(member);

        // when
        Member findMember1 = memberRepository.findById(1L).get();
        Member findMember2 = memberRepository.findById(1L).get();

        // then
        assertThat(findMember1).isEqualTo(findMember2);
    }

    @Test
    @DisplayName("변경 감지")
    void dirtyCheck() {
        // given
        Member member = new Member("시안", "백엔드");
        entityManager.persist(member);
        entityManager.flush();

        // when
        Member findMember = memberRepository.findById(1L).get();
        findMember.setRole("잡부");
        entityManager.flush();

        // then
        assertThat(member.getRole()).isEqualTo(findMember.getRole());
    }
}