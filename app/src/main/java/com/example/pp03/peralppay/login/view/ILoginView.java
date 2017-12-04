package com.example.pp03.peralppay.login.view;

/**
 * Created by pp03 on 2017/11/28.
 */

    public interface ILoginView {
        void onClearNameText();
        void onClearPassText();
        void onSetProgressBarVisibility(int visibility);
        void onLoginResult(Boolean result, int code);
    }

