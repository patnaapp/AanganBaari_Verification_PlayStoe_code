package bih.in.aanganwadiBenVerification.entity;

import java.io.Serializable;

public class UserProfile implements Serializable {
    public static Class<UserProfile> UserProfile_CLASS= UserProfile.class;

    private String UserId;
    private String UserName;
    private String Email;
    private String ContactNo;
    private String Photo;


    public UserProfile()
    {

    }


    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}