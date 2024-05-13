package com.example.ms1.security;

import com.example.ms1.note.member.Member;
import com.example.ms1.note.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
   public final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByLoginId(username).orElseThrow(
                () -> new RuntimeException("존재하지 않는 아이디입니다.")
        );
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        List<SimpleGrantedAuthority> authorities = List.of(authority); // 권한이 여러개일 경우 List.of()로 추가 가능

        return new User(member.getLoginId(), member.getPassword(), authorities); // 3가지 필수 인증 정보필요. //아이디, 비밀번호, 권한
    }
}

// 스프링 시큐리티는 DB에 대해서 모르기 때문에 DB 관련 작업을 처리해서 유저를 만들어주는 UserDetailService 를 반드시 구현해야한다.