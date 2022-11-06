package com.grh.pfeprv.repository;

import com.grh.pfeprv.domaine.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long> {

    List<Contrat> findAllByDeletedIsFalse();
    Contrat findByEmployee_Jobid(String jobid);
    Contrat findAllByCode(String code);

}
