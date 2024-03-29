package com.grh.pfeprv.domaine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="employee",uniqueConstraints = {@UniqueConstraint(columnNames = "jobid")} )
@DiscriminatorValue("employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends  User{
    private static final long serialVersionUID = 1L;
    private String status;
    private String salary;
    private String cnss;
    private String departement;
    private String jobid;
    private Boolean suppr;
    @JsonIgnore
    @OneToOne(mappedBy = "employee")
    private  Contrat contrat;
    @JsonIgnore
    @OneToMany(targetEntity =Fichedepaie.class,mappedBy ="employee")
    private List<Fichedepaie> fichedepaies = new ArrayList<>();
    @JsonIgnore
    @OneToMany(targetEntity =Conge.class,mappedBy ="employee")
    private  List<Conge> conges= new ArrayList<>();
    @JsonIgnore
    @OneToMany(targetEntity =Notedefraie.class,mappedBy ="employee")
    private  List<Notedefraie> notedefraies= new ArrayList<>();
    @JsonIgnore
    @OneToMany(targetEntity =Visa.class,mappedBy ="employee")
    private List<Visa> visas = new ArrayList<>();


}
