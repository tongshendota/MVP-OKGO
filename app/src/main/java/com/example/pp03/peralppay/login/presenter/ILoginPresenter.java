package com.example.pp03.peralppay.login.presenter;

/**
 * Created by pp03 on 2017/11/28.
 */


public interface ILoginPresenter {
    void doLogin(String name, String password);
    void setProgerssBarVisibility(int visibility);
}