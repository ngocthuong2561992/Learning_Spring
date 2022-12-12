package com.jpa.repository;

import com.jpa.entity.relationship.PostDetailEntity;
import com.jpa.entity.relationship.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDetailRepository extends JpaRepository<PostDetailEntity, Integer> {

}
