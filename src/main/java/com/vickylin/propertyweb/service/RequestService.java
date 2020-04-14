package com.vickylin.propertyweb.service;

import com.vickylin.propertyweb.entity.Request;
import com.vickylin.propertyweb.exception.ResourceNotFoundException;
import com.vickylin.propertyweb.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request createRequest(Request request) {
        request.setCreatedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }

    public Request getRequest(Long id) throws ResourceNotFoundException {
        Optional<Request> opt = requestRepository.findById(id);

        if(!opt.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return opt.get();
    }

    public Page<Request> getRequests(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

    public Request resolveRequest(Long id) throws ResourceNotFoundException {
        Request request = getRequest(id);
        request.setResolved(true);
        return requestRepository.save(request);
    }
}
