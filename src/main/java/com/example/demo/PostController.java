package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @PostMapping("")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        post.setBody(post.getBody());
        post.setTitle(post.getTitle());
        post.setUserId(post.getUserId());
        postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Post>> getPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postRepository.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        Post post = postRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long id) {
        postRepository.deleteById(Math.toIntExact(id));
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        Post editedPost = postRepository.findById(Math.toIntExact(id))
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setBody(updatedPost.getBody());
                    post.setUserId(updatedPost.getUserId());
                    return postRepository.save(post);
                })
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
        return ResponseEntity.status(HttpStatus.OK).body(editedPost);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<Post> patchPost(@PathVariable Long id, @RequestBody Post updatedPost) {
//        Post editedPost = postRepository.findById(Math.toIntExact(id))
//                .map(post -> {
//                    post.setTitle(updatedPost.getTitle());
//                    post.setBody(updatedPost.getBody());
//                    return postRepository.save(post);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
//        return ResponseEntity.status(HttpStatus.OK).body(editedPost);
//    }
}
