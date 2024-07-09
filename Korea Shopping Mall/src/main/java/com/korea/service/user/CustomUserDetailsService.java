package com.korea.service.user;

import com.korea.dto.user.UserDTO;
import com.korea.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("로그인 시도하는 유저명..: " + username);
        UserDTO findUser = userMapper.selectUserById(username); // 해당 id를 가진 유저를 DB에서 조회

        // 유저를 못찾았다면 로그인을 시키면 안된다
        if(Objects.isNull(findUser)) {
            System.out.println("유저가 존재하지 않음..");
            throw new UsernameNotFoundException("Error: 유저가 존재하지 않습니다, Not Found");
        }
//
//        String encodedPw = passwordEncoder.encode(findUser.getPassword());
//        System.out.println("인코딩된 패스워드: " + encodedPw);

        return findUser;
    }
}
