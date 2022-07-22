package com.example.rest.wishlist.service;

import com.example.rest.naver.NaverClient;
import com.example.rest.naver.dto.SearchImageReq;
import com.example.rest.naver.dto.SearchLocalReq;
import com.example.rest.wishlist.dto.WishListDto;
import com.example.rest.wishlist.entity.WishListEntity;
import com.example.rest.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;


    public WishListDto search(String query){
        // 지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if(searchLocalRes.getTotal() > 0){  // 검색 결과가 존재할 경우
            var localItem = searchLocalRes.getItems().stream().findFirst().get(); // 첫 번째 검색 결과만 반환
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>",""); // 검색 시, 잡다한 문자를 제외시킴
            log.info(imageQuery);   // 해운대암소갈비집

            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            // 이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal() > 0){  // 검색 이미지가 존재할 경우
                var imageItem = searchImageRes.getItems().stream().findFirst().get();   // 첫 번째 이미지 검색 결과

                // 결과를 리턴
                var result = new WishListDto();
                result.setLastVisitDate(searchLocalRes.getLastBuildDate());
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                // 첫 번째 검색 결과만 가져옴 ( display = 1 )

                return result;
            }
        }

        return new WishListDto();
    }


    public WishListDto add(WishListDto wishListDto) {
        //
        var entity = dtoToEntity(wishListDto);
        var saveEntity = wishListRepository.save(entity);
        return entityToDto(saveEntity);
    }



    private WishListEntity dtoToEntity(WishListDto wishListDto){
        var entity = new WishListEntity();
        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());
        return entity;
    }

    private WishListDto entityToDto(WishListEntity wishListEntity){
        var dto = new WishListDto();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());
        return dto;
    }

    public List<WishListDto> findAll() {

        return wishListRepository.findall()
                .stream()
                .map(it -> entityToDto(it))// entity -> dto 변경
                .collect(Collectors.toList());

    }

    public void delete(int index){
        wishListRepository.deleteById(index);
    }

    public void addVisit(int index){
        var wishItem = wishListRepository.findById(index);
        if(wishItem.isPresent()){
            var item = wishItem.get();
            item.setVisit(true);
            item.setVisitCount(item.getVisitCount() + 1);
        }
    }



}
