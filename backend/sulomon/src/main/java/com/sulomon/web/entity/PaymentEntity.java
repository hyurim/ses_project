package com.sulomon.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class PaymentEntity {

    // 결제 ID (AUTO_INCREMENT, PRIMARY KEY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    // 결제 금액 (BigDecimal 타입, NOT NULL, 소수점 두 자리까지 허용)
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount; // 결제 금액

    // 결제 방법 (VARCHAR로 변경, NOT NULL)
    @Column(name = "payment_method", nullable = false, length = 20)
    private String paymentMethod; // 결제 방법 ('TOSS', 'NAVER_PAY', 'KAKAO_PAY')

    // 결제 상태 (VARCHAR로 변경, NOT NULL, 기본값 'PENDING')
    @Column(name = "status", nullable = false, length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'PENDING'")
    private String status; // 결제 상태 ('PENDING', 'COMPLETED', 'FAILED')

    // 결제 생성 시간 (DATETIME, NOT NULL, DEFAULT CURRENT_TIMESTAMP)
    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt; // 결제 생성 시간

}
