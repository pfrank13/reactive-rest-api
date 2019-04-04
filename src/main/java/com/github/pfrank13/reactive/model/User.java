package com.github.pfrank13.reactive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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
@Table("user")
public class User {
  @Id
  private Integer id;
  private String name;
}
