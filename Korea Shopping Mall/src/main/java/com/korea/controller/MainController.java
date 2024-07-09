package com.korea.controller;

import com.korea.dto.CategoryDTO;
import com.korea.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired private ProductService productService;

    @GetMapping("/main")
    public void get_main(Model model){
        List<CategoryDTO> categories = productService.get_categories();
//        System.out.println(categories.toString());
        model.addAttribute("categories", categories);
    }
}
