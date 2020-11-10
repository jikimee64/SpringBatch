package com.soap.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//JPA에서는 프록시를 생성을 위해서 기본 생성자를 반드시 하나를 생성
//이때 접근 권한이 protected : 외부에서 생성을 열어둘 필요가 X
public class MzsendtranEntity {
    @Id
    @GeneratedValue
    private String sn;

    @Column
    private String senderKey;
    @Column
    private String channel;
    @Column
    private String sndType;
    @Column
    private String phoneNum;
    @Column
    private String tmplCd;
    @Column
    private String subject;
    @Column
    private String sndMsg;
    @Column
    private String smsSndMsg;
    @Column
    private String smsSndNum;
    @Column
    private String reqDeptCd;
    @Column
    private String reqUsrId;
    @Column
    private String reqDtm;
    @Column
    private String sndDtm;
    @Column
    private String rsltCd;
    @Column
    private String rcptMsg;
    @Column
    private String rcptDtm;
    @Column
    private String smsSndDtm;
    @Column
    private String smsRsltCd;
    @Column
    private String smsRcptMsg;
    @Column
    private String smsRcptDtm;
    @Column
    private String smsGb;
    @Column
    private String smsSndYn;
    @Column
    private String tranSn;
    @Column
    private String tranSts;
    @Column
    private String agentId;
    @Column
    private String slot1;
    @Column
    private String slot2;
    @Column
    private String trTypeCd;
    @Column
    private String attachment;
    @Column
    private String appUserId;




}
