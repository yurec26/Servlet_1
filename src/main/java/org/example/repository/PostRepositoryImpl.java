package org.example.repository;


import org.example.model.Post;

import java.util.*;

public class PostRepositoryImpl implements PostRepository {
    public static long postCounter = 0;
    private List<Post> posts = Collections.synchronizedList(new ArrayList<>());

    public List<Post> all() {
        return posts;
    }

    public Optional<Post> getById(long id) {
        for (Post p : posts) {
            if (p.getId() == id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            postCounter++;
            post.setId(postCounter);
            posts.add(post);
            return post;

        } else {
            for (Post e : posts) {
                if (e.getId() == post.getId()) {
                    e.setContent(post.getContent());
                    return e;
                }
            }

            return new Post(-404, "Post not found"); // просто для примера.
        }
    }

    public void removeById(long id) {
        posts.removeIf(p -> p.getId() == id);
    }
}

