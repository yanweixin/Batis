package com.batis.application.service.ugc;

import com.batis.application.database.entity.ugc.Post;
import com.batis.application.service.CommonService;

import java.util.List;

public interface PostService extends CommonService<Post> {
    List<Post> findAllByKeywords(String keywords);
}
