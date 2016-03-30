package com.nutstudio.nutenglish.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.nutstudio.nutenglish.Tools.UITools;

/**
 * Created by fei on 2015/12/20.
 */
public class MyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new UITools(this, this).setStatusBar();
        super.onCreate(savedInstanceState);

    }

    protected void initView(){
    }
    protected void initEvent(){
    }
}
