package com.mTrepka.simpleShop.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;

@Entity
public class EmailTemplate {
    @Id
    @GeneratedValue
    private long id;
    private HashMap<String, String> dataMap;
    private String template;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HashMap<String, String> getDataMap() {
        return dataMap;
    }

    public void setDataMap(HashMap<String, String> dataMap) {
        this.dataMap = dataMap;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
