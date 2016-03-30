package com.nutstudio.nutenglish.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nutstudio.nutenglish.R;
import com.nutstudio.nutenglish.Activity.WordActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainFragment extends Fragment {
    private String dc[] = {"engine lathe", " turret lathe", "vertical turning mill ", "automatic lathe", "horizontal", " vertical", "milling machine", " boring mill ", "drilling machine ", "grinding machine ", "machine tool ", "headstock", "chuck ", "tool post", "carriage", "tailstock ", "spindle", "handwheel", "gearbox ", "guideway ", "cast iron ", "grey cast iron", "nodular cast iron", "bar stock", " workpiece", "nut", "screw", "twist drills ", "boring bars", "process", "instruction ", "mainframe", "application ", "processor ", "temporary", "permanent ", "operator", " instruction  system", "lump", " metal", "magnetic", "shield ", "workpiece ", "hydraulic", "tank", "filter", "altitude", "friction ", "AC ", "DC", " apparatus", "blower ", "controller", "crane", " excitation ", "motor ", " rectifier ", "resistor", "capacitor ", "inductor", "torque", "hoist", "comparison", "density", "diesel", "range", "vehicle ", "simplicity", "PLC", "characteristic", "command", "portable", "robot ", "universal", "weld", "conception ", "performance ", "interface ", "input ", "keyboard", "stress", "terminal", "CAD", "off-line", "CAM"};
    private String dcN[] = {"普通车床", " 转塔车床", "立式车床", "自动车床", "卧式", "立式", "铣床", " 镗床", "钻床", "磨床", " 机床", " 主轴箱", "卡盘", "刀架", "溜板箱", "尾架", "轴", " 手轮", " 齿轮箱", "导轨", "铸铁", " 灰铸铁", "球磨铸铁", "棒料", " 工件", "螺母", " 螺杆", "麻花钻", "镗杆", "加工", "指令", "大型机", " 应用", "处理器", "暂时的", "永久的", "运算数", "指令系统", " 块状", "金属", "磁的", "屏蔽", " 工件", "液压的", "水箱", "过滤器", "海拔", "摩擦", "交流", "直流", " 仪器", "鼓风机", "控制器", "起重机", " 励磁", "电动机", "整流器", "电阻器", "电容器", " 电感器", "转矩", "卷扬机", "比较", " 密度", " 柴油机", "范围", " 车辆", "简易性", "可编程控制器", " 特点", "命令", "便携式的", " 机器人", "通用的", "焊接", "概念", "性能", "接口", "输入", "键盘", "压力", "终端", "计算机辅助设计", "脱机", "计算机辅助制造"};
    private String englishText[] = {"Don‘t cry because it is over, smile because it happened", "When falling in love, some lose their head, others lose their heart.", "Happineis good health and a bad memory.", "Love is the only thing that holds the dark at bay.", "Love is a game that two can play and both win."};
    private String englishTextN[] = {"不要因为结束而哭泣，微笑吧，为你的曾经拥有。", "在陷入情网的时候，有些人丢了头脑，另一些人丢了心", "幸福是良好的健康加上糟糕的记性", "唯有爱可以把黑暗囚在波港湾里。", "爱是两个人玩的双赢游戏。"};
    private final int textMaxNum = englishText.length;
    private Button unit1, unit2, unit3, unit4, unit5, unit6, unit7, unit8;
    private EditText eText;
    private TextView drawMainText, drawTextN;
    private ImageView seacherButton, drawflash;
    private GridView gridView;
    private final int MAXINDEX = dc.length;
    private View rootView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> wordIndex=new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_study, container, false);
        getWordABC();
        gridView=(GridView)rootView.findViewById(R.id.myWordTable);
        arrayAdapter=new ArrayAdapter<String>(getActivity(),R.layout.worditem,R.id.wordBtn,wordIndex);
        gridView.setAdapter(arrayAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jumpActivity(position);
            }
        });
        drawMainText=(TextView)rootView.findViewById(R.id.drawTextView1);
        drawTextN=(TextView)rootView.findViewById(R.id.drawTextViewN);
       /* seacherButton=(ImageView)rootView.findViewById(R.id.drawButton1);
        seacherButton.setOnClickListener(new DrawLisenner());*/
        drawflash=(ImageView)rootView.findViewById(R.id.drawFlashImg);
        drawflash.setOnClickListener(new DrawLisenner());
       // eText=(EditText)rootView.findViewById(R.id.drawEditText1);
        flashEnglish();

        return rootView;
    }

    private void getWordABC() {
        int j=0;
        for (char i='a';i<='z';i++){
            wordIndex.add(j,i+"");
            j++;
        }
    }

    public void setAnim(View view){
        Animation scle=new ScaleAnimation(1.0f,0.8f,1.0f,0.8f,50,50);
        scle.setDuration(200);
        view.startAnimation(scle);
    }
    public void flashEnglish(){
        AnimationSet animset=new AnimationSet(true);
        Animation trans=new TranslateAnimation(0,-300,0,0);
        Animation alpha=new AlphaAnimation(1.0f,0.0f);
        animset.addAnimation(trans);
        animset.addAnimation(alpha);
        animset.setDuration(1000);
        drawMainText.startAnimation(animset);
        drawTextN.startAnimation(animset);
        animset.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation p1)
            {
                // TODO: Implement this method
            }
            @Override
            public void onAnimationEnd(Animation p1)
            {
                Random random=new Random();
                int a=Math.abs(random.nextInt(textMaxNum));
                drawMainText.setText(englishText[a]);
                drawTextN.setText(englishTextN[a]);
                // TODO: Implement this method
            }

            @Override
            public void onAnimationRepeat(Animation p1)
            {
                // TODO: Implement this method
            }
        });


    }
    public void myToast(String string){
        Toast atoast;
        atoast=Toast.makeText(getActivity(),string,Toast.LENGTH_SHORT);
        atoast.setGravity(Gravity.CENTER, 0, 0);
        atoast.show();
    }
   /* @Override
    protected void onResume()
    {	if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
        super.onResume();
    }*/
    public void jumpActivity( int jumpPager){
        Intent intent = new Intent();
        intent.setClass(getActivity(), WordActivity.class);
        intent.putExtra("picture",jumpPager);
        startActivity(intent);
    }
    public class DrawLisenner implements View.OnClickListener
    {

        @Override
        public void onClick(View p1)
        {
           /* if(p1.getId()==R.id.drawButton1){

                List lista = Arrays.asList(dc);
                List listb=Arrays.asList(dcN);
                for(int arrayIndex=0;arrayIndex<=MAXINDEX;arrayIndex++){

                    if(eText.getText().toString().equals(dc[arrayIndex])||eText.getText().toString().equals(dcN[arrayIndex])){

                        jumpActivity(arrayIndex);
                        break;
                    }
                    else if(eText.getText().toString().equals("")){
                        myToast("请点击输入框输入单词");
                        break;
                    }
                    else if(lista.contains(eText.getText().toString())==false&&listb.contains(eText.getText().toString())==false){
                        myToast("未收录此单词");
                        break;
                    }

                    // TODO: Implement this method
                }
            }
            else*/ 
      if(p1.getId()==R.id.drawFlashImg){
                Animation rotat=new AlphaAnimation(1.0f,0.1f);
                rotat.setDuration(1000);
                drawflash.startAnimation(rotat);
                flashEnglish();
            }
        }


    }
}
