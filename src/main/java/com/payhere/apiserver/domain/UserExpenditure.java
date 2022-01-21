package com.payhere.apiserver.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "userExpenditure")
public class UserExpenditure {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Long id;
    @Column
    private Long userId;
    @Column
    private Long expenditureId;
    @Column
    private Boolean isDeleted;
}
