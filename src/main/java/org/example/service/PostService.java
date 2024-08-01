package org.example.service;


import org.example.model.Post;
import org.example.repository.PostRepositoryImpl;
import org.example.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepositoryImpl repository;

    public PostService(PostRepositoryImpl repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        Optional<Post> post = repository.getById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new NotFoundException();
        }
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}

