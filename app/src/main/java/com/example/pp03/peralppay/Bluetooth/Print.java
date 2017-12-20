package com.example.pp03.peralppay.Bluetooth;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.pp03.peralppay.utils.SharedPreferencesUtil;
import com.example.pp03.peralppay.utils.ToastUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by pp03 on 2017-12-18.
 */

public class Print {

    private static BluetoothSocket mmSocket;
    static ProgressDialog progressDialog = null;
    //蓝牙适配器
    private static BluetoothAdapter mBluetoothAdapter;
    //打印的输出流
    private static OutputStream outputStream = null;
    private static String keysum;
    private final static int exceptionCod = 100;
   static   Context mcontext;

   public   Print(ProgressDialog progressDialog, Context context){
       mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

       this.progressDialog = progressDialog;

       this.mcontext = context;

   }
   public void setdata( String keysum){
       this.keysum = keysum;
   }
    /**
     * 连接为客户端
     */
    public static class ConnectThread extends Thread {
        public ConnectThread() {
            try {

                mmSocket = mBluetoothAdapter.getRemoteDevice( (String) SharedPreferencesUtil.getData(mcontext,"Dress","1")).createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            //取消的发现,因为它将减缓连接
            mBluetoothAdapter.cancelDiscovery();
            try {
                //连接socket
                mmSocket.connect();
                //连接成功获取输出流
                outputStream = mmSocket.getOutputStream();


                send(keysum);
            } catch (Exception connectException) {
                Log.e("test", "连接失败");
                connectException.printStackTrace();

                ToastUtil.showCustomToast("打印发送失败，请稍后再试");
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
                try {
                    mmSocket.close();
                } catch (Exception closeException) {
                    closeException.printStackTrace();
                }
                return;
            }
        }
    }
    /**
     * 发送数据
     */
    public static void send(String sendData) {
        try {
            PrintUtils.setOutputStream(outputStream);
            PrintUtils.selectCommand(PrintUtils.RESET);
            PrintUtils.selectCommand(PrintUtils.LINE_SPACING_DEFAULT);
            PrintUtils.selectCommand(PrintUtils.ALIGN_CENTER);
            PrintUtils.printText(sendData+"\n\n");
            PrintUtils.selectCommand(PrintUtils.DOUBLE_HEIGHT_WIDTH);
            PrintUtils.printText("是真的蠢\n\n");
            PrintUtils.selectCommand(PrintUtils.NORMAL);
            PrintUtils.selectCommand(PrintUtils.ALIGN_LEFT);
            PrintUtils.printText(PrintUtils.printTwoData("订单编号", "201507161515\n"));
            PrintUtils.printText(PrintUtils.printTwoData("点菜时间", "2016-02-16 10:46\n"));
            PrintUtils.printText(PrintUtils.printTwoData("上菜时间", "2016-02-16 11:46\n"));
            PrintUtils.printText(PrintUtils.printTwoData("人数：2人", "收银员：张三\n"));

            PrintUtils.printText("--------------------------------\n");
            PrintUtils.selectCommand(PrintUtils.BOLD);
            PrintUtils.printText(PrintUtils.printThreeData("项目", "数量", "金额\n"));
            PrintUtils.printText("--------------------------------\n");
            PrintUtils.selectCommand(PrintUtils.BOLD_CANCEL);
            PrintUtils.printText(PrintUtils.printThreeData("面", "1", "0.00\n"));
            PrintUtils.printText(PrintUtils.printThreeData("米饭", "1", "6.00\n"));
            PrintUtils.printText(PrintUtils.printThreeData("铁板烧", "1", "26.00\n"));
            PrintUtils.printText(PrintUtils.printThreeData("一个测试", "1", "226.00\n"));
            PrintUtils.printText(PrintUtils.printThreeData("牛肉面啊啊", "1", "2226.00\n"));
            PrintUtils.printText(PrintUtils.printThreeData("牛肉面啊啊啊牛肉面啊啊啊", "888", "98886.00\n"));

            PrintUtils.printText("--------------------------------\n");
            PrintUtils.printText(PrintUtils.printTwoData("合计", "53.50\n"));
            PrintUtils.printText(PrintUtils.printTwoData("抹零", "3.50\n"));
            PrintUtils.printText("--------------------------------\n");
            PrintUtils.printText(PrintUtils.printTwoData("应收", "50.00\n"));
            PrintUtils.printText("--------------------------------\n");

            PrintUtils.selectCommand(PrintUtils.ALIGN_LEFT);
            PrintUtils.printText("备注：不要辣、不要香菜");
            PrintUtils.printText("\n\n\n\n\n");
            progressDialog.dismiss();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            ToastUtil.showCustomToast("打印发送失败，请稍后再试");
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

}
