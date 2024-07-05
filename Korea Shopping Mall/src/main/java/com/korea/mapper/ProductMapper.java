package com.korea.mapper;

import com.korea.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    ProductDTO selectProductByNo(Integer no);
}
