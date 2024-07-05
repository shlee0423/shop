package com.korea.service;

import com.korea.dto.ProductDTO;
import com.korea.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> get_products() {
        return productMapper.selectProducts();
    }

    public ProductDTO get_product_by_no(Integer productNo){
        return productMapper.selectProductByNo(productNo);
    }
}
