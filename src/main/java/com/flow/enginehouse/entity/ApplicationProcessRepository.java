package com.flow.enginehouse.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationProcessRepository extends JpaRepository<ApplicationProcess, Long>{


}