package io.github.yklaus.webservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.yklaus.webservice.domain.posts.Posts;
import io.github.yklaus.webservice.domain.posts.PostsRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostsRepositoryTest {

  @Autowired
  PostsRepository postsRepository;

  @AfterEach
  void cleanup() {
    /*
     이후 테스트 코드에 영향을 끼치지 않기 위해
     테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
     */
    postsRepository.deleteAll();
  }

  @Test
  void 게시글저장_불러오기() {
    //given
    postsRepository.save(Posts.builder()
        .title("테스트 게시글")
        .content("테스트 본문")
        .author("jojoldu@gmail.com")
        .build());

    //when
    List<Posts> postsList = postsRepository.findAll();

    //then
    Posts posts = postsList.get(0);
    assertThat(posts.getTitle()).isEqualTo("테스트 게시글");
    assertThat(posts.getContent()).isEqualTo("테스트 본문");
  }

  @Test
  void BaseTimeEntity_등록() {
    //given
    LocalDateTime now = LocalDateTime.now();
    postsRepository.save(Posts.builder()
        .title("테스트 게시글")
        .content("테스트 본문")
        .author("jojoldu@gmail.com")
        .build());
    //when
    List<Posts> postsList = postsRepository.findAll();

    //then
    Posts posts = postsList.get(0);
    assertThat(posts.getCreatedDate()).isAfter(now);
    assertThat(posts.getModifiedDate()).isAfter(now);
  }
}
