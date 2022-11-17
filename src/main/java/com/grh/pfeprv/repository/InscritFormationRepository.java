package com.grh.pfeprv.repository;

import com.grh.pfeprv.domaine.InscritFormation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscritFormationRepository extends JpaRepository<InscritFormation,Long> {
    List<InscritFormation> findAllBySupprIsFalse();
    List<InscritFormation> findByEmployee_JobidAndSupprIsFalse(String jobid );
    List<InscritFormation> findByEmployee_NomAndAndEmployee_PrenomAndAndSupprIsFalse(String nom,String prenom);
    List<InscritFormation> findByEmployee_Email(String mail);
    List<InscritFormation> findByEmployee_IdAndSupprIsFalse(Long id);

}
