package com.mTrepka.simpleShop.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;

@Entity
@Data
public class EmailTemplate {
    @Id
    @GeneratedValue
    private long id;
    private HashMap<String, String> dataMap;
    private String template;
    private String name;
}
