package com.example.demo;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.ResourceNotFoundException;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
//        return postRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
//        return ResponseEntity.ok(post);
    }

//    @PostMapping
//    public Post createPost(@RequestBody Post post) {
//        return postRepository.save(post);
//    }
//
//    @PutMapping("/{id}")
//    public Post updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
//        return postRepository.findById(id)
//                .map(post -> {
//                    post.setTitle(updatedPost.getTitle());
//                    post.setContent(updatedPost.getContent());
//                    return postRepository.save(post);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletePost(@PathVariable Long id) {
//        postRepository.deleteById(id);
//    }
}
