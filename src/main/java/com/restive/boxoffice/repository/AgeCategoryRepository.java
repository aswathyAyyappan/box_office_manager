package com.restive.boxoffice.repository;

import com.restive.boxoffice.entity.AgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgeCategoryRepository extends JpaRepository<AgeCategory, Long> {
    @Query("SELECT a FROM AgeCategory a WHERE :age BETWEEN a.minAge AND COALESCE(a.maxAge, :age)")
    List<AgeCategory> findAgeCategoryForAge(@Param("age") Integer age);
}
