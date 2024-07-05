package com.korea.controller;

import com.korea.dto.ProductDTO;
import com.korea.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

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
