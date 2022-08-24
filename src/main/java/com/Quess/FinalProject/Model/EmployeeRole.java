package com.Quess.FinalProject.Model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table
public class EmployeeRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int EmployeeRole_id;
    @Column
    private String EmployeeRole_name;
}
