package com.mTrepka.simpleShop.domain;


import javax.persistence.*;

@Entity
public class EmailMap {
    @Column(name = "email_id")
    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
