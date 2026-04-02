package com.janaprasath.scholarstack.repository;

import com.janaprasath.scholarstack.entity.AcademicResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<AcademicResource, Long> {

    java.util.List<AcademicResource> findBySubjectCode(String subjectCode);

    java.util.List<AcademicResource> findByUploaderEmail(String email);
}
