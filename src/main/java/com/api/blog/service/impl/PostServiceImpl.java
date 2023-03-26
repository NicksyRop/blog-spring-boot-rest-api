package com.api.blog.service.impl;

import com.api.blog.entity.Post;
import com.api.blog.exception.ResourceNotFoundException;
import com.api.blog.payload.PostDto;
import com.api.blog.payload.PostResponse;
import com.api.blog.respository.PostRepository;
import com.api.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert DTO to entity
        Post post = mapToEntity(postDto);
        Post newPost =   postRepository.save(post);

        //convert entity to DTO
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo , int pageSize , String sortBy) {

        Pageable pageable = PageRequest.of( pageNo,pageSize , Sort.by(sortBy));
       Page<Post> posts = postRepository.findAll(pageable);

       //get content from page object

        List<Post>  listOfPost = posts.getContent();
       //fetch list of posts then convert it to a list of DTO by using map then call the mapToDto method then collect the result
       List<PostDto> content = listOfPost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse  postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return  postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
      Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

      return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {

        //get post from database
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

        postRepository.delete(post);
    }


    // method to convert Entity to Dto
    private PostDto mapToDto(Post post){

        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        postDto.setTitle(post.getTitle());

        return  postDto;
    }
    //method to convert Dto to entity
    private Post mapToEntity(PostDto postDto){

        Post post = new Post();

        post.setId(postDto.getId());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());

        return post;

    }
}
