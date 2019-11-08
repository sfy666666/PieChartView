package com.example.yanxu.piechartview.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yanxu.piechartview.R;
import com.example.yanxu.piechartview.view.PieChartView;

/***
 * 首页
 */

public class MainActivity extends AppCompatActivity {

    private PieChartView mPieChartView;
    private float[] ratios = {0.8f, 0.2f};
    //    private float[] ratios = {0.1f, 0.2f, 0.3f, 0.4f};
    private String[] colors = {"#6F7CFF", "#FFB864"};
    //    private String[] colors = {"#FFB864", "#6F7CFF", "#884EDB", "#90E3E1"};
    static int progress = 80;
    static int current=0;
    private static TextView tvProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * 初始化view
     */
    private void initView() {
        mPieChartView = findViewById(R.id.pie_chart_view);
        tvProgress = findViewById(R.id.tv_progress);
    }

    /**
     * 初始化data
     */
    private void initData() {
        if (ratios.length == 1)
            colors = null;
        mPieChartView.initSrc(ratios, colors);
        mPieChartView.startDraw();
        mHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg!=null){
                tvProgress.setText(msg.what+"%");
                if (current <= progress)
                    mHandler.sendEmptyMessage(current++);
                else
                    mHandler.removeCallbacksAndMessages(null);
            }

        }
    };

}
