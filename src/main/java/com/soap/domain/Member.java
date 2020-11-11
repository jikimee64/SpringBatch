package com.soap.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="MEMBER")
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String nickName;

    @Column
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column
    private int amountCharged;  // 청구된 금액

    @Column
    private int amountPaid; // 결제된 금액

    @Column
    private LocalDate dueDate;  // 결제 마감일

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    public Member setStatusByUnPaid() {
        if(this.isUnpaid()) {
            this.status = MemberStatus.INACTIVE;
        }
        return this;
    }

    public boolean isUnpaid() {
        return this.amountCharged <= this.amountPaid;
    }

    @Builder
    public Member() {}

    @Builder
    public Member(String name, String email, String nickName, int amountCharged, int amountPaid, LocalDate dueDate) {
        this.name = name;
        this.email = email;
        this.nickName = nickName;
        this.status = MemberStatus.ACTIVE;
        this.amountCharged = amountCharged;
        this.amountPaid = amountPaid;
        this.dueDate = dueDate;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}