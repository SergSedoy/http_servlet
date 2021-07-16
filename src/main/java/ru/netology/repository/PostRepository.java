package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;

// Stub
public class PostRepository {
    private static List<Post> posts = new ArrayList<>();
    private static long id;

    public List<Post> all() {
        return posts;
    }

    public synchronized Optional<Post> getById(long id) {
        return posts.get(id);
    }

    public synchronized Post save(Post post) {
        if (post.getId() == 0) {
            long newId = id++;
            post.setId(newId);
            posts.set((int) newId, post);
        }
        if (post.getId() != 0) {
            try {
                post = posts.get((int) post.getId());
            } catch (Exception e) {
                post.setId(0);
                return save(post);
            }
        }
        return post;
    }

    public synchronized void removeById(long id) {
        posts.set(id, null);
    }
}
