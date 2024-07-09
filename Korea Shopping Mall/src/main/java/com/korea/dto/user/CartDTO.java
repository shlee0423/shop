package com.korea.dto.user;

import com.korea.dto.ProductDTO;
import lombok.*;

@Getter
@Setter
@ToString
public class CartDTO {
    private Integer no;
    private UserDTO user; // 회원
    private ProductDTO product; // 장바구니의 상품
    private Integer amount;
    private String size;
    private String color;
}
