package com.soap.application;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class KakaoAlimTalkApiServiceTest {

    @Autowired
    KakaoAlimTalkApiService kakaoAlimTalkApiService;

    @Test
    public void yamlReadTest(){
        String alimtalkApiURL = kakaoAlimTalkApiService.getAlimtalkApiURL();

        assertThat(alimtalkApiURL).isEqualTo("123123");
    }
}
