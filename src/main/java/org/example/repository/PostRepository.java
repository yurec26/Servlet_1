package org.example.repository;


import org.example.model.Post;

import java.util.*;

public interface PostRepository {


    public List<Post> all();

    public Optional<Post> getById(long id);

    public Post save(Post post);

    public void removeById(long id);
}

