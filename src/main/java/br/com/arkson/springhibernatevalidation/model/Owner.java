package br.com.arkson.springhibernatevalidation.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author arkson
 * @date 30/04/2021
 */
@Entity
@Table(name = "owners")
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String cpf;
}
