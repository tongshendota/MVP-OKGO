package com.example.pp03.peralppay.work;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pp03.peralppay.R;
import com.example.pp03.peralppay.login.BaseActivity;
import com.example.pp03.peralppay.work.Bean.MenuBean;
import com.example.pp03.peralppay.work.adapter.order_adapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by pp03 on 2017/12/8.
 */

public class Pay_Order_Activity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.list)
    ListView listView;
    @BindView(R.id.moneny_20)
    TextView moneny_20;
    @BindView(R.id.moneny_50)
    TextView moneny_50;
    @BindView(R.id.moneny_100)
    TextView moneny_100;
    @BindView(R.id.moneny_200)
    TextView moneny_200;
    @BindView(R.id.moneny_500)
    TextView moneny_500;
    @BindView(R.id.sum_moneny)
    TextView sum_moneny;
    @BindView(R.id.sum)
    TextView sum;
    @BindView(R.id.summoneny)
    TextView summoneny;
    @BindView(R.id.pocket)
    TextView pocket;
    @BindView(R.id.pro_commit)
    Button pro_commit;
    @BindView(R.id.order_type)
    TextView order_type;
    @BindView(R.id.num_sum)
    TextView num_sum;
    private  String moneny;
    private order_adapter orderAdapter;
    private ArrayList<MenuBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.pay_order_activity);
        ButterKnife.bind(this);
        findViewById(R.id.black).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                black(v);
            }
        });
         init();
        initdata();
    }
    private void init(){

        Bundle bundle = getIntent().getExtras();
        list = bundle.getParcelableArrayList("data");
        if(list==null){
            list = new ArrayList<>();
        }

        if(!TextUtils.isEmpty(getIntent().getStringExtra("summoneny"))){
        moneny  = getIntent().getStringExtra("summoneny");
            sum.setText(moneny);
//            summoneny.setText(R.string.receivables+""+moneny+R.string.cable_dollars);
            summoneny.setText(getResources().getString(R.string.receivables)+moneny+getResources().getString(R.string.cable_dollars));
            pocket.setText(moneny);
        }
         if(!TextUtils.isEmpty(getIntent().getStringExtra("sum"))){

             num_sum.setText(getIntent().getStringExtra("sum"));
         }
         if(!TextUtils.isEmpty(getIntent().getStringExtra("ordertype"))){
             if(getIntent().getStringExtra("ordertype").equals("0")){
                 order_type.setText(getResources().getString(R.string.for_here));
             }
             if(getIntent().getStringExtra("ordertype").equals("1")){
                 order_type.setText(getResources().getString(R.string.takeaway));
             }
         }
        orderAdapter = new order_adapter(getBaseContext());
        listView.setAdapter(orderAdapter);
        listView.setFocusable(false);
        orderAdapter.setData(list);
    }
    private void initdata(){

    }
    private void black(View view){
        finish();
    }
    @OnClick({R.id.moneny_20, R.id.moneny_50,R.id.moneny_100,R.id.moneny_200,R.id.moneny_500,R.id.pro_commit})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case  R.id.moneny_20:
               sum_moneny.setText("20");
            break;
            case  R.id.moneny_50:
                sum_moneny.setText("50");
                break;
            case  R.id.moneny_100:
                sum_moneny.setText("100");
                break;
            case  R.id.moneny_200:
                sum_moneny.setText("200");
                break;
            case  R.id.moneny_500:
                sum_moneny.setText("500");
                break;
            case  R.id.pro_commit:
               showPasswordSetDailog();
                break;
        }
    }
    private void showPasswordSetDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        View view = View.inflate(this, R.layout.summoneny_dialog, null);
        // dialog.setView(view);// 将自定义的布局文件设置给dialog
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
        TextView summoneny = (TextView)view.findViewById(R.id.summoneny);
        TextView procket = (TextView)view.findViewById(R.id.procket);
        Button commit = (Button)view.findViewById(R.id.commit);
        LinearLayout back = (LinearLayout)view.findViewById(R.id.back);
        summoneny.setText(getResources().getString(R.string.cash_receipts)+sum_moneny.getText().toString()+getResources().getString(R.string.cable_dollars));
         Double porcketa = Double.valueOf(sum_moneny.getText().toString())-Double.valueOf(moneny);
        procket.setText(getResources().getString(R.string.please_change)+porcketa+getResources().getString(R.string.cable_dollars));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                SweetAlertDialog d =     new SweetAlertDialog(Pay_Order_Activity.this, SweetAlertDialog.SUCCESS_TYPE);
                        d.setTitleText(getResources().getString(R.string.payment_success));
                        d.show();
                d.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Intent intent = new Intent("order");
                        sendBroadcast(intent);
                        finish();
                    }
                });

            }
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                switch (keyCode){
                    case KeyEvent.KEYCODE_0:
                        keysum = keysum+"0";     Log.e("PrintActivity",keysum);
                        break;
                    case KeyEvent.KEYCODE_1:
                        keysum = keysum+"1";     Log.e("PrintActivity",keysum);
                        break;
                    case KeyEvent.KEYCODE_2:
                        keysum = keysum+"2";     Log.e("PrintActivity",keysum);
                        break;
                    case KeyEvent.KEYCODE_3:
                        keysum = keysum+"3";     Log.e("PrintActivity",keysum);
                        break;
                    case KeyEvent.KEYCODE_4:
                        keysum = keysum+"4";     Log.e("PrintActivity",keysum);
                        break;
                    case KeyEvent.KEYCODE_5:
                        keysum = keysum+"5";     Log.e("PrintActivity",keysum);
                        break;
                    case KeyEvent.KEYCODE_6:
                        keysum = keysum+"6";     Log.e("PrintActivity",keysum);
                        break;
                    case KeyEvent.KEYCODE_7:
                        keysum = keysum+"7";     Log.e("PrintActivity",keysum);
                        break;
                    case KeyEvent.KEYCODE_8:
                        keysum = keysum+"8";     Log.e("PrintActivity",keysum);
                        break;
                    case KeyEvent.KEYCODE_9:
                        keysum = keysum+"9";     Log.e("PrintActivity",keysum);
                        break;
                    case KeyEvent.KEYCODE_ENTER:
                        keysum = "";
                        dialog.dismiss();
                        Intent intent = new Intent("order");
                        sendBroadcast(intent);
                        finish();
                        break;
                    case KeyEvent.KEYCODE_NUMPAD_ENTER:
                        keysum = "";
                        dialog.dismiss();
                        Intent intenta = new Intent("order");
                        sendBroadcast(intenta);
                        finish();
                        break;
                }

                return false;
            }
        });
        dialog.show();
    }
    private String keysum ="";
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_0:
                keysum = keysum+"0";     Log.e("PrintActivity",keysum);
                break;
            case KeyEvent.KEYCODE_1:
                keysum = keysum+"1";     Log.e("PrintActivity",keysum);
                break;
            case KeyEvent.KEYCODE_2:
                keysum = keysum+"2";     Log.e("PrintActivity",keysum);
                break;
            case KeyEvent.KEYCODE_3:
                keysum = keysum+"3";     Log.e("PrintActivity",keysum);
                break;
            case KeyEvent.KEYCODE_4:
                keysum = keysum+"4";     Log.e("PrintActivity",keysum);
                break;
            case KeyEvent.KEYCODE_5:
                keysum = keysum+"5";     Log.e("PrintActivity",keysum);
                break;
            case KeyEvent.KEYCODE_6:
                keysum = keysum+"6";     Log.e("PrintActivity",keysum);
                break;
            case KeyEvent.KEYCODE_7:
                keysum = keysum+"7";     Log.e("PrintActivity",keysum);
                break;
            case KeyEvent.KEYCODE_8:
                keysum = keysum+"8";     Log.e("PrintActivity",keysum);
                break;
            case KeyEvent.KEYCODE_9:
                keysum = keysum+"9";     Log.e("PrintActivity",keysum);
                break;
            case KeyEvent.KEYCODE_ENTER:
                keysum = "";
                Intent intent = new Intent("order");
                sendBroadcast(intent);
                finish();
                break;
            case KeyEvent.KEYCODE_NUMPAD_ENTER:
                keysum = "";
                Intent intenta = new Intent("order");
                sendBroadcast(intenta);
                finish();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

}
