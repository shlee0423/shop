package com.korea.mapper;

import com.korea.dto.user.CartDTO;
import com.korea.dto.user.SocialUserDTO;
import com.korea.dto.user.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO selectUserById(String id);

    UserDTO selectUserByCi(String ci);

    /************ 회원가입 ***************/
    void insert_user(UserDTO user); // 유저 회원가입


    /********************** socialuser *****************/
    SocialUserDTO selectSocialUserById(String id);

    void insert_socialUser(SocialUserDTO socialUser);

    /*************** 장바구니 *********************/
    List<CartDTO> selectCartByUserId(String userId);

    CartDTO selectCartDuplicated(CartDTO cart);// 장바구니에 해당 상품이 존재?

    void insertCart(CartDTO cart); // 장바구니에 상품 추가

    void updateCartAmount(
            @Param("cartNo") Integer cartNo,
            @Param("amount") Integer amount); // 장바구니에 존재하는 상품의 수량 변경

    void deleteCart(List<Integer> cartNumbers); // 해당 장바구니의 상품을 삭

}
