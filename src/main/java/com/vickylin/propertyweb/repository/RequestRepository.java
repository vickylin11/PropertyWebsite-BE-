package com.vickylin.propertyweb.repository;

import com.vickylin.propertyweb.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    Optional<Request> findById(Long id);

    Page<Request> findAllByUserId(Pageable pageable, Long userId);
}
