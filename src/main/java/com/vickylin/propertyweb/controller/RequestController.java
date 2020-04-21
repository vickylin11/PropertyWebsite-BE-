package com.vickylin.propertyweb.controller;

import com.vickylin.propertyweb.entity.Request;
import com.vickylin.propertyweb.entity.User;
import com.vickylin.propertyweb.exception.ResourceNotFoundException;
import com.vickylin.propertyweb.exception.UserPermissionException;
import com.vickylin.propertyweb.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping
    public Request createRequest(
            @RequestBody Request request) {
        return requestService.createRequest(request);
    }

    @GetMapping(path = "{id}")
    public Request getRequest(
            @PathVariable Long id) throws ResourceNotFoundException {
        return requestService.getRequest(id);
    }

    @GetMapping
    public Page<Request> getRequests(
            @RequestParam int size, int page,
            @RequestHeader(value = "user_type") String userType) throws UserPermissionException {
        Pageable pageable = PageRequest.of(page, size);
        if(!userType.equals(User.UserType.admin.name())) {
            throw new UserPermissionException();
        }
        return requestService.getRequests(pageable);
    }

    @GetMapping(path = "/my-requests")
    public Page<Request> getMyRequests(
            @RequestParam int size, int page, Long userId) {
        Pageable pageable= PageRequest.of(page, size);
        return requestService.getRequestsByUserId(pageable, userId);
    }

    @PostMapping(path = "/{id}/action/resolve")
    public Request resolveRequest(
            @RequestHeader(value = "user_type") String userType,
            @PathVariable Long id) throws UserPermissionException, ResourceNotFoundException {
        if(!userType.equals(User.UserType.admin.name())) {
            throw new UserPermissionException();
        }
        return requestService.resolveRequest(id);
    }

}
