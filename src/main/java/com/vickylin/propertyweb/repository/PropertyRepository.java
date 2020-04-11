package com.vickylin.propertyweb.repository;

import com.vickylin.propertyweb.entity.Property;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> findById(Long id);

    Page<Property> findAllByAddressContainingOrTypeContainingOrPurposeContaining(String addressKey, String typeKey, String purposeKey, Pageable pageable);

    List<Property> findAllByPriceBetween(int minPrice, int maxPrice);

    Page<Property> findAllByType(String type, Pageable pageable);
}
