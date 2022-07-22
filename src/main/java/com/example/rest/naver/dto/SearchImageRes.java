package com.example.rest.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageRes {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<SearchImageItem> items;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImageItem {
        private String title;
        private String link;    // 검색 결과 이미지의 하이퍼텍스트 link
        private String thumbnail;   // 검색 결과 이미지의 썸네일 link
        private String sizeheight;
        private String sizewidth;

    }
}
