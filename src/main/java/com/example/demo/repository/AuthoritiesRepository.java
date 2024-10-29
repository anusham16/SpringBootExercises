package com.example.demo.repository;

import com.example.demo.entity.Authorities;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {


}
