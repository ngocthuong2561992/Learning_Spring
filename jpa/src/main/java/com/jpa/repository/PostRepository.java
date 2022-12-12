package com.jpa.repository;

import com.jpa.entity.relationship.ActorEntity;
import com.jpa.entity.relationship.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

}
