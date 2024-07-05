package com.korea.dto;

import lombok.*;

import java.util.Base64;

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

    public void setData(byte[] data) {
        this.data = data;
        try{
            this.url = Base64.getEncoder().encodeToString(data);
        }catch (Exception e){

        }
    }
}
