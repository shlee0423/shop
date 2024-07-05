package com.korea.dto.customer_service;

import com.korea.dto.FileDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OneOnOneDTO {
    private Integer no;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime writeDateTime;
    private Integer viewCount;
    private Boolean isOpened;
    private Boolean isAnswered;
    private String password;
    private List<FileDTO> files;
    private List<OneOnOneAnswerDTO> answers;
}
