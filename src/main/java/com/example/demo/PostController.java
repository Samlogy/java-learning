package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @PostMapping("")
    public Post createPost(@RequestBody Post post) {
        post.setBody(post.getBody());
        post.setTitle(post.getTitle());
        post.setUserId(post.getUserId());
        postRepository.save(post);
        return post;
    }

    @GetMapping("")
    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Integer id) {
        return postRepository.findPostById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(Math.toIntExact(id));
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        return postRepository.findById(Math.toIntExact(id))
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setBody(updatedPost.getBody());
                    return postRepository.save(post);
                })
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
    }

//    @PatchMapping("/{id}")
//    public Post patchPost(@PathVariable Long id, @RequestBody Post updatedPost) {
//        return postRepository.findById(Math.toIntExact(id))
//                .map(post -> {
//                    post.setTitle(updatedPost.getTitle());
//                    post.setBody(updatedPost.getBody());
//                    return postRepository.save(post);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
//    }
}
