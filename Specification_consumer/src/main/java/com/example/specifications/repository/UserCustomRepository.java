package com.example.specifications.repository;

import com.example.specifications.model.entity.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCustomRepository
        extends JpaRepository<UserCustom, Long>, JpaSpecificationExecutor<UserCustom> {
}
