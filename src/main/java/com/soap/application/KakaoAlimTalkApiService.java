package com.soap.application;

import com.soap.application.dto.KakaoApiResultVO;
import com.soap.domain.Mzsendlog;
import com.soap.domain.Mzsendtran;
import com.soap.domain.MzsendtranRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.gson.Gson;

/**
 * 카카오 API 요청 클래스
 */
@Slf4j
@Service
@Getter
@Setter
@RequiredArgsConstructor
@Configuration
public class KakaoAlimTalkApiService {

    private final MzsendtranRepository mzsendtranRepository;

    @Value("${const.alimtalkApiURL}")
    private String alimtalkApiURL;

    /**
     * MZSENDTRAN 데이터 받은 후 KAKAO API 호출 -> 받은 리턴값 리턴값 MZSENDLOG 엔티티에 추가후 반환
     */
    public Mzsendlog sendAlimTalk(Mzsendtran mzsendtran) {
        RestTemplate restTemplate = new RestTemplate();
        JSONObject request = new JSONObject();
        HttpHeaders header = new HttpHeaders();
        Gson gson = new Gson();
        KakaoApiResultVO kakaoApiResultVO = new KakaoApiResultVO();
        Mzsendlog mzsendlog = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String datestr = simpleDateFormat.format(Calendar.getInstance().getTime());

            String phoneNum = (String) mzsendtran.getPhoneNum();
            String replacePhoneNum = "82" + phoneNum.substring(1);
            String tranSts = "2";

            request.put("serial_number", datestr+"-"+mzsendtran.getSn());
            request.put("sender_key", mzsendtran.getSenderKey());
            request.put("phone_number", replacePhoneNum);
            request.put("template_code", mzsendtran.getTmplCd());
            request.put("message", mzsendtran.getSndMsg());
            request.put("response_method", mzsendtran.getSndType().equals("P") ? "push" : "realtime");

            if (StringUtils.isNotEmpty((String) mzsendtran.getAttachment())) {
                String attachment = (String) mzsendtran.getAttachment();
                request.put("attachment", jsonParser(attachment));
            }

            header.add("Content-type", "application/json; charset=utf-8");
            HttpEntity<?> entity = new HttpEntity<>(request.toString(), header);

            //카카오 API 호출
            String result = restTemplate.postForObject(new URI(alimtalkApiURL), entity, String.class);

            //mzsendtran 테이블에 적재한 데이터 -> mzsendlog 엔티티로 적재
            mzsendlog = Mzsendlog.builder()
                    .sn(mzsendtran.getSn())
                    .senderKey(mzsendtran.getSenderKey())
                    .channel(mzsendtran.getChannel())
                    .sndType(mzsendtran.getSndType())
                    .phoneNum(mzsendtran.getPhoneNum())
                    .tmplCd(mzsendtran.getTmplCd())
                    .subject(mzsendtran.getSubject())
                    .sndMsg(mzsendtran.getSndMsg())
                    .smsSndMsg(mzsendtran.getSmsSndMsg())
                    .smsSndNum(mzsendtran.getSmsSndNum())
                    .reqDeptCd(mzsendtran.getReqDeptCd())
                    .reqUsrId(mzsendtran.getReqUsrId())
                    .reqDtm(mzsendtran.getReqDtm())
                    .smsSndYn(mzsendtran.getSmsSndYn())
                    .tranSts(mzsendtran.getTranSts())
                    .slot1(mzsendtran.getSlot1())
                    .slot2(mzsendtran.getSlot2())
                    .trTypeCd(mzsendtran.getTrTypeCd())
                    .attachment(mzsendtran.getAttachment())
                    .appUserId(mzsendtran.getAppUserId())
                    .build();

            kakaoApiResultVO = gson.fromJson(result, KakaoApiResultVO.class);

            tranSts = (result != null) ? "4" : "3";

            //mzsendlog 엔티티에 카카오 결과값 적재
            mzsendlog.setRsltCd(kakaoApiResultVO.getCode());
            mzsendlog.setRcptMsg(kakaoApiResultVO.getMessage());
            mzsendlog.setTranSts(tranSts);
            mzsendlog.setRcptDtm(datestr);
            mzsendlog.setSndDtm(datestr);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("Kakao sendAlimTalk fail!!");
        }
        return mzsendlog;

    }

    /**
     * String -> JSONObject 변환 메소드
     */
    public JSONObject jsonParser(String message) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(message);
        JSONObject jsobObj = (JSONObject) obj;
        return jsobObj;
    }


}

