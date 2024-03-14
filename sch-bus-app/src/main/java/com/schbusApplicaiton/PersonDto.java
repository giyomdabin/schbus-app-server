package com.schbus;

public class PersonDto {
    private String user_id;
    private String password;
    private String name;

    public PersonDto() {
        // 기본 생성자
    }

    // Getter 메서드
    public String getUser_id() {
        return user_id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    // Setter 메서드
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
