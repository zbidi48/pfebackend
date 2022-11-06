package com.grh.pfeprv.domaine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "entretient")
public class Entretient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String heure;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "condidats_id", referencedColumnName = "id")
    private Condidats condidat;

}