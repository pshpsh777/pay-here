package com.payhere.apiserver.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "expenditure")
public class Expenditure {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Long id;
    @Column
    private int amounts;
    @Column
    private Date datetime;
    @Column
    private String memo;
}
