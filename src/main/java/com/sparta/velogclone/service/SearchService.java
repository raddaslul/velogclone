package com.sparta.velogclone.service;

import com.sparta.velogclone.domain.Post;
import com.sparta.velogclone.dto.requestdto.SearchRequestDto;
import com.sparta.velogclone.dto.responsedto.PostResponseDto;
import com.sparta.velogclone.repository.PostRepository;
import com.sparta.velogclone.specification.PostSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

//        List<Post> posts = postRepository.findAllByTitleContainingIgnoreCase(searchRequestDto.getSearchWord());

        Specification<Post> spec = Specification.where(PostSpecification.equalTitle(searchRequestDto.getSearch()));
        if(searchRequestDto.getSearch() != null) {
            spec = spec.and(PostSpecification.equalTitle(searchRequestDto.getSearch()));
            spec = spec.or(PostSpecification.equalContent(searchRequestDto.getSearch()));
        }
        List<Post> posts = postRepository.findAll(spec);

        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        if (posts.isEmpty()) {
            return postResponseDtoList;
        }

        for (Post post : posts) {
            String postModifiedAt = post.getModifiedAt().toString();
            String year = postModifiedAt.substring(0,4) + "년";
            String month = postModifiedAt.substring(5,7) + "월";
            String day = postModifiedAt.substring(8,10) + "일";
            postModifiedAt = year + month + day;

            PostResponseDto postResponseDto = new PostResponseDto(post,
                    post.getComments().size(),
                    post.getLikeses().size(),
                    postModifiedAt);

            postResponseDtoList.add(postResponseDto);
        }

        return postResponseDtoList;
    }
}
