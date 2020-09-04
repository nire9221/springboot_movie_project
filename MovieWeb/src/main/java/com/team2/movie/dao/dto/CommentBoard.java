package com.team2.movie.dao.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name="CommentBoard")
public class CommentBoard {

   @Id
   @GeneratedValue
   @Column(name="commentId")
   private long commentId;
   
   @Column(name="kakaoId")
   @NonNull
   private String kakaoId;
   
   @Column(name="stars")
   private double stars;
   
   
   @Column(name="contents", columnDefinition = "TEXT", nullable=false)
   private String contents;
   
   
}