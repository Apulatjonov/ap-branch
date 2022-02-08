package com.example.apbranch.workplace;

import com.example.apbranch.entity.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, UUID> {
}
