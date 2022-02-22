package com.sparta.velogclone.contoller;

import com.sparta.velogclone.dto.requestdto.SearchRequestDto;
import com.sparta.velogclone.dto.responsedto.PostResponseDto;
import com.sparta.velogclone.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/api/search")
    public List<PostResponseDto> searchPost(@RequestBody SearchRequestDto searchRequestDto) {
        return searchService.searchPost(searchRequestDto);
    }
}
