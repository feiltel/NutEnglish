package com.nutstudio.nutenglish.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nutstudio.nutenglish.Activity.GameActivity;
import com.nutstudio.nutenglish.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartGameFragment extends Fragment {
    private String appURL="http://pan.baidu.com/share/link?shareid=1496095071&uk=1427289872";
    private TextView winText;
    private ImageView shareMyCounter;
    private Button chengHao,startgameimg;
private View rootView;
    public StartGameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        chengHao.setText(getWinCounter());
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_start_game, container, false);
        shareMyCounter=(ImageView)rootView.findViewById(R.id.stargameshare);
        shareMyCounter.setOnClickListener(new gamelisenner());
        winText=(TextView)rootView.findViewById(R.id.stargameTextView1);
        startgameimg=(Button)rootView.findViewById(R.id.startgame);
        startgameimg.setOnClickListener(new gamelisenner());
        chengHao=(Button)rootView.findViewById(R.id.stargameButton1);
        chengHao.setText(getWinCounter());
        return rootView;
    }

    public void setAnim(View view){
        Animation ro=new RotateAnimation(0,360);
        ro.setDuration(500);
        view.setAnimation(ro);
    }

    public String getWinCounter(){
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("win", Activity.MODE_PRIVATE);
        String winString=sharedPreferences.getString("wincounter","");
        return winString;
    }

    public void itemShare(String string){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,string);
        startActivity(intent);
    }
    public class gamelisenner implements View.OnClickListener
    {

        @Override
        public void onClick(View p1)
        {
            if(p1.getId()==R.id.startgame){
                Intent intent=new Intent();
                intent.setClass(getActivity(),GameActivity.class);
                startActivity(intent);
            }
            else if(p1.getId()==R.id.stargameshare){
                itemShare("我在坚果英语中得到了"+getWinCounter()+"分，谁敢与我一战"+appURL);
            }
        }


    }
}
