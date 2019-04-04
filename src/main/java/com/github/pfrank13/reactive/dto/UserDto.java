package com.github.pfrank13.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * @author pfrank
 */
@Data
@AllArgsConstructor
@Builder
public class UserDto {
  private Integer id;
  private String name;
}
