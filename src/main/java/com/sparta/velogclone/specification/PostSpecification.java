package com.sparta.velogclone.specification;

import com.sparta.velogclone.domain.Post;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification{

    public static Specification<Post> equalTitle(String searchWord) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("title"), searchWord);
    }

    public static Specification<Post> equalContent(String searchWord) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("content"), searchWord);
    }
}
