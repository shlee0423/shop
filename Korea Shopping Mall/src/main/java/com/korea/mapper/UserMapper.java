package com.korea.mapper;

import com.korea.dto.user.SocialUserDTO;
import com.korea.dto.user.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    UserDTO selectUserById(String id);

    UserDTO selectUserByCi(String ci);

    /************ 회원가입 ***************/
    void insert_user(UserDTO user); // 유저 회원가입


    /********************** socialuser *****************/
    SocialUserDTO selectSocialUserById(String id);

    void insert_socialUser(SocialUserDTO socialUser);
}
