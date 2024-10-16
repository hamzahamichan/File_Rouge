package com.feg.fileRouge.Repository;

import com.feg.fileRouge.Entity.Model.Client;
import com.feg.fileRouge.Entity.Model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalleRepository extends JpaRepository<Salle,Long> {

    @Query("SELECT s FROM Salle s WHERE " +
            "(:nom IS NULL OR s.nom LIKE %:nom%) AND " +
            "(:emplacement IS NULL OR s.emplacement LIKE %:emplacement%)")
    List<Salle> findByCriteria(@Param("nom") String nom,
                               @Param("emplacement") String emplacement);

    List<Salle> findByClient(Client client);
}
