package com.jm.vmarqueeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> messages = new ArrayList();
    private VMarqueeView vMarqueeView;

    {
        messages.add("助力制造业强国梦想 奏响“新蓝海”时代强音");
        messages.add("又一个世界第一 核电技术迎来“中国芯”");
        messages.add("“联盟”号火箭能力不如IPhone？俄驻英大使馆：请坐着IP...");
        messages.add("美国陆军只是为海空军打酱油的么？来看看他们的真正实力！");
        messages.add("广告：好消息好消息，厂家直销，厂家直销，路过不要错过");
        messages.add("手机商都在忙着“刷脸”，而微软却要在键盘上加指纹识别！");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vMarqueeView = (VMarqueeView) findViewById(R.id.vmarqueeview);
        vMarqueeView.setAdapter(new MyLooperAdapter(messages));
        vMarqueeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int cp = position % messages.size();
                Toast.makeText(MainActivity.this, position + "," + cp + "\n" + messages.get(cp), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        ViewTreeObserver viewTreeObserver = vMarqueeView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                vMarqueeView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = vMarqueeView.getMeasuredWidth();
                int height = vMarqueeView.getMeasuredHeight();
            }
        });


        int widthMesureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        int heightMesureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        vMarqueeView.measure(widthMesureSpec, heightMesureSpec);
    }*/

    private class MyLooperAdapter extends VMarqueeView.LooperAdapter<String> {

        public MyLooperAdapter(List<String> mDatas) {
            super(mDatas);
        }

        @Override
        public View getView(String item, View convertView, ViewGroup parent) {
            VH vh = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                vh = new VH();
                vh.tv = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(vh);
            } else {
                vh = (VH) convertView.getTag();
            }
            vh.tv.setText(item);
            return convertView;
        }
    }

    private static class VH {
        public TextView tv;
    }
}
