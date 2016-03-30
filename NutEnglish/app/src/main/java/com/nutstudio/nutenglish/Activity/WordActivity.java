package com.nutstudio.nutenglish.Activity;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.*;
import android.speech.tts.*;
import android.support.v4.view.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import java.util.*;

import com.nutstudio.nutenglish.R;
import com.nutstudio.nutenglish.Tools.UITools;
import com.nutstudio.nutenglish.Tools.WordAdapter;
import com.nutstudio.nutenglish.Tools.WordDB;

public class WordActivity extends Activity implements WordAdapter.ToMainClickListener{

    private String appURL = "http://pan.baidu.com/share/link?shareid=1496095071&uk=1427289872";
    private int pagernumber;
    private int changeNum;
    private int colorarry[] = {R.color.color1, R.color.color2, R.color.color3, R.color.color4, R.color.color5, R.color.color6, R.color.color7};
    private Cursor cursor;
    private WordDB wordDB;
    private SQLiteDatabase dbReader;
    private ImageView  topLogo, topSearch, topMenu,topBack;
    private TextView topAppName;
    private ViewPager vpager;
    private WordAdapter wordViewpageradpter;
    private List<View> content;
    private LayoutInflater inflater;
    private TextToSpeech myTTS;

    //  Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new UITools(this, this).setStatusBar();
        setContentView(R.layout.picture);
        initView();
        initEvent();
        wordDB = new WordDB(this);
        dbReader = wordDB.getWritableDatabase();
        cursor = dbReader.query(WordDB.TABLE_NAME, null, null, null, null, null, null);
        //searchBtn = (ImageView) this.findViewById(R.id.pButton1);
        // searchEdit = (EditText) this.findViewById(R.id.pEtext);
        myTTS = new TextToSpeech(WordActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = myTTS.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(WordActivity.this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
                    }
                }
                // TODO: Implement this method
            }


        });
        //得到主识图页传进来的viewpage页码值
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        pagernumber = bundle.getInt("picture");
        //找到viewpager
        vpager = (ViewPager) this.findViewById(R.id.viewpager);
        inflater = LayoutInflater.from(this);
        content = new ArrayList<View>();
        // setViewPager();
        setViewList();
        wordViewpageradpter = new WordAdapter(content, this, cursor);

        wordViewpageradpter.notifyDataSetChanged();
        vpager.setAdapter(wordViewpageradpter);
        //设置要跳转的页码
        vpager.setCurrentItem(pagernumber);
        vpager.setPageTransformer(true, new DepthPageTransformer());
    /*   searchBtn.setOnClickListener(new OnClickListener(){
               @Override
			   public void onClick(View p1)
			   { 
			   List lista = Arrays.asList(dc);
			   List listb=Arrays.asList(dcN);
				   for(int arrayIndex=0;arrayIndex<=MAXINDEX;arrayIndex++){
					
					   if(searchEdit.getText().toString().equals(dc[arrayIndex])||searchEdit.getText().toString().equals(dcN[arrayIndex])){						 
						   vpager.setCurrentItem(arrayIndex);
						   break;
					   }
					   else if(searchEdit.getText().toString().equals("")){
						  myToast("请点击输入框输入单词");
						   break;
					   }
					   else if(lista.contains(searchEdit.getText().toString())==false&&listb.contains(searchEdit.getText().toString())==false){
						  myToast("未收录此单词");
						   break;
					   }
					   
				   // TODO: Implement this method
			   }

}
		   });*/
    }

    private void initEvent() {
        topBack.setVisibility(View.VISIBLE);
        topLogo.setVisibility(View.GONE);
        topAppName.setText("      ");
        topMenu.setImageDrawable(getResources().getDrawable(R.drawable.ic_add));
        topBack.setOnClickListener(new MyWordClick());
        topMenu.setOnClickListener(new MyWordClick());
        topSearch.setOnClickListener(new MyWordClick());
    }
    private void initView() {
        topLogo = (ImageView) this.findViewById(R.id.im_top_logo);
        topBack=(ImageView)this.findViewById(R.id.im_top_back);
        topSearch = (ImageView) this.findViewById(R.id.im_top_search);
        topMenu = (ImageView) this.findViewById(R.id.im_top_menu);
        topAppName = (TextView) this.findViewById(R.id.tv_top_title);
    }
    public void myToast(String string) {
        Toast atoast;
        atoast = Toast.makeText(WordActivity.this, string, Toast.LENGTH_SHORT);
        atoast.setGravity(Gravity.CENTER, 0, 0);
        atoast.show();
    }
/*
	public void setImage(int number){
		String myJpgPath = "/sdcard/NutEnglish/item"+number+".png";
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 2;
		Bitmap bm = BitmapFactory.decodeFile(myJpgPath, options);
		itemImg.setImageBitmap(bm);
	}*/

    @Override
    protected void onResume() {
       /* if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }*/
        wordViewpageradpter.notifyDataSetChanged();
        // TODO: Implement this method
        super.onResume();
    }

    public void itemShare(String string, String stringb) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "今天我通过#坚果英语#学会了专业词汇" + string + ":" + stringb + "  。" + " 学专业英语So easy!!!" + "速来下载" +
                appURL);
        startActivity(intent);
    }

    /*
     public void saveHeart(String item,String number){
         SharedPreferences mySharedPreferences= getSharedPreferences("isSaveHeart",Activity.MODE_PRIVATE);
 //实例化SharedPreferences.Editor对象（第二步）
         SharedPreferences.Editor editor = mySharedPreferences.edit();
 //用putString的方法保存数据
         editor.putString(item,number);
 //提交当前数据
         editor.commit();
     }
     public String setHeart(String mitem){
         SharedPreferences sharedPreferences= getSharedPreferences("isSaveHeart",Activity.MODE_PRIVATE);
         String heartnumber=sharedPreferences.getString(mitem,"");
     return heartnumber;

     }*/
    public void setUnitToast() {
        while (vpager.getCurrentItem() == 10) {
            Toast.makeText(WordActivity.this, "第二单元", Toast.LENGTH_SHORT).show();
        }
    }

    public void setViewList() {
        for (changeNum = 0; changeNum < cursor.getCount(); changeNum++) {
            View myView = inflater.inflate(R.layout.carditem, null);
            content.add(myView);
        }
    }

    @Override
    public void onShareBtnClick() {
        cursor.moveToPosition(vpager.getCurrentItem());
       String en = cursor.getString(cursor.getColumnIndex(WordDB.ENWORD));
       String cn = cursor.getString(cursor.getColumnIndex(WordDB.CNWORD));
        itemShare(en,cn);
    }

    @Override
    public void onVoiceBtnClick() {
        cursor.moveToPosition(vpager.getCurrentItem());
        String en = cursor.getString(cursor.getColumnIndex(WordDB.ENWORD));
        if (myTTS != null && !myTTS.isSpeaking()) {
            myTTS.setPitch(0.8f);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
            myTTS.setSpeechRate(0.8f);
            myTTS.speak(en,
                    TextToSpeech.QUEUE_FLUSH, null);
        }
    }
    @Override
    public void onMoreInfoClick() {
        cursor.moveToPosition(vpager.getCurrentItem());
        String en = cursor.getString(cursor.getColumnIndex(WordDB.ENWORD));
        Intent intent = new Intent();
        intent.setClass(WordActivity.this,WebActivity.class);
        intent.putExtra("keyword",en);
        startActivity(intent);
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        myTTS.stop(); // 不管是否正在朗读TTS都被打断  
        myTTS.shutdown(); // 关闭，释放资源  
    }


    private class MyWordClick implements OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.im_top_back:
                    finish();
                    break;
                case R.id.im_top_search:

                    break;
                case R.id.im_top_menu:
                    startActivity(new Intent(WordActivity.this,AddwordActivity.class));

                break;
            }
        }
    }
}

