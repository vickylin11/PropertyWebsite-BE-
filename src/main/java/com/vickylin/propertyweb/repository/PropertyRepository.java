package com.vickylin.propertyweb.repository;

import com.vickylin.propertyweb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> findById(Long id);

    List<Property> findAllByAddressContainingOrTypeContainingOrPurposeContaining(String addressKey, String typeKey, String purposeKey);

    List<Property> findAllByPriceBetween(int minPrice, int maxPrice);
}
