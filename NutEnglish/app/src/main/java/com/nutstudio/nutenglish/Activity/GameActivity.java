package com.nutstudio.nutenglish.Activity;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.media.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.widget.*;
import java.util.*;

import com.nutstudio.nutenglish.R;
import com.nutstudio.nutenglish.Tools.UITools;

public class GameActivity extends Activity
{
	private ArrayList mylist=new ArrayList();
	private int winCounter;
	private String dcN[]={"engine lathe" , " turret lathe" , "vertical turning mill ", "automatic lathe" , "horizontal" ," vertical" , "milling machine" ," boring mill ","drilling machine ","grinding machine ","machine tool " ,"headstock" ,"chuck ","tool post" ,"carriage" , "tailstock ","spindle" ,"handwheel" ,"gearbox ","guideway " ,"cast iron ","grey cast iron" ,"nodular cast iron"  ,"bar stock" ," workpiece" ,"nut" ,"screw" ,"twist drills ","boring bars" ,"process" ,"instruction ","mainframe","application ","processor ","temporary" , "permanent ", "operator" ," instruction  system"  ,"lump" ," metal" , "magnetic" ,"shield ","workpiece ","hydraulic" ,"tank" ,"filter" , "altitude" , "friction ", "AC " , "DC" ," apparatus" , "blower ","controller" ,"crane" ," excitation ","motor "," rectifier ","resistor" , "capacitor ", "inductor" ,"torque" , "hoist" ,"comparison" ,"density" ,"diesel" ,"range" ,"vehicle ","simplicity" ,"PLC" ,"characteristic" ,"command" ,"portable" ,"robot ","universal" ,"weld" ,"conception ","performance ","interface "  ,"input ","keyboard" ,"stress" , "terminal" ,"CAD" ,"off-line" ,"CAM"};
	private String dc[]={"普通车床"," 转塔车床", "立式车床", "自动车床", "卧式", "立式", "铣床" ," 镗床" , "钻床", "磨床" ," 机床", " 主轴箱", "卡盘" , "刀架", "溜板箱" , "尾架", "轴"," 手轮"," 齿轮箱", "导轨", "铸铁"," 灰铸铁", "球磨铸铁",  "棒料"," 工件" , "螺母"," 螺杆" , "麻花钻", "镗杆", "加工", "指令", "大型机"," 应用", "处理器", "暂时的" , "永久的", "运算数", "指令系统"," 块状", "金属", "磁的", "屏蔽"," 工件", "液压的", "水箱", "过滤器", "海拔", "摩擦", "交流", "直流"," 仪器", "鼓风机", "控制器", "起重机"," 励磁", "电动机", "整流器", "电阻器", "电容器"," 电感器", "转矩" , "卷扬机" , "比较"," 密度"," 柴油机", "范围"," 车辆", "简易性", "可编程控制器"," 特点", "命令", "便携式的"," 机器人", "通用的", "焊接", "概念", "性能", "接口",  "输入", "键盘", "压力", "终端", "计算机辅助设计", "脱机" , "计算机辅助制造"};
	private final int MAXINDEX=dc.length;
	private SoundPool anisp;
	private int sound[]=new int[3];
	//每一次点击的时间限制10为十秒
	private int stepTime=10;
	//控制进度条状态
	private boolean isRun=true;
	private int pi=0;
	private Button gbutton1,gbutton2,gbutton3,gbutton4;
	private TextView gmain1,gwincounter,bestcounter;
	private ProgressBar game_progressbar;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		new UITools(this,this).setStatusBar();
		setContentView(R.layout.game);
		GameLoadSound gLS=new GameLoadSound();
		gLS.start();
		gwincounter=(TextView)this.findViewById(R.id.gameWinCounter);
		bestcounter=(TextView)this.findViewById(R.id.gameTextView1);
		gmain1=(TextView)this.findViewById(R.id.gamemainbutton);
		gbutton1=(Button)this.findViewById(R.id.gameButton1);
		gbutton2=(Button)this.findViewById(R.id.gameButton2);
		gbutton3=(Button)this.findViewById(R.id.gameButton3);
		gbutton4=(Button)this.findViewById(R.id.gameButton4);
		game_progressbar=(ProgressBar)this.findViewById(R.id.gameProgressBar1);
		gbutton1.setOnClickListener(new GameClick());
		gbutton2.setOnClickListener(new GameClick());
		gbutton3.setOnClickListener(new GameClick());
		gbutton4.setOnClickListener(new GameClick());
		MyThread mt=new MyThread();
		mt.start();
		bestcounter.setText(getWinCounter()+"");
	}
	//复写返回键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_BACK){
			exitGameDialog();
			isRun=false;
			setGbuttonVisibility(false);
		}
		// TODO: Implement this method
		return super.onKeyDown(keyCode, event);
	}
	
	public int getWinCounter(){
		SharedPreferences sharedPreferences= getSharedPreferences("win",Activity.MODE_PRIVATE); 
		String winString=sharedPreferences.getString("wincounter","");
		int winInt=Integer.parseInt(winString);
		return winInt;
	}
	public void saveWinCounter(int number){
		SharedPreferences mySharedPreferences= getSharedPreferences("win",Activity.MODE_PRIVATE); 
//实例化SharedPreferences.Editor对象（第二步） 
		SharedPreferences.Editor editor = mySharedPreferences.edit(); 
//用putString的方法保存数据 
		if(getWinCounter()<number){
			returnDrawAnim(bestcounter);
			editor.putString("wincounter",number+"");
//提交当前数据 
			editor.commit();
		}
		}
	public void setGbuttonVisibility(boolean visi){
		if(visi==true){
		gbutton1.setVisibility(View.VISIBLE);
		gbutton2.setVisibility(View.VISIBLE);
		gbutton3.setVisibility(View.VISIBLE);
		gbutton4.setVisibility(View.VISIBLE);
		}
		else if(visi==false){
			gbutton1.setVisibility(View.GONE);
			gbutton2.setVisibility(View.GONE);
			gbutton3.setVisibility(View.GONE);
			gbutton4.setVisibility(View.GONE);
		}
	}
	protected void overDialog(){
		AlertDialog.Builder upDialog=new AlertDialog.Builder(this);
		upDialog.setCancelable(false);
		upDialog.setTitle("恭喜你,通关了!!!");
		upDialog.setPositiveButton("退出", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					finish();
				}
			});
		upDialog.setNegativeButton("再试一次", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					winCounter=0;
					mylist.clear();
					pi=0;
					isRun=true;
					MyThread mt=new MyThread();
					mt.start();
					returnTextEN();
					setMainDraw();
						}
			});
		upDialog.setOnCancelListener(new DialogInterface.OnCancelListener(){
				@Override
				public void onCancel(DialogInterface p1)
				{
					isRun=true;
					setGbuttonVisibility(true);
					MyThread mt=new MyThread();
					mt.start();
					if(game_progressbar.getProgress()==game_progressbar.getMax()){
						isRun=false;
					}
				}
			});
		upDialog.show();	
	}
	protected void exitGameDialog(){
		AlertDialog.Builder exitDialog=new AlertDialog.Builder(this);
		exitDialog.setCancelable(false);
		exitDialog.setTitle("要退出游戏吗？");
		exitDialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					finish();
				}
			});
		exitDialog.setNegativeButton("继续游戏", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					setGbuttonVisibility(true);
					isRun=true;
					MyThread mt=new MyThread();
					mt.start();
					returnTextEN();
					setMainDraw();
					// TODO: Implement this method
				}
			});
		exitDialog.setOnCancelListener(new DialogInterface.OnCancelListener(){
				@Override
				public void onCancel(DialogInterface p1)
				{
					isRun=true;
					setGbuttonVisibility(true);
					MyThread mt=new MyThread();
					mt.start();
					if(game_progressbar.getProgress()==game_progressbar.getMax()){
						isRun=false;
					}
				}
			});
		exitDialog.show();	
	}
	@Override
	protected void onResume()
	{	/*if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
		{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}*/
		returnTextEN();
		setMainDraw();
		super.onResume();		
	}
	public int agg(int a,int b){
		int c=a+b;
		return c;
	}
	
	
	//换图逻辑
	private void returnTextEN(){	
		Random random=new Random();	
		int s = random.nextInt(10)%(10-7+1)+7;
		int a=0;
		int b=0;
		int c=0;
		int d=0;
		while(a==b||b==c||c==d||d==a||a==c||b==d){
			 
			//不满足条件时跳出
		
			a=Math.abs(random.nextInt(MAXINDEX));
			b=Math.abs(random.nextInt(MAXINDEX));
			c=Math.abs(random.nextInt(MAXINDEX));	
			d=Math.abs(random.nextInt(MAXINDEX));
		}
		//出现不重复的
		int main_draw[]={a,b,c,d};
		int ee=Math.abs(random.nextInt(4));
		int yy=main_draw[ee];
		if(mylist.contains(yy)==false){	
		gmain1.setTag(dc[main_draw[ee]]+"");
			gbutton1.setText(dc[a]);
			gbutton2.setText(dc[b]);
			gbutton3.setText(dc[c]);
			gbutton4.setText(dc[d]);
			gbutton1.setTag(dc[a]+"");
			gbutton2.setTag(dc[b]+"");
			gbutton3.setTag(dc[c]+"");
			gbutton4.setTag(dc[d]+"");
			mylist.add(yy);
		}
		
	else{
		returnTextEN();
			
		}
		
	}
	public void jumpActivty(Context context,Class<?> cls){
		Intent intent=new Intent();

		intent.setClass(context,cls);
		startActivity(intent);
		finish();
	}

	public void setMainDraw(){
		for(int i=0;i<=MAXINDEX;i++){
			if(gmain1.getTag().equals(dc[i])){
				gmain1.setText(dcN[i]);
				break;
			}
		}
	}
	@Override
	protected void onPause()
	{
		isRun=false;
		super.onPause();
	}
	//换图动画
	public void returnDrawAnim(View view){
		Animation apl=new AlphaAnimation(1.0f,0.0f);
		apl.setDuration(500);
		view.startAnimation(apl);
	}
	public void gameOver(){
		anisp.play(sound[1],1,1,0,0,1);
		
		//jumpActivty(GameActivity.this,StarGame.class);
		finish();
	}
	public void gameWin(){
		anisp.play(sound[0],1,1,0,0,1);
		
		winCounter++;
		saveWinCounter(winCounter);
		//进度条复位
		pi=0;				
		//更换文字英文
		returnTextEN();
		//更换主文字中文
		setMainDraw();
		//动画
		returnDrawAnim(gbutton1);
		returnDrawAnim(gbutton2);
		returnDrawAnim(gbutton3);
		returnDrawAnim(gbutton4);
		returnDrawAnim(gmain1);
		returnDrawAnim(gwincounter);
		gwincounter.setText(winCounter+"");
		bestcounter.setText(getWinCounter()+"");
		//Toast.makeText(GameActivity.this,mylist.size()+"",Toast.LENGTH_SHORT).show();
		if(winCounter>=dcN.length-2){
			isRun=false;
			overDialog();
			//Toast.makeText(GameActivity.this,"几点结婚的话",Toast.LENGTH_LONG).show();
		}
	}
	//游戏点击判断
	public class GameClick implements OnClickListener
	{
		@Override
		public void onClick(View p1)
		{
			//星星评级启动
			if(p1.getId()==R.id.gameButton1){
				if((gbutton1.getTag()).equals((gmain1.getTag())))
				{
					gameWin();
					}
				else {		
				gameOver();
			}
			}
			else if(p1.getId()==R.id.gameButton2){
				if((gbutton2.getTag()).equals((gmain1.getTag()))){
					gameWin();
						}
				else{
					gameOver();
					}
												}
			else if(p1.getId()==R.id.gameButton3){
				if((gbutton3.getTag()).equals((gmain1.getTag()))){
				gameWin();

				}
				else{
					gameOver();
				}
				
			}
			else if(p1.getId()==R.id.gameButton4){
				if((gbutton4.getTag()).equals((gmain1.getTag()))){
					gameWin();
				}
				else{
				gameOver();
				}
			}
			
	}
	}
//一秒执行一次
	 Handler myhander=new Handler(){
		@Override
		public void handleMessage(Message msg)
		{
			game_progressbar.setMax(10*stepTime);
			game_progressbar.setProgress(msg.arg1);
		if(msg.arg2==1000){
			anisp.play(sound[1],1,1,0,0,0);
	//		jumpActivty(GameActivity.this, StarGame.class);
			finish();
			isRun=false;
			}
		}
	
};
//进度条更新
public class MyThread extends Thread{
        @Override  
        public void run() { 
		if(isRun==true){
		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException e)
		{}
			Message msg=myhander.obtainMessage();
			msg.arg1=pi;
			pi++;
		if(pi==stepTime*10){
			msg.arg2=1000;
		}
			myhander.sendMessage(msg);
				MyThread myt=new MyThread();
				myt.start();
			}
        }  	
    }
	public class GameLoadSound extends Thread
	{
		//在工作线程里加载声音
		@Override
		public void run()
		{			
			anisp=new SoundPool(3,AudioManager.STREAM_SYSTEM,5);
			sound[0]=anisp.load(GameActivity.this,R.raw.click,1);
			sound[1]=anisp.load(GameActivity.this,R.raw.sound,1);
			//sound[2]=anisp.load(GameActivity.this,R.raw.sound3,1);
		}		
	}
}
