package com.janaprasath.scholarstack.controller;

import com.janaprasath.scholarstack.entity.AcademicResource;
import com.janaprasath.scholarstack.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private com.janaprasath.scholarstack.repository.UserRepository userRepository;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestBody AcademicResource resource) {
        // 1. Get the email from Security Context
        String email = (String) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        // 2. Map the user to the resource and save
        return userRepository.findByEmail(email).<ResponseEntity<?>>map(user -> {
            resource.setUploader(user);
            resource.setUploadDate(java.time.LocalDateTime.now());

            AcademicResource saved = resourceService.uploadResource(resource);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.status(404).body("User not found"));
    }
    @GetMapping("/all")
    public ResponseEntity<java.util.List<AcademicResource>> getAllResources() {
        return ResponseEntity.ok(resourceService.getAllResources());
    }

    @GetMapping("/subject/{code}")
    public ResponseEntity<java.util.List<AcademicResource>> getBySubject(@PathVariable String code) {
        return ResponseEntity.ok(resourceService.getResourcesBySubject(code));
    }

    @GetMapping("/my-uploads")
    public ResponseEntity<java.util.List<AcademicResource>> getMyOwnResources() {
        // Extract the email of the person currently logged in from the Security Token
        String email = (String) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(resourceService.getMyUploads(email));
    }
}