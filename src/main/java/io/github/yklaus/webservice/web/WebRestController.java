package io.github.yklaus.webservice.web;

import io.github.yklaus.webservice.dto.posts.PostsSaveRequestDto;
import io.github.yklaus.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebRestController {

  private PostsService postsService;

  @GetMapping("/hello")
  public String hello() {
    return "HelloWorld";
  }

  @PostMapping("/posts")
  public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
    return postsService.save(dto);
  }

}
