package com.feg.fileRouge.Repository;

import com.feg.fileRouge.Entity.Model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryServices extends JpaRepository<Service,Long> {
}
