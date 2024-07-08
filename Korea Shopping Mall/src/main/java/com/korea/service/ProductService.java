package com.korea.service;

import com.korea.dto.CategoryDTO;
import com.korea.dto.ProductDTO;
import com.korea.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> get_products(Integer categoryNo) {
        return productMapper.selectProductsByCategoryNo(categoryNo);
    }

    public ProductDTO get_product_by_no(Integer productNo){
        return productMapper.selectProductByNo(productNo);
    }

    //**************************************************************/
    // 존재하는 모든 카테고리 조회(계층구조로)
    public List<CategoryDTO> get_categories(){
        return productMapper.selectCategories();
    }

    public CategoryDTO get_category(Integer categoryNo){
        return productMapper.selectCategory(categoryNo);
    }
}
