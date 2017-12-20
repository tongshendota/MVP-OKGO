package com.example.pp03.peralppay.Bluetooth;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pp03.peralppay.R;
import com.example.pp03.peralppay.utils.SharedPreferencesUtil;

import java.util.ArrayList;

import static com.example.pp03.peralppay.Bluetooth.PrintBean.PRINT_TYPE;

/**
 * 类说明:打印的页面
 * 阳（360621904@qq.com）  2017/4/27  19:56
 */

public class PrintActivity extends AppCompatActivity {
    //设备列表
    private ListView listView;
    private ArrayList<PrintBean> mBluetoothDevicesDatas;
    private PrintAdapter adapter;
    //蓝牙适配器
    private BluetoothAdapter mBluetoothAdapter;
    //请求的code
    public static final int REQUEST_ENABLE_BT = 1;

    private Switch mSwitch;
    private FloatingActionButton mFloatingActionButton;
    private ProgressBar mProgressBar;
    private Toolbar toolbar;
    private TextView searchHint;
       private ProgressDialog progressDialog;
    /**
     * 启动打印页面
     *
     * @param printContent 要打印的内容
     */
    public static void starUi(Context context, String printContent) {
        Intent intent = new Intent(context, PrintActivity.class);
        intent.putExtra("printContent", printContent);
        context.startActivity(intent);
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                Toast.makeText(getBaseContext(), "打印发送失败，请稍后再试", Toast.LENGTH_SHORT).show();
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        //广播注册
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
        //初始化
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mSwitch = (Switch) findViewById(R.id.switch1);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar3);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchHint = (TextView) findViewById(R.id.searchHint);
        toolbar.setTitle("选择打印设备");

        listView = (ListView) findViewById(R.id.listView);
        mBluetoothDevicesDatas = new ArrayList<>();

        String printContent=getIntent().getStringExtra("printContent");
        adapter = new PrintAdapter(this, mBluetoothDevicesDatas, TextUtils.isEmpty(printContent)?"我叫打印机":printContent);
        listView.setAdapter(adapter);
        listView.setFocusable(false);
        chechBluetooth();
        addViewListener();

    }

    /**
     * 判断有没有开启蓝牙
     */
    private void chechBluetooth() {
        //没有开启蓝牙
        if (mBluetoothAdapter != null) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); // 设置蓝牙可见性，最多300秒
                intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 20);
                startActivityForResult(intent, REQUEST_ENABLE_BT);
                setViewStatus(true);
                //开启蓝牙
            } else {
                searchDevices();
                setViewStatus(true);
                mSwitch.setChecked(true);
            }
        }
    }

    /**
     * 搜索状态调整
     *
     * @param isSearch 是否开始搜索
     */
    private void setViewStatus(boolean isSearch) {

        if (isSearch) {
            mFloatingActionButton.setVisibility(View.GONE);
            searchHint.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mFloatingActionButton.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
            searchHint.setVisibility(View.GONE);
        }
    }


    /**
     * 添加View的监听
     */
    private void addViewListener() {
        //蓝牙的状态
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    openBluetooth();
                    setViewStatus(true);
                } else {
                    closeBluetooth();
                }
            }
        });
        //重新搜索
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSwitch.isChecked()) {
                    searchDevices();
                    setViewStatus(true);
                } else {
                    openBluetooth();
                    setViewStatus(true);
                }
            }
        });

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_ENABLE_BT) {
            Log.e("text", "开启蓝牙");
            searchDevices();
            mSwitch.setChecked(true);
            mBluetoothDevicesDatas.clear();
            adapter.notifyDataSetChanged();
        } else if (resultCode == RESULT_CANCELED && requestCode == REQUEST_ENABLE_BT) {
            Log.e("text", "没有开启蓝牙");
            mSwitch.setChecked(false);
            setViewStatus(false);
        }

    }

    /**
     * 打开蓝牙
     */
    public void openBluetooth() {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); // 设置蓝牙可见性，最多300秒
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 20);
        startActivityForResult(intent, REQUEST_ENABLE_BT);

    }

    /**
     * 关闭蓝牙
     */
    public void closeBluetooth() {
        mBluetoothAdapter.disable();
    }


    /**
     * 搜索蓝牙设备
     */
    public void searchDevices() {
        mBluetoothDevicesDatas.clear();
        adapter.notifyDataSetChanged();
        //开始搜索蓝牙设备
        mBluetoothAdapter.startDiscovery();
    }


    /**
     * 通过广播搜索蓝牙设备
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 把搜索的设置添加到集合中
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //已经匹配的设备
                if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
                    addBluetoothDevice(device);
      SharedPreferencesUtil.saveData(getBaseContext(),"Dress",device.getAddress());
                    //没有匹配的设备
                } else {

                }
                adapter.notifyDataSetChanged();
                //搜索完成
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setViewStatus(false);
            }
        }

        /**
         * 添加数据
         * @param device 蓝牙设置对象
         */
        private void addBluetoothDevice(BluetoothDevice device) {
            for (int i = 0; i < mBluetoothDevicesDatas.size(); i++) {
                if (device.getAddress().equals(mBluetoothDevicesDatas.get(i).getAddress())) {
                    mBluetoothDevicesDatas.remove(i);
                }
            }
            if (device.getBondState() == BluetoothDevice.BOND_BONDED && device.getBluetoothClass().getDeviceClass() == PRINT_TYPE) {
                mBluetoothDevicesDatas.add(0, new PrintBean(device));
            } else {
                mBluetoothDevicesDatas.add(new PrintBean(device));
            }
        }
    };
    private String keysum="";
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
                Log.e("PrintActivity",keysum);
                keysum = "";
                progressDialog = ProgressDialog.show(this, "提示", "正在打印...", true);
                Print p = new Print(progressDialog,getBaseContext());
                p.setdata("陌离这个蠢货");
                new Print.ConnectThread().start();
                break;
            case KeyEvent.KEYCODE_NUMPAD_ENTER:
                Log.e("PrintActivity",keysum);
                keysum = "";
                progressDialog = ProgressDialog.show(this, "提示", "正在打印...", true);
                Print pa = new Print(progressDialog,getBaseContext());
                pa.setdata("陌离这个蠢货");
                new Print.ConnectThread().start();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

}
