package io.github.yklaus.webservice;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.yklaus.webservice.domain.posts.Posts;
import io.github.yklaus.webservice.domain.posts.PostsRepository;
import io.github.yklaus.webservice.dto.posts.PostsSaveRequestDto;
import io.github.yklaus.webservice.service.PostsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

  @Autowired
  private PostsService postsService;

  @Autowired
  private PostsRepository postsRepository;

  @AfterEach
  void cleanup() {
    postsRepository.deleteAll();
  }

  @Test
  void Dto데이터가_posts테이블에_저장된다() {
    //given
    PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
        .author("jojoldu@gmail.com")
        .content("테스트")
        .title("테스트 타이틀")
        .build();

    //when
    postsService.save(dto);

    //then
    Posts posts = postsRepository.findAll().get(0);
    assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
    assertThat(posts.getContent()).isEqualTo(dto.getContent());
    assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
  }
}
