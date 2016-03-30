package com.nutstudio.nutenglish.Activity;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.content.res.Configuration;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import com.nutstudio.nutenglish.Fragment.MainFragment;
import com.nutstudio.nutenglish.Fragment.PeopleFragment;
import com.nutstudio.nutenglish.Fragment.StartGameFragment;
import com.nutstudio.nutenglish.R;
import com.nutstudio.nutenglish.Tools.UITools;
import com.nutstudio.nutenglish.service.LogService;

import java.security.Provider;

public class MainActivity extends Activity {
    protected static final String ACTIVITY_TAG = "tang";
    //第一阶段
    private MainFragment studyFragment = new MainFragment();
    private StartGameFragment startGameFragment = new StartGameFragment();
    private PeopleFragment peopleFragment = new PeopleFragment();
    private String appURL = "http://pan.baidu.com/share/link?shareid=1496095071&uk=1427289872";
    private LinearLayout mainLin, startgameLin, peopleLin;
    private ImageView topSearch, topMenu, bottomLean, bottomGame, bottomMore;


    private static final boolean FINAL_CONSTANT_IS_LOCAL = true;
    private static final String TAG = Provider.class.getSimpleName();

    private String getLogTag() {
        if (FINAL_CONSTANT_IS_LOCAL) {
            Throwable stack = new Throwable().fillInStackTrace();
            StackTraceElement[] trace = stack.getStackTrace();
            return trace[1].getClassName().toString() + "=>" + trace[1].getMethodName() + ":" + trace[1].getLineNumber();
        } else {
            return TAG;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(ACTIVITY_TAG, getLogTag());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(ACTIVITY_TAG, getLogTag());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ACTIVITY_TAG, getLogTag());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(ACTIVITY_TAG, getLogTag());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(ACTIVITY_TAG, getLogTag());
        Log.d(ACTIVITY_TAG, getLogTag());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(ACTIVITY_TAG, getLogTag());
        super.onCreate(savedInstanceState);
        startService(new Intent(MainActivity.this, LogService.class));
        new UITools(this, this).setStatusBar();
        if (savedInstanceState == null) {
            jump2fragment(studyFragment);
        }
        setContentView(R.layout.main);
        mainLin = (LinearLayout) this.findViewById(R.id.lin_main);
        startgameLin = (LinearLayout) this.findViewById(R.id.lin_game);
        peopleLin = (LinearLayout) this.findViewById(R.id.lin_people);
        topMenu = (ImageView) this.findViewById(R.id.im_top_menu);
        topSearch = (ImageView) this.findViewById(R.id.im_top_search);
        bottomLean = (ImageView) this.findViewById(R.id.im_bottom_lean);
        bottomGame = (ImageView) this.findViewById(R.id.im_bottom_game);
        bottomMore = (ImageView) this.findViewById(R.id.im_bottom_more);
        //初始化底部选择条
        initBottomView();
        bottomLean.setImageDrawable(getResources().getDrawable(R.drawable.ic_lean));
        topSearch.setOnClickListener(new MainLinsenner());
        mainLin.setOnClickListener(new MainLinsenner());
        startgameLin.setOnClickListener(new MainLinsenner());
        peopleLin.setOnClickListener(new MainLinsenner());

    }

    private void initBottomView() {
        Log.d(ACTIVITY_TAG, getLogTag());
        bottomLean.setImageDrawable(getResources().getDrawable(R.drawable.nutenglish));
        bottomGame.setImageDrawable(getResources().getDrawable(R.drawable.game));
        bottomMore.setImageDrawable(getResources().getDrawable(R.drawable.more));
    }

    private void jump2fragment(Fragment fragment) {
        Log.d(ACTIVITY_TAG, getLogTag());
        if (!studyFragment.isAdded()) {
            getFragmentManager().beginTransaction().add(R.id.container, studyFragment).commit();
        }
        if (!startGameFragment.isAdded()) {

            getFragmentManager().beginTransaction().add(R.id.container, startGameFragment).commit();
        }
        if (!peopleFragment.isAdded()) {
            getFragmentManager().beginTransaction().add(R.id.container, peopleFragment).commit();
        }
        getFragmentManager().beginTransaction().
                hide(studyFragment).hide(startGameFragment).hide(peopleFragment).commit();
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).show(fragment).commit();
    }

    protected void myDialog() {
        Log.d(ACTIVITY_TAG, getLogTag());
        AlertDialog.Builder exitDialog = new AlertDialog.Builder(this);
        exitDialog.setTitle("确定要退出吗？");
        exitDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface p1, int p2) {
                // TODO: Implement this method
                finish();
            }

        });
        exitDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface p1, int p2) {
                // TODO: Implement this method
            }
        });
        exitDialog.show();
    }

    @Override
    protected void onResume() {

        Log.d(ACTIVITY_TAG, getLogTag());
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    // TODO: Implement this m
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(ACTIVITY_TAG, getLogTag());
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            myDialog();
        }
        // TODO: Implement this method
        return super.onKeyDown(keyCode, event);

    }

    public class MainLinsenner implements OnClickListener {
        @Override
        public void onClick(View p1) {
            if (p1.getId() == R.id.lin_main) {
                initBottomView();
                if (studyFragment.isHidden()) {
                    jump2fragment(studyFragment);
                } else {
                }
                bottomLean.setImageDrawable(getResources().getDrawable(R.drawable.ic_lean));
            } else if (p1.getId() == R.id.lin_game) {
                initBottomView();
                if (startGameFragment.isAdded()) {
                    Toast.makeText(MainActivity.this, "is add", Toast.LENGTH_LONG).show();
                }
                if (startGameFragment.isHidden()) {
                    jump2fragment(startGameFragment);
                }
                bottomGame.setImageDrawable(getResources().getDrawable(R.drawable.game_selct));
            } else if (p1.getId() == R.id.lin_people) {
                initBottomView();
                if (peopleFragment.isHidden()) {
                    jump2fragment(peopleFragment);
                }
                bottomMore.setImageDrawable(getResources().getDrawable(R.drawable.more_selct));
            } else if (p1.getId() == R.id.im_top_search) {
                Log.d(ACTIVITY_TAG, getLogTag());
                startActivity(new Intent(MainActivity.this, SearchWordActivity.class));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "分享");
        menu.add(1, 2, 1, "关于");
        menu.add(1, 3, 1, "退出");
        // TODO: Implement this method
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == 1) {
            startActivity(new Intent(Intent.ACTION_SEND).
                    setType("text/plain").putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text2) + appURL));
        } else if (item.getItemId() == 2) {

        } else if (item.getItemId() == 3) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
