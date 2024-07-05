package com.korea.mapper;

import com.korea.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDTO> selectProducts();
    ProductDTO selectProductByNo(Integer no);
}
