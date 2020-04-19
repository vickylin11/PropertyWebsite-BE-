package com.vickylin.propertyweb.controller;

import com.vickylin.propertyweb.entity.Property;
import com.vickylin.propertyweb.entity.User;
import com.vickylin.propertyweb.exception.ResourceNotFoundException;
import com.vickylin.propertyweb.exception.UserPermissionException;
import com.vickylin.propertyweb.service.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public Property addProperty(
            @Valid @RequestBody Property property) {
        return propertyService.addProperty(property);
    }

    @GetMapping
    public Page<Property> getProperties(
            @RequestParam(value = "q", required = false) String key, Pageable pageable) {
        return  propertyService.getProperties(key, pageable);
    }

    @GetMapping(path = "/price")
    public List<Property> getPropertiesByPrice(
            @RequestParam int max, int min) {
        return propertyService.getPropertiesByPrice(max, min);
    }

    @GetMapping(path = "/{id}")
    public Property getPropertyById(
            @PathVariable Long id) throws ResourceNotFoundException {
        return propertyService.getPropertyById(id);
    }

    @PatchMapping(path = "/{id}")
    public Property updateProperty(
            @PathVariable Long id,
            @RequestBody Property property,
            @RequestHeader(value = "user_type") String userType) throws Exception {
        if(!userType.equals(User.UserType.admin.name())) {
            throw new UserPermissionException();
        }
        return propertyService.updateProperty(id, property);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProperty(
            @PathVariable Long id,
            @RequestHeader(value = "user_type") String userType) throws Exception {
        if(!userType.equals(User.UserType.admin.name())) {
            throw new UserPermissionException();
        }
        propertyService.deleteProperty(id);
    }

    @GetMapping("/type")
    public Page<Property> getPropertiesByType(
            @RequestParam String type, Pageable pageable) {
        return propertyService.getPropertiesByType(type, pageable);
    }
}
