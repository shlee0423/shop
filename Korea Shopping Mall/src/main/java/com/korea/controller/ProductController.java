package com.korea.controller;

import com.korea.dto.ProductDTO;
import com.korea.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //모든 상품들의 리스트를 보여주는 list.html화면
    @GetMapping
    public String get_products(Model model){
        List<ProductDTO> products = productService.get_products();
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
