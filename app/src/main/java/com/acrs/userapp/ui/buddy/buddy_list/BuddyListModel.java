package com.acrs.userapp.ui.buddy.buddy_list;

public class BuddyListModel {

    String buddy_id;
    String buddy_name;
    String email;
    String phone_no;
    String gender;
    String firebasetocken;
    String status;
    String Message;

    public String getBuddy_id() {
        return buddy_id;
    }

    public void setBuddy_id(String buddy_id) {
        this.buddy_id = buddy_id;
    }

    public String getBuddy_name() {
        return buddy_name;
    }

    public void setBuddy_name(String buddy_name) {
        this.buddy_name = buddy_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirebasetocken() {
        return firebasetocken;
    }

    public void setFirebasetocken(String firebasetocken) {
        this.firebasetocken = firebasetocken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
