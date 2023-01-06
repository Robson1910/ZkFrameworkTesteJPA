package io.github.robson.model;

import org.zkoss.bind.annotation.DependsOn;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contatos")
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcon")
    private String id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "fone")
    private String fone;
    @Column(name = "email")
    private String email;

    public Contato() {
    }

    public Contato(String id, String nome, String fone, String email) {
        this.id = id;
        this.nome = nome;
        this.fone = fone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DependsOn({ "id", "nome","fone","email"})
    public String getFullName() {
        return getId() + " " + getNome() + " " + getFone() + " " + getEmail();
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", fone='" + fone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
