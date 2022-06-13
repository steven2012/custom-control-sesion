package com.co.taxislibres.platform.crosscutting.persistence.entity;

import lombok.*;

import javax.persistence.*;


import java.io.Serializable;
import java.util.Date;


@Table(name = "CONDUCTOR")
@Entity
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConductorEntity implements Serializable {
    private static final long serialVersionUID = 1135471710369713709L;
   
    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "CEDULA", nullable = false)
    private String cedula;

    @Column(name = "CELULAR", nullable = false)
    private String celular;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "GCMTOKEN", nullable = false)
    private String gcmtoken;

    @Column(name = "VERSION")
    private String version;

    @Column(name = "HABILITADO", nullable = false)
    private Boolean habilitado;

    @Column(name = "IMEI", nullable = false)
    private String imei;

    @Column(name = "NOMBRES", nullable = false)
    private String nombres;

    @Column(name = "FECHAULTIMOLOGIN", nullable = false)
    private Date fechaUltimologin;

    @Column(name = "PROMEDIO")
    private Double promedio;


}