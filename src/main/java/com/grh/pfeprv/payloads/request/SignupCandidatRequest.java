package com.grh.pfeprv.payloads.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
@Data
public class SignupCandidatRequest {
    private String email;
    private String password;
    private  String nom;
    private  String prenom;
    private  String post;
    private String competences;
    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date dateOfBirth;
    private String phone;
    private String adresse ;
    private String ville;
    private String nationality;
    private String niveauEtud;
    private String titreDiplome;
    private String university;
    private String niveauExp;

    private String cin;
    private Boolean suppr;
}
