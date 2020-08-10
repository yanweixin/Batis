package com.batis.application.controller.ugc;

import com.batis.application.entity.ugc.Post;
import com.batis.application.repository.elastic.EsPostRepository;
import com.batis.application.repository.ugc.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ugc/post")
public class PostController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    EsPostRepository esPostRepository;

    @GetMapping("/")
    public Page<Post> getPagedPost(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @PostMapping("/")
    public List<Post> addPost(@RequestBody List<Post> posts) {
        return postRepository.saveAll(posts);
    }
}
