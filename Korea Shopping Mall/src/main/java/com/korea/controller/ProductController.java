package com.korea.controller;

import com.korea.dto.CategoryDTO;
import com.korea.dto.ProductDTO;
import com.korea.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //모든 상품들의 리스트를 보여주는 list.html화면
    @GetMapping
    public String get_products(
            @RequestParam("category") Integer categoryNo,
            Model model
    ){
        // header에 표시할 모든 카테고리 리스트
        List<CategoryDTO> categories = productService.get_categories();
        // 현재 카테고리의 리스트를 출력하기 위한 level1 -> 2 -> 3의 카테고리 목록
        // 현재 카테고리가 '신발' 이라면 '신발'의 최상위 카테고리를 가져옴
        CategoryDTO category = productService.get_category(categoryNo);
        // 해당 카테고리에 맞는 모든 상품의 리스트를 조회함
        List<ProductDTO> products = productService.get_products(categoryNo);

        model.addAttribute("categories", categories);
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        return "list";
    }

    //하나의 상품을 보여주는 상세 페이지 화면. product.html
    @GetMapping("/{no}")
    public String get_product(
            @PathVariable("no") Integer productNo,
            Model model
    ) {
        ProductDTO product = productService.get_product_by_no(productNo);
        model.addAttribute("product", product);
        return "product";
    }
}
