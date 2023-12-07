package com.luchonicolosi.willitaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luchonicolosi.willitaapi.dto.RoleDto;
import com.luchonicolosi.willitaapi.model.Role;
import com.luchonicolosi.willitaapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createRole(RoleDto data) {
        Role newRole = mapper.convertValue(data, Role.class);
        roleRepository.save(newRole);
    }
}
