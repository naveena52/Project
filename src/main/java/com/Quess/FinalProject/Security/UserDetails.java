package com.Quess.FinalProject.Security;

import com.Quess.FinalProject.Model.Employee;
import com.Quess.FinalProject.Repository.Employeerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetails implements UserDetailsService {
    @Autowired
    private Employeerepository employeeRepo;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee detail= this.employeeRepo.findByEmail(username);

        return detail;
    }

}
