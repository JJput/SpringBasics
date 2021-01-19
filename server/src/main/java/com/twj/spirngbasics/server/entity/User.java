package com.twj.spirngbasics.server.entity;

public class User extends BaseEntity {


    private String remake;

    private String spare1;

    private String spare2;

    private String spare3;

    private String spare4;

    private String spare5;

    private String spare6;

    private String spare7;

    private String spare8;

    private String spare9;

    private String companyId;

    private String phone;

    private String password;

    private String email;

    private String name;

    private String status;

    private String roleId;



    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getSpare1() {
        return spare1;
    }

    public void setSpare1(String spare1) {
        this.spare1 = spare1;
    }

    public String getSpare2() {
        return spare2;
    }

    public void setSpare2(String spare2) {
        this.spare2 = spare2;
    }

    public String getSpare3() {
        return spare3;
    }

    public void setSpare3(String spare3) {
        this.spare3 = spare3;
    }

    public String getSpare4() {
        return spare4;
    }

    public void setSpare4(String spare4) {
        this.spare4 = spare4;
    }

    public String getSpare5() {
        return spare5;
    }

    public void setSpare5(String spare5) {
        this.spare5 = spare5;
    }

    public String getSpare6() {
        return spare6;
    }

    public void setSpare6(String spare6) {
        this.spare6 = spare6;
    }

    public String getSpare7() {
        return spare7;
    }

    public void setSpare7(String spare7) {
        this.spare7 = spare7;
    }

    public String getSpare8() {
        return spare8;
    }

    public void setSpare8(String spare8) {
        this.spare8 = spare8;
    }

    public String getSpare9() {
        return spare9;
    }

    public void setSpare9(String spare9) {
        this.spare9 = spare9;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void clear(){
        this.password=null;
        this.createdBy=null;
        this.createdTime=null;
        this.updateBy=null;
        this.updateTime=null;
    }
}
