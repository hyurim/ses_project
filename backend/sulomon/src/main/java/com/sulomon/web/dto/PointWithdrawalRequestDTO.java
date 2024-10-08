package com.sulomon.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointWithdrawalRequestDTO {

    private Integer withdrawalId;          // 출금 요청 ID
    private UUID userNum;               // 사용자 ID
    private Integer requestedAmount;       // 요청 금액
    private String bankName;               // 은행 이름
    private String accountNumber;          // 계좌 번호
    private String status;       // 출금 요청 상태 (PENDING, APPROVED, REJECTED)
    private LocalDateTime createdAt;       // 출금 요청 생성 시간
    private LocalDateTime processedAt;     // 출금 처리 시간


}