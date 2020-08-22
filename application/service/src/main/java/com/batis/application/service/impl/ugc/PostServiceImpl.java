package com.batis.application.service.impl.ugc;

import com.batis.application.database.entity.ugc.Post;
import com.batis.application.database.repository.elastic.EsPostRepository;
import com.batis.application.database.repository.jpa.base.DeviceInfoRepository;
import com.batis.application.database.repository.jpa.management.UserRepository;
import com.batis.application.database.repository.jpa.ugc.PostRepository;
import com.batis.application.service.ugc.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DeviceInfoRepository deviceInfoRepository;
    @Autowired
    EsPostRepository esPostRepository;

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post save(Post post) {
        esPostRepository.save(post);
        return postRepository.save(post);
    }

    @Override
    public List<Post> saveAll(List<Post> posts) {
        posts.forEach(it -> {
            it.setAuthor(userRepository.findOrCreate(it.getAuthor()));
            it.setDeviceInfo(deviceInfoRepository.findOrCreate(it.getDeviceInfo()));
            it.setParent(it.getParent() == null ? null : postRepository.findById(it.getParent().getId()).get());
        });
        postRepository.saveAll(posts);
        esPostRepository.saveAll(posts);
        return posts;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<Post> findAllByKeywords(String keywords) {
        return esPostRepository.findAllByKeywords(keywords);
    }
}
