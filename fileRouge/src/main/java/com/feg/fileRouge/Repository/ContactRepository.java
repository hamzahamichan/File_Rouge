package com.feg.fileRouge.Repository;

import com.feg.fileRouge.Entity.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
