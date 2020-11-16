package com.soap.application;

import com.soap.domain.Mzsendtran2;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Slf4j
@Service
@Getter
@Configuration
@ConfigurationProperties(prefix="const")
public class KakaoAlimTalkApiService {

    //@Value("${alimtalkApiURL}")
    //private String alimtalkApiURL;

    public Mzsendtran2 sendAlimTalk(Mzsendtran2 mzsendtran2) {
        //Map<String, String> response = new HashMap<String, String>();
        RestTemplate restTemplate = new RestTemplate();
        JSONObject request = new JSONObject();
        HttpHeaders header = new HttpHeaders();
        Gson gson = new Gson();

        try {
            /*
                API 대신 임의로 가공 부분
            */
            //update구문 배치에서 생성후 실행
            mzsendtran2.setPhoneNum("01099999999");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("Kakao sendAlimTalk fail!!");
            //response.put("status", "fail");
        }
        return mzsendtran2;
        //return response;
    }

    //String -> JSONObject 변환
    public JSONObject jsonParser(String message) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(message);
        JSONObject jsobObj = (JSONObject) obj;
        return jsobObj;
    }
}


 /*
            String phoneNum = (String) mzsendtranEntity.getPhoneNum()
            String replacePhoneNum = "82" + phoneNum.substring(1);
            String tranSts = "2"; //2: 발송처리중

            request.put("serial_number", mzsendtranEntity.getSn());
            request.put("sender_key", mzsendtranEntity.getSenderKey());
            request.put("phone_number", replacePhoneNum);
            request.put("template_code", mzsendtranEntity.getTmplCd());
            request.put("message", mzsendtranEntity.getSndMsg());
            request.put("response_method", mzsendtranEntity.getSndType().equals("P") ? "push" : "realtime");

            if (StringUtils.isNotEmpty((String) mzsendtranEntity.getAttachment())) {
                String attachment = (String) mzsendtranEntity.getAttachment();
                request.put("attachment", jsonParser(attachment));
            }

            header.add("Content-type", "application/json; charset=utf-8");
            HttpEntity<?> entity = new HttpEntity<>(request.toString(), header);

            // log.info("request.toString() : {}", request.toString());

            String result = restTemplate.postForObject(new URI(alimtalkApiURL), entity, String.class);

            //3: 발송요청완료, 4:알림톡결과수신완료
            tranSts = (result != null) ? "4" : "3";

            // json -> object(json형식의 문자열인 result를 ResultVo에 인서트)
            mzsendtranEntity = gson.fromJson(result, MzsendtranEntity.class);

            //log.info("resultVo.getCode() : {} resultVo.getMessage() : {}", resultVo.getCode(), resultVo.getMessage());
            response.put("code", resultVo.getCode());
            response.put("message", resultVo.getMessage());
            response.put("tranSts", tranSts);
            response.put("status", "success");
            */