package com.vickylin.propertyweb.service;

import com.vickylin.propertyweb.entity.Property;
import com.vickylin.propertyweb.repository.PropertyRepository;
import com.vickylin.propertyweb.utils.NullAwareBeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private NullAwareBeanUtilsBean beanUtils;

    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    public List<Property> getProperties(String key) {
        if(StringUtils.isNotBlank(key)) {
            return propertyRepository.findAllByAddressContainingOrTypeContainingOrPurposeContaining(key, key , key);
        } else {
            return propertyRepository.findAll();
        }
    }

    public List<Property> getPropertiesByPrice(int maxPrice, int minPrice) {
        return propertyRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    public Property updateProperty(Long id, Property newProperty) throws Exception {
        Optional<Property> opt = propertyRepository.findById(id);
        if(opt.isPresent()) {
            Property originalProperty = opt.get();
            beanUtils.copyProperties(originalProperty, newProperty);
            return propertyRepository.save(originalProperty);
        } else {
            throw new Exception("Resource not found.");
        }
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
