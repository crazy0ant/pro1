package com.example.mobilplay;


//import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.mobilplay.R;
import com.example.mobilplay.base.BasePager;
import com.example.mobilplay.page.AudioPager;
import com.example.mobilplay.page.NetAudioPager;
import com.example.mobilplay.page.NetVideoPager;
import com.example.mobilplay.page.VideoPager;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity {


    private RadioGroup  rg_bottom_tag;

    /**
     * ҳ��ļ���
     */
    private ArrayList<BasePager> basePagers;

    /**
     * ѡ�е�λ��
     */
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_bottom_tag = (RadioGroup) findViewById(R.id.rb_bottom_tag);

//        rg_bottom_tag.check(R.id.rb_video);//Ĭ��ѡ����ҳ

        basePagers = new ArrayList<BasePager>();
        basePagers.add(new VideoPager(this));//��ӱ�����Ƶҳ��-0
        basePagers.add(new AudioPager(this));//��ӱ�������ҳ��-1
        basePagers.add(new NetVideoPager(this));//���������Ƶҳ��-2
        basePagers.add(new NetAudioPager(this));//���������Ƶҳ��-3


        //����RadioGroup�ļ���
        rg_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId){
                default:
                    position = 0;
                    break;
                case R.id.rb_audio://��Ƶ
                    position = 1;
                    break;
                case R.id.rb_net_video://������Ƶ
                    position = 2;
                    break;
                case R.id.rb_net_audio://������Ƶ
                    position = 3;
                    break;
            }

            setFragment();


        }
    }

    /**
     * ��ҳ����ӵ�Fragment��
     */
    private void setFragment() {
        //1.�õ�FragmentManger
        FragmentManager manager = getSupportFragmentManager();
        //2.��������
        FragmentTransaction ft = manager.beginTransaction();
        //3.�滻
        ft.replace(R.id.fl_main_content,new Fragment(){
//            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                BasePager basePager = getBasePager();
                if(basePager != null){
                    //����ҳ�����ͼ
                    return basePager.rootView;
                }
                return null;
            }
        });
        //4.�ύ����
        ft.commit();

    }

    /**
     * ����λ�õõ���Ӧ��ҳ��
     * @return
     */
    private BasePager getBasePager() {
        BasePager basePager = basePagers.get(position);
        if(basePager != null&&!basePager.isInitData){
            basePager.initData();//����������߰�����
            basePager.isInitData = true;
        }
        return basePager;
    }
}
