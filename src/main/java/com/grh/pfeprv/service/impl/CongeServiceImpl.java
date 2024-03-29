package com.grh.pfeprv.service.impl;

import com.grh.pfeprv.domaine.Conge;
import com.grh.pfeprv.domaine.Employee;

import com.grh.pfeprv.enums.EStatusConge;
import com.grh.pfeprv.enums.EStatusVisa;
import com.grh.pfeprv.exception.NotFoundException;
import com.grh.pfeprv.payloads.request.CongeRequest;
import com.grh.pfeprv.payloads.response.CongeResponse;
import com.grh.pfeprv.payloads.response.MessageResponse;
import com.grh.pfeprv.repository.CongeRepository;
import com.grh.pfeprv.repository.EmployeeRepository;
import com.grh.pfeprv.service.ICongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CongeServiceImpl implements ICongeService {
    @Autowired
    CongeRepository congeRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<CongeResponse> afficherconge() {
        List<CongeResponse> responses = new ArrayList<>();
        congeRepository.findBySupprIsFalse().forEach(
                conge -> {
                    responses.add(new CongeResponse(
                            conge.getId(),
                            conge.getDatedebut(),
                            conge.getDatefin(),
                            conge.getStatusConge(),
                            conge.getTypeConge(),
                            conge.getEmployee().getNom(),
                            conge.getEmployee().getPrenom(),
                            conge.getEmployee().getPost(),
                            conge.getEmployee().getJobid()));
                }
        );

        return responses;
    }


    @Override
    public ResponseEntity<MessageResponse> ajoutdemandeconge(CongeRequest congeRequest) {
        Conge conge = new Conge();
        Optional<Employee> employee=employeeRepository.findById(congeRequest.getEmployee_id());
        if(!employee.isPresent())
        {
            throw new NotFoundException("Ajout impossible");
        }
        Employee employee1 =employee.get();
        conge.setDatedebut(congeRequest.getDatedebut());
        conge.setDatefin(congeRequest.getDatefin());
        conge.setStatusConge(EStatusConge.DEMANDEENCOUR);
        conge.setSuppr(false);
       /*
        if(congeRequest.getTypeConge().equals("anuelle"))
        {
            conge.setTypeConge(ETypeConge.ANUELLE);
        }
        if(congeRequest.getTypeConge().equals("maladie"))
        {
            conge.setTypeConge(ETypeConge.MALADIE);
        }
        if(congeRequest.getTypeConge().equals("maternité"))
        {
            conge.setTypeConge(ETypeConge.MATERNITE);
        }
        */
        conge.setTypeConge(congeRequest.getTypeConge());
        conge.setEmployee(employee1);
        congeRepository.save(conge);
        return   ResponseEntity.ok(new MessageResponse("demande congé ajouter avec succeé"));
    }

    @Override
    public ResponseEntity<MessageResponse> miseajourconge(Long id, CongeRequest congeRequest) {
        Optional<Conge> conge=congeRepository.findById(id);
        if(!conge.isPresent())
        {
            throw new NotFoundException("congé ID: "+id+" not found");
        }
        Conge conge1= conge.get();
        conge1.setDatedebut(congeRequest.getDatedebut());
        conge1.setDatefin(congeRequest.getDatefin());
        conge1.setTypeConge(congeRequest.getTypeConge());
        congeRepository.save(conge1);
        return   ResponseEntity.ok(new MessageResponse("congé modifier avec succeé"));
    }

    @Override
    public ResponseEntity<MessageResponse> supprimerconge(Long id) {
        Optional<Conge> conge= congeRepository.findById(id);
        if(!conge.isPresent())
        {
            throw new NotFoundException("congé ID: "+id+" not found");
        }
        Conge conge1 =conge.get();
        conge1.setSuppr(true);
        congeRepository.save(conge1);
       return ResponseEntity.ok(new MessageResponse("congé supprimer avec succeé"));
    }

    @Override
    public Conge detailconge(Long id) {
        Optional<Conge> conge= congeRepository.findById(id);
        if(!conge.isPresent())
        {
            throw new NotFoundException("congé ID: "+id+" not found");
        }


        return conge.get();
    }


    @Override
    public List<CongeResponse> affichercongeparmail(String mail) {
        List<CongeResponse> responses=new ArrayList<>();
        congeRepository.findByEmployee_Email(mail).forEach(conge -> {
            responses.add(new CongeResponse(
                    conge.getId(),
                    conge.getDatedebut(),
                    conge.getDatefin(),
                    conge.getStatusConge(),
                    conge.getTypeConge(),
                    conge.getEmployee().getNom(),
                    conge.getEmployee().getPrenom(),
                    conge.getEmployee().getPost(),
                    conge.getEmployee().getJobid()));
        });

        return responses;
    }

    @Override
    public ResponseEntity<MessageResponse> accordconge(Long id, String status) {
        Optional<Conge> conge=congeRepository.findById(id);
        if(!conge.isPresent())
        {
            throw new NotFoundException("congé ID: "+id+" not found");
        }
        Conge conge1= conge.get();
        /*
        if (congeRequest.getStatusConge().equals("accepte"))
        {
            conge1.setStatusConge(EStatusConge.ACCEPTEDEMANDECONGE);
        }
        if (congeRequest.getStatusConge().equals("refus"))
        {
            conge1.setStatusConge(EStatusConge.REFUSEDEMANDECONGE);
        }
         */
        if(status.equals("accepte")){
            conge1.setStatusConge(EStatusConge.ACCEPTEDEMANDE);
        } else {
            conge1.setStatusConge(EStatusConge.REFUSEDEMANDE);
        }
        congeRepository.save(conge1);
        return ResponseEntity.ok(new MessageResponse("etat congé valide"));
    }

    @Override
    public List<CongeResponse> rechercheconge(String query) {
        List<CongeResponse> responses=new ArrayList<>();
        congeRepository.
              findAllByEmployee_NomAndSupprIsFalseOrEmployee_PrenomAndSupprIsFalseOrEmployee_JobidAndSupprIsFalse(query,query,query).
                forEach(conge -> {
            responses.add(new CongeResponse(
                    conge.getId(),
                    conge.getDatedebut(),
                    conge.getDatefin(),
                    conge.getStatusConge(),
                    conge.getTypeConge(),
                    conge.getEmployee().getNom(),
                    conge.getEmployee().getPrenom(),
                    conge.getEmployee().getPost(),
                    conge.getEmployee().getJobid()));
        });

        return responses;
    }
}
