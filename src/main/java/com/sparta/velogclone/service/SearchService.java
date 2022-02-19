package com.sparta.velogclone.service;

import com.sparta.velogclone.domain.Post;
import com.sparta.velogclone.dto.requestdto.SearchRequestDto;
import com.sparta.velogclone.dto.responsedto.PostResponseDto;
import com.sparta.velogclone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final PostRepository postRepository;

    @Autowired
    public SearchService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public List<PostResponseDto> searchPost(SearchRequestDto searchRequestDto) {

        List<Post> posts = postRepository.findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(searchRequestDto.getSearchWord());

        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        if (posts.isEmpty()) {
            return postResponseDtoList;
        }

        for (Post post : posts) {
            postResponseDtoList.add(post.toResponse());
        }

        return postResponseDtoList;
    }
}
