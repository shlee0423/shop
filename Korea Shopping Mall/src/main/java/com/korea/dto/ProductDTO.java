package com.korea.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductDTO {
    private Integer no;
    private String name;
    private Integer price;
    private String tag;
    private String colors;
    private String describe;
    private String material;
    private String sizes;
    private List<FileDTO> images;
}
