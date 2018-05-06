package biz.bikehub.rentbike.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String userid;
    private String email;
    private String password;
    private String fname;
    private String lname;
    private String address;
    private String phone;
    private String gender;
    private String photo;
    private String birthday;
    private String dni;
    private String usertype;

    public User() {
    }

    public User(String userid, String email, String password, String fname, String lname, String address, String phone, String gender, String photo, String birthday, String dni, String usertype) {
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.photo = photo;
        this.birthday = birthday;
        this.dni = dni;
        this.usertype = usertype;
    }

    public String getUserid() {
        return userid;
    }

    public User setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFname() {
        return fname;
    }

    public User setFname(String fname) {
        this.fname = fname;
        return this;
    }

    public String getLname() {
        return lname;
    }

    public User setLname(String lname) {
        this.lname = lname;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public User setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public User setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getDni() {
        return dni;
    }

    public User setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public String getUsertype() {
        return usertype;
    }

    public User setUsertype(String usertype) {
        this.usertype = usertype;
        return this;
    }

    public static User from(JSONObject jsonUser){

        try {
            return new User(
                    jsonUser.getString("userid"),
                    jsonUser.getString("email"),
                    jsonUser.getString("password"),
                    jsonUser.getString("fname"),
                    jsonUser.getString("lname"),
                    jsonUser.getString("address"),
                    jsonUser.getString("phone"),
                    jsonUser.getString("gender"),
                    jsonUser.getString("photo"),
                    jsonUser.getString("birthday"),
                    jsonUser.getString("dni"),
                    jsonUser.getString("usertype"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<User> from(JSONArray jsonStations) {
        List<User> users = new ArrayList<>();
        int length = jsonStations.length();
        for(int i = 0; i < length; i++) {
            try {
                users.add(User.from(jsonStations.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public static User from(Bundle bundle) {
        return new User(
                bundle.getString("userid"),
                bundle.getString("email"),
                bundle.getString("password"),
                bundle.getString("fname"),
                bundle.getString("lname"),
                bundle.getString("address"),
                bundle.getString("phone"),
                bundle.getString("gender"),
                bundle.getString("photo"),
                bundle.getString("birthday"),
                bundle.getString("dni"),
                bundle.getString("usertype"));
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();

        bundle.putString("userid",getUserid());
        bundle.putString("email",getEmail());
        bundle.putString("password",getPassword());
        bundle.putString("fname",getFname());
        bundle.putString("lname",getLname());
        bundle.putString("address",getAddress());
        bundle.putString("phone",getPhone());
        bundle.putString("gender",getGender());
        bundle.putString("photo",getPhoto());
        bundle.putString("birthday",getBirthday());
        bundle.putString("dni",getDni());
        bundle.putString("usertype",getUsertype());

        return bundle;
    }


}
