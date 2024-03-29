package com.sefvi.seamarket.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AccountModell implements Serializable{

        @SerializedName("uuid")
        @Expose
        private String uuid;

        @SerializedName("fullname")
        @Expose
        private String fullname;

        @SerializedName("dateOfBirth")
        @Expose
        private String dateOfBirth;

        @SerializedName("gender")
        @Expose
        private String gender;

        @SerializedName("avatar")
        @Expose
        private String avatar;

        @SerializedName("address")
        @Expose
        private String address;

        @SerializedName("isAdmin")
        @Expose
        private int isAdmin;

        @SerializedName("isActive")
        @Expose
        private int isActive;

        @SerializedName("createAt")
        @Expose
        private String createAt;

        @SerializedName("updateAt")
        @Expose
        private String updateAt;

        @SerializedName("phone")
        @Expose
        private String phone;

        @SerializedName("password")
        @Expose
        private String password;

        @SerializedName("token")
        @Expose
        private  String token;

        public AccountModell(){}

        public AccountModell(String uuid, String fullname, String dateOfBirth, String gender, String avatar, String address, int isAdmin, int isActive, String careateAt, String updateAt, String phone, String password){
            this.uuid = uuid;
            this.fullname = fullname;
            this.dateOfBirth = dateOfBirth;
            this.gender = gender;
            this.avatar = avatar;
            this.address = address;
            this.isAdmin = isAdmin;
            this.isActive = isActive;
            this.createAt = careateAt;
            this.updateAt = updateAt;
            this.phone = phone;
            this.password = password;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(int isAdmin) {
            this.isAdmin = isAdmin;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(String updateAt) {
            this.updateAt = updateAt;
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
    public  String getToken(){return  token;}
    public  void setToken(String token){this.token = token;}
}


