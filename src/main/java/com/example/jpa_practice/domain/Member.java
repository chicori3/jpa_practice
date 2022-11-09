package com.example.jpa_practice.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EqualsAndHashCode(of = "id")
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;
    private String role;

    public Member(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
