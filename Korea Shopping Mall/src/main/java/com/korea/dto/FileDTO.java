package com.korea.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString(exclude = "data")
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    private String UUID;
    private String originalFileName;
    private String saveFileName;
    private byte[] data;
    private String url;
}
