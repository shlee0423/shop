package com.korea.controller.user;

import com.korea.dto.user.CartDTO;
import com.korea.dto.user.OrderDTO;
import com.korea.dto.user.UserDTO;
import com.korea.service.ProductService;
import com.korea.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

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
        boolean result = userService.create_user(impUid, user);
        if(result){
            return "redirect:/user/login";
        }
        redirectAttributes.addFlashAttribute("certErrorMsg","본인인증이 완료되지 않았습니다.");
        return "redirect:/user/register";
    }

    /***************** 장바구니 ******************/
    // 장바구니 화면으로 이동
    @GetMapping("/cart")
    public void get_user_cart(
            @AuthenticationPrincipal UserDTO user,
            Model model
    ){
        List<CartDTO> carts = new ArrayList<>();
        if(!Objects.isNull(user)){
            carts = userService.get_carts(user);
        }
        model.addAttribute("carts", carts);
    }
    // 장바구니에 상품을 추가
    @ResponseBody
    @PostMapping("/cart")
    public ResponseEntity<Void> post_user_cart(
            @AuthenticationPrincipal UserDTO user,
            CartDTO cart
    ){
        log.info(cart);
        if(Objects.isNull(user)){
            log.error("로그인 되지 않은 유저의 장바구니 삽입 시도");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        userService.add_cart(user, cart);
        // 장바구니 삽입 성공
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<Void> delete_user_cart(
            @RequestBody List<CartDTO> carts
    ){
        log.info(carts);
        userService.delete_cart(carts);
        return ResponseEntity.ok().body(null);
    }

    /********************** 결제 *********************/
    @GetMapping("/order")
    public String get_user_order(
            HttpSession session,
            Model model
    ){
        Object cartsData = session.getAttribute("carts");
        if(Objects.isNull(cartsData)){
            log.error("카트 데이터가 없음");
            return "redirect:/user/cart";
        }
        List<CartDTO> carts = (List<CartDTO>) cartsData;
        productService.set_product_information_of_carts(carts);
        log.info(carts);
        model.addAttribute("carts", carts);
        return "user/order";
    }

    @PostMapping("/order")
    public void post_user_order(
            @AuthenticationPrincipal UserDTO user,
            @RequestBody List<CartDTO> carts,
            HttpSession session
    ){
        session.setAttribute("carts", carts);
    }

    /******************* 결제 **********************/
    @ResponseBody
    @PostMapping("/payment")
    public ResponseEntity<Void> post_user_payment(
            @AuthenticationPrincipal UserDTO user,
            @RequestBody OrderDTO order
    ){
        // 주문내역(+결제내역) 추가
        userService.add_order(user, order);
        return ResponseEntity.ok().body(null);
    }
}
