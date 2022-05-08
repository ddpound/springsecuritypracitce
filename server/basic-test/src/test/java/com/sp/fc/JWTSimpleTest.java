package com.sp.fc;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Map;

public class JWTSimpleTest {

    private void printToken(String token){
        // 토큰은 . 이 구분자여서 아래처럼 스플릿 했습니다.
        String[] tokens = token.split("\\.");
        System.out.println("header : "+ new String (Base64.getDecoder().decode(tokens[0])));
        System.out.println("body : "+ new String (Base64.getDecoder().decode(tokens[1])));
    }

    @DisplayName("1. jjwt 를 이용한 토큰")
    @Test
    void test_1(){

        String okta_token = Jwts.builder().addClaims(
                Map.of("name", "yyy", "price", 1000)
        ).signWith(SignatureAlgorithm.HS256, "yyy12345")
                .compact(); // Base64 로 인코딩 해주는 메소드라 예상

        System.out.println(okta_token);
        printToken(okta_token);
    }

    // oauth0 에서 만든 토큰 라이브러리
    @DisplayName("1. java-jwt 를 이용한 토큰")
    @Test
    void test_2(){
        String oauth0_token = JWT.create().withClaim("name", "yyy1234").withClaim("price",2100)
                .sign(Algorithm.HMAC256("yyy1290"));

        System.out.println(oauth0_token);
        printToken(oauth0_token);

    }
}
