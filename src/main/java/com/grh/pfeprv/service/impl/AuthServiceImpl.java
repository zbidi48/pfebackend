package com.grh.pfeprv.service.impl;


import com.grh.pfeprv.enums.ERole;
import com.grh.pfeprv.payloads.request.LoginRequest;
import com.grh.pfeprv.payloads.request.SignupRequest;
import com.grh.pfeprv.payloads.response.JwtResponse;
import com.grh.pfeprv.payloads.response.MessageResponse;
import com.grh.pfeprv.repository.RoleRepository;
import com.grh.pfeprv.repository.UserRepository;
import com.grh.pfeprv.security.jwt.JwtUtils;
import com.grh.pfeprv.service.IAuthService;
import com.grh.pfeprv.service.UserDetailsImpl;
import com.grh.pfeprv.domaine.Condidats;
import com.grh.pfeprv.domaine.Employee;
import com.grh.pfeprv.domaine.Role;
import com.grh.pfeprv.domaine.ServiceRH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl  implements IAuthService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Override
    public ResponseEntity<JwtResponse> signin(LoginRequest request) {
       // System.out.println("Email : "+request.getEmail()+" Password : "+request.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        //System.out.println("Principal : "+authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //System.out.println("AVANT JWT");
        String jwt = jwtUtils.generateJwtToken(authentication);
        //System.out.println("JWT");

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public ResponseEntity<MessageResponse> signupEmp(SignupRequest request) {
        Role role_emp = roleRepository.findByName(ERole.ROLE_Employee).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role_emp);
        //User employe = new User();
        Employee employe= new Employee();
        employe.setEmail(request.getEmail());
        //employe.setSalary(request.);
        employe.setPassword(encoder.encode(request.getPassword()));

        employe.setRoles(roles);
        userRepository.save(employe);
        return ResponseEntity.ok(new MessageResponse("ajout avec success !"));
    }

    @Override
    public ResponseEntity<MessageResponse> signupRH(SignupRequest request) {
        Role role_RH = roleRepository.findByName(ERole.ROLE_serviceRH).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role_RH);
        //User RH = new User();
        ServiceRH RH=new ServiceRH();
        RH.setEmail(request.getEmail());
        RH.setPassword(encoder.encode(request.getPassword()));
        RH.setRoles(roles);
        userRepository.save(RH);
        return ResponseEntity.ok(new MessageResponse("ajout avec success !"));

    }

    @Override
    public ResponseEntity<MessageResponse> signupCondidat(SignupRequest request) {
        Role role_RH = roleRepository.findByName(ERole.ROLE_Condidat).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role_RH);
        //User condidat = new User();
        Condidats condidat = new Condidats();
        condidat.setEmail(request.getEmail());
        condidat.setPassword(encoder.encode(request.getPassword()));
        condidat.setRoles(roles);
        userRepository.save(condidat);
        return ResponseEntity.ok(new MessageResponse("ajout avec success !"));
    }


}
