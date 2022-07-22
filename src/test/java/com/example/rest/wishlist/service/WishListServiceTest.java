package com.example.rest.wishlist.service;

import com.example.rest.naver.NaverClient;
import com.example.rest.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListServiceTest {
    @Autowired
    private WishListService wishListService;

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchTest(){
        var search = new SearchLocalReq();
        search.setQuery("갈비집");

        var result = naverClient.searchLocal(search);
        System.out.println(result);

        var result2 = wishListService.search("갈비집");
        System.out.println(result2);


    }
}
