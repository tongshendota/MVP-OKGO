/**  
 * 工程: Coyote<p>
 * 标题: TimerCountDownTimer.java<p>
 * 包:   com.esun.ui.mainact.lottery.view<p>
 * 描述: TODO<p>
 * 作者: 梅雄新 meixx@500wan.com<p>
 * 时间: 2014-8-6 下午6:11:28<p>
 * 版权: Copyright 2014 Shenzhen E-sun Skey Net Tech Co.,Ltd.<p>
 * All rights reserved.<p>
 *
 */

package com.example.pp03.peralppay.utils;

import android.os.CountDownTimer;
import android.text.TextUtils;

public class TimerCountDownTimer extends CountDownTimer {

	public interface OnTimeChangeListener{
		public void onTimeFinsh();
		public void onTimeTick(String lastTime);
	}
	
	public OnTimeChangeListener changeListener;
	
	public TimerCountDownTimer(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
	}

	public OnTimeChangeListener getChangeListener() {
		return changeListener;
	}

	public void setChangeListener(OnTimeChangeListener changeListener) {
		this.changeListener = changeListener;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		 long time = millisUntilFinished / 1000;
			// 剩余小时
			 String strHour = String.valueOf(time / 3600);
			if (strHour.length() < 2) {
				if (Integer.parseInt(strHour) == 0) {
					strHour = null;
				} else {
					strHour = "0" + strHour;
				}
			}
			time = time % 3600;
			// 剩余分钟数
			String strMin = String.valueOf(time / 60);
			if (strMin.length() < 2) {
				strMin = "0" + strMin;
			}
			// 剩余秒数
			String strSec = String.valueOf(time % 60);
			if (strSec.length() < 2) {
				strSec = "0" + strSec;
			}
			String countTime = null;
			if (TextUtils.isEmpty(strHour)) {
				countTime = strMin + ":" + strSec;
			} else {
				countTime = strHour + ":" + strMin + ":" + strSec;
			}
			if(changeListener!=null){
				changeListener.onTimeTick(countTime);
			}
			
	}

	@Override
	public void onFinish() {
		if(changeListener!=null){
			changeListener.onTimeFinsh();
		}
	}

}
