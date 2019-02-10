package com.mTrepka.simpleShop.domain;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class UserLog {
    @Column(name = "log_id")
    @Id
    @GeneratedValue
    private long id;

    private String type;
    private Timestamp data;
    private String ip;
    private String secondIp;
    private String description;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
