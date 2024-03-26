package com.schbus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usertable")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_pwd")
    private String userPwd;
    @Column(name = "user_name")
    private String userName;

    public Person(String userId, String userPwd, String userName) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
    }
}
