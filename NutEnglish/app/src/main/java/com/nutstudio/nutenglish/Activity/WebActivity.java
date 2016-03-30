package com.nutstudio.nutenglish.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.nutstudio.nutenglish.R;
import com.nutstudio.nutenglish.Tools.UITools;

public class WebActivity extends Activity {
    private ImageView  topLogo, topSearch, topMenu,topBack;
    private TextView topAppName;
    private WebView webView;
    private String mainUrl;
    private String myurl = "https://www.baidu.com/s?ie=utf-8&fr=bks0000&wd=";
    private String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new UITools(this, this).setStatusBar();
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        keyword = bundle.getString("keyword");
        webView = (WebView) this.findViewById(R.id.myweb);
        mainUrl = myurl + keyword;
        init();
        initView();
        initEvent();
    }
    private void initEvent() {
        topLogo.setVisibility(View.GONE);
        topBack.setVisibility(View.VISIBLE);
        topAppName.setText("      ");
        topMenu.setVisibility(View.GONE);

        topBack.setOnClickListener(new MyWebClick());
        topSearch.setOnClickListener(new MyWebClick());
    }

    private void initView() {
        topLogo = (ImageView) this.findViewById(R.id.im_top_logo);
        topBack = (ImageView) this.findViewById(R.id.im_top_back);
        topSearch = (ImageView) this.findViewById(R.id.im_top_search);
        topMenu = (ImageView) this.findViewById(R.id.im_top_menu);
        topAppName = (TextView) this.findViewById(R.id.tv_top_title);
    }
    private void init() {
        webView.loadUrl(mainUrl);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub

                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    private class MyWebClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.im_top_back:
                    finish();
                    break;
                case R.id.im_top_search:

                    break;
            }
        }
    }
}
