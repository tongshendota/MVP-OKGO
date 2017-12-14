package com.example.pp03.peralppay.work.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pp03.peralppay.R;
import com.example.pp03.peralppay.utils.DateUtil;
import com.example.pp03.peralppay.utils.Url;
import com.example.pp03.peralppay.work.Bean.OrdenBean;
import com.example.pp03.peralppay.work.adapter.orden_adapter;
import com.example.pp03.peralppay.work.presenter.IOrdenPresenterCompl;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp03 on 2017-12-14.
 */

public class TodayFragment extends BaseFragment implements ITodayView{
     ArrayList<OrdenBean> list;
    private ImageView tvLoadError;
    private TextView tvNoResult;
    private ListView listView;
    private orden_adapter orden_adapter;
    IOrdenPresenterCompl iOrdenPresenterCompl;
    private TextView today;
   private Handler handler = new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
       switch (msg.what){
           case 1:
              if(list!=null){
                  if(list.size()>0){
                      tvLoadError.setVisibility(View.GONE);
                      tvNoResult.setVisibility(View.GONE);
                      orden_adapter = new orden_adapter(getContext(),handler);
                      listView.setAdapter(orden_adapter);
                      orden_adapter.setData(list);
                  }else{
                      tvLoadError.setVisibility(View.VISIBLE);
                      tvNoResult.setVisibility(View.VISIBLE);
                  }
              }else{
                  tvLoadError.setVisibility(View.VISIBLE);
                  tvNoResult.setVisibility(View.VISIBLE);
              }
               break;
       }
       }
   };
    @Override
    public void initData() {
        iOrdenPresenterCompl = new IOrdenPresenterCompl(TodayFragment.this,getActivity());
        iOrdenPresenterCompl.getData(Url.get_getDrawList());
        today.setText(DateUtil.StringData());
    }

    @Override
    public int getFragmentLayoutResousId() {
        return R.layout.orden_managment_fragment;
    }

    @Override
    public void initUI() {
        tvLoadError = (ImageView)rootView.findViewById(R.id.tv_loaderror);
        tvNoResult = (TextView)rootView.findViewById(R.id.tv_loadnoresult);
        listView = (ListView)rootView.findViewById(R.id.list);
        today = (TextView)rootView.findViewById(R.id.today);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onGetDataResult(Boolean result, int code, List<OrdenBean> pare) {
          if(result){
            list = new ArrayList<>();
              list.addAll(pare);
              Message message = new Message();
              message.what=1;
              handler.sendMessage(message);
          }
    }
}
