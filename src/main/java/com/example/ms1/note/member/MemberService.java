package com.example.ms1.note.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member save(String loginId, String password, String nickname, String email) {
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(passwordEncoder.encode(password)); // 스프링 시큐리티에서 password가 암호화되지 않았을 경우 인증처리를 하지 않는다.
        member.setNickname(nickname);
        member.setEmail(email);
        member.setCreateDate(LocalDateTime.now());

        return memberRepository.save(member);
    }
}
