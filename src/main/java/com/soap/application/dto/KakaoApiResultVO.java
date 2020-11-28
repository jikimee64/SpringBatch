package com.soap.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 카카오 API 결과 받는 VO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoApiResultVO {
    private String code;
    private String received_at;
    private String message;
}
