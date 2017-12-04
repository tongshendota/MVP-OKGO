package com.example.pp03.peralppay.login.model;

/**
 * Created by pp03 on 2017/11/28.
 */

public interface IUser  {
    String getName();
    String getPasswd();
    int checkUserValidity(String name, String password);
}