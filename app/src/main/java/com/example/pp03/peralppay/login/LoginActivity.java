package com.example.pp03.peralppay.login;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.example.pp03.peralppay.work.MainActivity;
import com.example.pp03.peralppay.R;
import com.example.pp03.peralppay.login.presenter.ILoginPresenter;
import com.example.pp03.peralppay.login.presenter.ILoginPresenterCompl;
import com.example.pp03.peralppay.login.view.ILoginView;
import com.example.pp03.peralppay.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pp03 on 2017/11/28.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView,View.OnClickListener{
    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.btn_login_login)
    CircularProgressButton btnLoginLogin;
    @BindView(R.id.find_pass)
    TextView find_pass;
    ILoginPresenter loginPresenter;
    InputMethodManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
         manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        loginPresenter = new ILoginPresenterCompl(this,LoginActivity.this);
        loginPresenter.setProgerssBarVisibility(View.INVISIBLE);
    }

    @Override
    public void onClearNameText() {
        etLoginUsername.setText("");
    }

    @Override
    public void onClearPassText() {
        etLoginPassword.setText("");
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {

    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        btnLoginLogin.setEnabled(true);
        if (result) {
            if (btnLoginLogin.getProgress() == 0) {
                simulateSuccessProgress(btnLoginLogin);
            } else {
                btnLoginLogin.setProgress(0);
            }
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        } else {
            if (btnLoginLogin.getProgress() == 0) {
                simulateErrorProgress(btnLoginLogin);
            } else {
                btnLoginLogin.setProgress(0);
            }
            Toast.makeText(this, "Login Fail, code=" + code, Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick({R.id.btn_login_login, R.id.find_pass})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:
                LogUtils.e("LoginActivity","===");
                btnLoginLogin.setEnabled(false);
                loginPresenter.doLogin(etLoginUsername.getText().toString(), etLoginPassword.getText().toString());
                break;
            case R.id.find_pass:

                break;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(getCurrentFocus()!=null && getCurrentFocus().getWindowToken()!=null){
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }
    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 200);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                if(value==200){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    button.setProgress(0);
                }else{
                    button.setProgress(value);
                }
            }
        });
        widthAnimation.start();
    }
    private void simulateErrorProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 300);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();

                if(value==300){
                    button.setProgress(0);
                }
                else if (value >99) {
                    button.setProgress(-1);
                }else{
                    button.setProgress(value);
                }


            }
        });
        widthAnimation.start();
    }
}
