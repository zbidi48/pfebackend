package com.grh.pfeprv.service;

import com.grh.pfeprv.domaine.Contrat;
import com.grh.pfeprv.payloads.request.ContratRequest;

import com.grh.pfeprv.payloads.response.ContratResponse;
import com.grh.pfeprv.payloads.response.MessageResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

public interface IContratService {
    public  List<Contrat> Afiichagecontratsimple();
    public List<ContratResponse> affichercontart();
    public ResponseEntity<MessageResponse> Ajoucontrat(ContratRequest c);
    public ResponseEntity<MessageResponse> MAcontrat(Long id,ContratRequest c);
    public ResponseEntity<MessageResponse> Effcontrat(Long id);

    public Contrat Affcontratid(Long id);


    public ResponseEntity<List<ContratResponse>> Affichercontratparemplid(Long emplid);
    public List<ContratResponse> recherchercontrat(String query);

    public ResponseEntity<MessageResponse> exportcontratpdf(Long id,Long emplid) throws FileNotFoundException,
            JRException, MalformedURLException;
}
