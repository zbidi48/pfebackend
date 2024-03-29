package com.grh.pfeprv.repository;

import com.grh.pfeprv.domaine.Fichedepaie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FichedepaieRepository extends JpaRepository<Fichedepaie,Long> {

   //List<Fichedepaie> findAllByUser_Id(Long id);
   //List<Fichedepaie> findAllByUser_NomAndUser_Prenom(String nom,String prenom);
   List<Fichedepaie> findByEmployee_IdAndSupprIsFalse(Long id);

   List<Fichedepaie> findAllBySupprIsFalse();

   List<Fichedepaie> findAllByEmployee_NomOrEmployee_PrenomOrEmployee_JobidAndSupprIsFalse(String nom,String prenom,String jobid);
}
