package com.luchonicolosi.willitaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luchonicolosi.willitaapi.dto.Auth.LoginDto;
import com.luchonicolosi.willitaapi.dto.Auth.SignUpDto;
import com.luchonicolosi.willitaapi.model.Role;
import com.luchonicolosi.willitaapi.model.User;
import com.luchonicolosi.willitaapi.repository.RoleRepository;
import com.luchonicolosi.willitaapi.repository.UserRepository;
import com.luchonicolosi.willitaapi.security.jwt.CustomerDetailsService;
import com.luchonicolosi.willitaapi.security.jwt.JwtUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private final Logger LOGGER = Logger.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomerDetailsService customerDetailsService;
    @Autowired
    private ObjectMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);


    public void signup(SignUpDto data) throws Exception {
        if (userRepository.findByUsername(data.getUsername()).isPresent()) {
            throw new Exception("This user does exist");
        }

        Set<Role> roles = new HashSet<>();

        for (String role : data.getRoles()) {
            Role findRole = roleRepository.findByName(role);
            if (findRole == null) throw new Exception("Role " + role + " not found");
            roles.add(findRole);
        }

        User newUser = new User();
        newUser.setEmail(data.getEmail());
        newUser.setUsername(data.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(data.getPassword()));
        newUser.setRoles(roles);

        userRepository.save(newUser);
    }

    public String login(LoginDto data) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
            if (authentication.isAuthenticated()) {
                return jwtUtil.generateToken(customerDetailsService.getUserDetail().getUsername(), customerDetailsService.getUserDetail().getRoles());
            }
        } catch (Exception e) {
            LOGGER.error("Failed to authenticate -> " + e);
        }
        return null;
    }

}
