package com.example.pp03.peralppay.utils;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Administrator on 2017/3/30.
 */

public class EdtTextWatcher implements TextWatcher {

    private AfterChangeListner mLister;

    public EdtTextWatcher(AfterChangeListner listner){
        this.mLister = listner;
    }

    public interface AfterChangeListner{
        public void afterTextChanged(Editable s);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mLister!=null){
            mLister.afterTextChanged(s);
        }
    }
}
