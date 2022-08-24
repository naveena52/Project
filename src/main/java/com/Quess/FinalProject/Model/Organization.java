package com.Quess.FinalProject.Model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int organization_id;

    @NotEmpty(message = "organizationName name Should not empty")
    @Size(min=3)
    private String organizationName;

    @NotEmpty(message = "Address Should not empty")
    private String address;

    @Pattern(regexp = "^\\d{10}$",message = "enter correct phone number")
    private String phoneNo;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Employee> Employee=new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Assets> Assets =new HashSet<>();

}