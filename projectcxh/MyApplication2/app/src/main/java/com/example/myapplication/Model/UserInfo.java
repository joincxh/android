package com.example.myapplication.Model;

import cn.bmob.v3.BmobObject;

public class UserInfo extends BmobObject {
    public String getSname() {
        return sname;
    }

    public String getEmail() {
        return email;
    }

    public String getJob() {
        return job;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String sname;
    private String phone;
    private String email;
    private String job;

}
