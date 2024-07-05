package com.korea.dto.customer_service;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OneOnOneAnswerDTO {
    private Integer no; // 번호
    private String userId; // 답변자
    private Integer oneOnOneNo; // 게시물번호
    private String answer; // 답변내용
    private LocalDateTime writeDateTime; // 답변날짜
}
