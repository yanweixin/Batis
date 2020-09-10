package com.batis.application.service.ugc;

import com.batis.application.database.entity.ugc.Content;
import com.batis.application.database.entity.ugc.Post;
import com.batis.application.database.repository.elastic.ContentRepository;
import com.batis.application.database.repository.jpa.base.DeviceInfoRepository;
import com.batis.application.database.repository.jpa.management.UserRepository;
import com.batis.application.database.repository.jpa.ugc.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DeviceInfoRepository deviceInfoRepository;
    @Autowired
    ContentRepository contentRepository;

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
        contentRepository.save(post.getContent());
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
        contentRepository.saveAll(
                posts.stream().map(Post::getContent)
                        .collect(Collectors.toList())
        );
        return posts;
    }

    @Override
    public int deleteById(Long id) {
        if (postRepository.existsById(id)) {
            contentRepository.deleteById(findById(id).getId());
            postRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Post> findAllByKeywords(String keywords) {
        List<Content> contents = contentRepository.findAllByKeywords(keywords);
        return contents.stream()
                .map(Content::getId)
                .map(id -> postRepository.findByContentId(id).get())
                .collect(Collectors.toList());
    }
}
