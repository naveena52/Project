package com.Quess.FinalProject.Model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Employee_id;
    @NotEmpty(message = "Name is mandatory")
    @Size(min = 4,message = "Name should be min 4 char")
    private String name;


    @Min(18)
    @Max(60)
    private int age;

    @NotEmpty(message = "Gender should not blank")
    private String gender;

    @NotEmpty(message = "address should not blank")
    private String address;

    @Pattern(regexp = "^\\d{10}$",message = "enter correct phone number")
    private String phoneNo;

    @Min(value = 10000)
    private int salary;

    @Email(message = "Email is invalid or already exits ")
    @NotEmpty(message = "Email should not blank")
    private String email;

    @Pattern(message="password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit with atleast 6 characters",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$")
    private String password;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="EmployeePosition", joinColumns = @JoinColumn(name="Employee_id", referencedColumnName = "Employee_id"),inverseJoinColumns = @JoinColumn(name="EmployeeRole",referencedColumnName = "EmployeeRole_id"))
    private Set<EmployeeRole> roles=new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities=this.roles.stream().map((role)->new SimpleGrantedAuthority(role.getEmployeeRole_name())).collect(Collectors.toList());
        return  authorities;
    }


    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @Override
//    public boolean equals(Object obj) {
//
//
//        if(obj==null || !(obj instanceof Employee) )
//            return false;
//        return this.Employee_id==((Employee)obj).getEmployee_id();
//    }

}
