package com.batis.application.controller.ugc;

import com.batis.application.entity.ugc.Post;
import com.batis.application.repository.jpa.base.DeviceInfoRepository;
import com.batis.application.repository.elastic.EsPostRepository;
import com.batis.application.repository.jpa.management.UserRepository;
import com.batis.application.repository.jpa.ugc.PostRepository;
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
    UserRepository userRepository;
    @Autowired
    DeviceInfoRepository deviceInfoRepository;
    @Autowired
    EsPostRepository esPostRepository;

    @GetMapping("/")
    public Page<Post> getPagedPost(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @GetMapping("/query")
    public List<Post> queryPagedPost(String keywords) {
        return esPostRepository.findAllByKeywords(keywords);
    }

    @PostMapping("/")
    public List<Post> addPost(@RequestBody List<Post> posts) {
        posts.forEach(it -> {
            it.setAuthor(userRepository.findOrCreate(it.getAuthor()));
            it.setDeviceInfo(deviceInfoRepository.findOrCreate(it.getDeviceInfo()));
        });
        postRepository.saveAll(posts);
        esPostRepository.saveAll(posts);
        return posts;
    }
}
