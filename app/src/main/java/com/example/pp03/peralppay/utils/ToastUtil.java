package com.example.pp03.peralppay.utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pp03.peralppay.GmApplication;
import com.example.pp03.peralppay.R;


public class ToastUtil {
	private static Toast customToast;
	public static void showCustomToast(String msg) {
		if (customToast == null) {
			customToast = new Toast(GmApplication.getContext());
			View view = LayoutInflater.from(GmApplication.getContext()).inflate(R.layout.toast_view, null);
			TextView tvMsg = (TextView) view.findViewById(R.id.toast_message_tv);
			tvMsg.setText(msg);
			customToast.setGravity(Gravity.CENTER, 0, 0);
			customToast.setView(view);
			customToast.setDuration(Toast.LENGTH_SHORT);
		} else {
			((TextView) customToast.getView().findViewById(R.id.toast_message_tv)).setText(msg);
		}
		customToast.show();
	}
}