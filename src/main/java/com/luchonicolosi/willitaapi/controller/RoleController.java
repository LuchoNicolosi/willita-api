package com.luchonicolosi.willitaapi.controller;

import com.luchonicolosi.willitaapi.dto.RoleDto;
import com.luchonicolosi.willitaapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping()
    public ResponseEntity<String> createRole(@RequestBody RoleDto rol) {
        try {
            roleService.createRole(rol);
            return new ResponseEntity<>("Rol Created", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
