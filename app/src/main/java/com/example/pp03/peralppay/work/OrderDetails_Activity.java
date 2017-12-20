package com.example.pp03.peralppay.work;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.pp03.peralppay.R;
import com.example.pp03.peralppay.login.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pp03 on 2017-12-20.
 */

public class OrderDetails_Activity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.black)
    ImageView black;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderdetails_activity);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.black})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.black:
                finish();
                break;
        }
    }
}
