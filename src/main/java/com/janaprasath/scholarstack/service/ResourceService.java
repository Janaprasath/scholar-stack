package com.janaprasath.scholarstack.service;

import com.janaprasath.scholarstack.entity.AcademicResource;
import com.janaprasath.scholarstack.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public AcademicResource uploadResource(AcademicResource resource) {
        return resourceRepository.save(resource);
    }
    public java.util.List<AcademicResource> getAllResources() {
        return resourceRepository.findAll();
    }
    public java.util.List<AcademicResource> getResourcesBySubject(String code) {
        return resourceRepository.findBySubjectCode(code);
    }

    public java.util.List<AcademicResource> getMyUploads(String email) {
        return resourceRepository.findByUploaderEmail(email);
    }
}