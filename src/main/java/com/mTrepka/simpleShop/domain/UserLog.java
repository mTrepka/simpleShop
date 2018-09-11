package com.mTrepka.simpleShop.domain;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class UserLog {
    @Column(name = "log_id")
    @Id
    @GeneratedValue
    private long id;
    private String type;
    private Timestamp data;
    private String ip;
    private String description;
    @ManyToOne(targetEntity = User.class)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        if(Objects.isNull(getUser()))
        return "["+type+"]["+data.toString()+"]["+ip+"][System]"+description;
        else
            return "["+type+"]["+data.toString()+"]["+ip+"]["+getUser().getEmail()+"]"+description;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
