package com.korea.controller.user;

import com.korea.dto.user.UserDTO;
import com.korea.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

/****************** 로그인 **********************/
    @GetMapping("/login")
    public void get_user_login(){}

    /****************** 회원가입 **********************/
    @GetMapping("/register")
    public void get_user_register(){}

    @PostMapping(value = "/register")
    public String post_user_register(
            @RequestParam("impUid") String impUid,
            @Validated UserDTO user,
            RedirectAttributes redirectAttributes,
            BindingResult bindingResult
    ){
        log.info("impUid: " + impUid);
        log.info("user: " + user);
//        boolean result = userService.create_user(impUid, user);
//        if(result){
//            return "redirect:/user/login";
//        }
//        redirectAttributes.addFlashAttribute("certErrorMsg","본인인증이 완료되지 않았습니다.");
//        return "redirect:/user/register";
        return "/user/register";
    }



}
