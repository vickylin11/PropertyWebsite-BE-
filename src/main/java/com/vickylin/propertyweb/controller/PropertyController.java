package com.vickylin.propertyweb.controller;

import com.vickylin.propertyweb.entity.Property;
import com.vickylin.propertyweb.service.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<Property> getProperties(
            @RequestParam(value = "q", required = false) String key) {
        return  propertyService.getProperties(key);
    }

    @GetMapping("/price")
    public List<Property> getPropertiesByPrice(
            @RequestParam int max, @RequestParam int min) {
        return propertyService.getPropertiesByPrice(max, min);
    }

    @PatchMapping(path = "/{id}")
    public Property updateProperty(
            @PathVariable Long id,
            @RequestBody Property property) throws Exception {
        return propertyService.updateProperty(id, property);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProperty(
            @PathVariable Long id) {
        propertyService.deleteProperty(id);
    }
}
