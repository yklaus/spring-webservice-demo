package io.github.yklaus.webservice.service;

import io.github.yklaus.webservice.domain.posts.PostsRepository;
import io.github.yklaus.webservice.dto.posts.PostsMainResponseDto;
import io.github.yklaus.webservice.dto.posts.PostsSaveRequestDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PostsService {

  private PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto dto) {
    return postsRepository.save(dto.toEntity()).getId();
  }

  @Transactional(readOnly = true)
  public List<PostsMainResponseDto> findAllDesc() {
    return postsRepository.findAllDesc()
        .map(PostsMainResponseDto::new)
        .collect(Collectors.toList());
  }
}
