package com.korea.service.user;

import com.korea.dto.user.UserDTO;
import com.korea.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Log4j2
@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RestOperations restOperations;

    private final String PORT_ONE_IMP_KEY = "1421524636044743";
    private final String PORT_ONE_IMP_SECRET = "w9l3XWsTIbLac7s2R75SztwaKm9EL17dzxH28Z1lFg6ZcSQrkVGaiEM3C6wVryaUDgUH45RJUU3mvdWZ";
    private final String PORT_ONE_ACCESS_TOKEN_URL = "https://api.iamport.kr/users/getToken";
    private final String PORT_ONE_USER_CERT_INFO_URL = "https://api.iamport.kr/certifications/{impUid}";

    // 포트원의 ACCESS_TOKEN 값을 얻는 메서드
    private String get_portone_access_token(){
        RequestEntity<String> getAccessTokenRequest = RequestEntity
                .post(PORT_ONE_ACCESS_TOKEN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(JSONObject.toJSONString(Map.of(
                        "imp_key", PORT_ONE_IMP_KEY,
                        "imp_secret", URLEncoder.encode(PORT_ONE_IMP_SECRET, StandardCharsets.UTF_8)
                )));

        ResponseEntity<Map> getAccessTokenResponse = restOperations.exchange(getAccessTokenRequest, Map.class);
        // 요청이 성공적으로 완료되었음
        if(getAccessTokenResponse.getStatusCode().equals(HttpStatus.OK)){
            Map<String, Map> body = (Map<String, Map>)getAccessTokenResponse.getBody();
            Map<String, String> response = body.get("response");
            String accessToken = response.get("access_token");
            log.info("엑세스토큰 발급 완료");
            return accessToken;
        }
        log.error("요청에러(실패)");
        // 요청에 실패함
        return null;
    }

    // 포트원의 본인인증 정보를 얻는 메서드 (유저의 ci값을 얻음)
    private String get_portone_user_cert_info(String impUid, String accessToken){
        RequestEntity<Void> userCertRequest = RequestEntity.get(PORT_ONE_USER_CERT_INFO_URL, impUid)
                        .header("Authorization", "Bearer " + accessToken)
                        .build();

        ResponseEntity<Map> userCertResponse = restOperations.exchange(userCertRequest, Map.class);
        // 200. OK. 요청이 성공하였음
        if(userCertResponse.getStatusCode().equals(HttpStatus.OK)){
            Map<String, Object> body = userCertResponse.getBody();
            Map<String, Object> response = (Map<String, Object>)body.get("response");
            Boolean certified = (Boolean) response.get("certified");
            if(certified){
                log.info("인증성공");
                String uniqueKey = (String) response.get("unique_key");
                return uniqueKey;
            }
            log.warn("인증실패");
            return null;
        }
        log.error("요청에러(실패)");
        return null;
    }


    // 유저 생성 (회원가입)
    public boolean create_user(String impUid, UserDTO user){
        if(impUid.isBlank()){
            return false;
        }
        String accessToken = get_portone_access_token();
        log.info(accessToken);
        if(accessToken == null){
            return false;
        }
        String userCi = get_portone_user_cert_info(impUid, accessToken);
        log.info(userCi);
        if(userCi == null){
            return false;
        }
        // 유저를 회원가입 시킬 때, CI 값을 같이 넣어준다
        user.setCi(userCi);
        // 유저를 회원가입 시킬 때, 패스워드를 인코딩해서 넣는다
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert_user(user);
        return true;
    }
}
