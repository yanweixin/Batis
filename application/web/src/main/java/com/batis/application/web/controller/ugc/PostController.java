package com.batis.application.web.controller.ugc;

import com.batis.application.database.entity.ugc.Post;
import com.batis.application.service.ugc.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ugc/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/")
    public Page<Post> getPagedPost(Pageable pageable) {
        return postService.findAll(pageable);
    }

    @GetMapping("/query")
    public List<Post> queryPagedPost(String keywords) {
        return postService.findAllByKeywords(keywords);
    }

    @DeleteMapping("/{id}")
    public int deletePostById(@PathVariable("id") Long id) {
        return postService.deleteById(id);
    }

    @PostMapping("/")
    public List<Post> addPost(@RequestBody List<Post> posts) {
        return postService.saveAll(posts);
    }
}
