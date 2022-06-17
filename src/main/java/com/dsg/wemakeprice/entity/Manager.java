package com.dsg.wemakeprice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    private String managerName;
    private String managerEmail;


    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Company> companies = new ArrayList<>();

    @Builder
    public Manager(Long id, String managerName, String managerEmail) {
        this.id = id;
        this.managerName = managerName;
        this.managerEmail = managerEmail;
    }
}
