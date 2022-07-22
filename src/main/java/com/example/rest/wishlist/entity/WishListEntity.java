package com.example.rest.wishlist.entity;

import com.example.rest.db.MemoryDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class WishListEntity extends MemoryDbEntity {

    private String title;   // 음식명, 장소명
    private String category;    // 카테고리
    private String address; // 주소
    private String roadAddress; // 도로명
    private String homePageLink;    // 홈페이지 주소
    private String imageLink;   // 음식, 가게 이미지 주소소
    private boolean isVisit; // 방문여부
    private int visitCount; // 방문 카운트
    private String lastVisitDate;    // 마지막 방문일지
}
