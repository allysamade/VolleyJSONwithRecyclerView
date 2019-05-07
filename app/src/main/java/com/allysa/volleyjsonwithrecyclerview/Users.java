package com.allysa.volleyjsonwithrecyclerview;

public class Users {
    private String Id;
    private String name;
    private String userName;
    private String email;
    private String addr;


    public Users(String id, String name, String userName, String email, String addr) {
        this.Id = id;
        this.name= name;
        this.userName = userName;
        this.email = email;
        this.addr = addr;

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
