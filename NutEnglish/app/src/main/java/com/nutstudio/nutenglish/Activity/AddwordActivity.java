package com.nutstudio.nutenglish.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nutstudio.nutenglish.R;
import com.nutstudio.nutenglish.Tools.UITools;
import com.nutstudio.nutenglish.Tools.WordDB;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddwordActivity extends Activity {
    private WordDB wordDb;
    private SQLiteDatabase dbWriter;
    private EditText cnWord, enWord, explain;
    private Button commitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordDb = new WordDB(this);
        dbWriter = wordDb.getWritableDatabase();
        new UITools(this, this).setStatusBar();
        setContentView(R.layout.activity_addword);
        initView();
    }

    private void initView() {
        cnWord = (EditText) this.findViewById(R.id.et_putin_cn);
        enWord = (EditText) this.findViewById(R.id.et_putin_en);
        explain = (EditText) this.findViewById(R.id.et_putin_explain);
        commitBtn = (Button) this.findViewById(R.id.btn_finish);
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDB();
                finish();
                Toast.makeText(AddwordActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createDB() {
        new Thread() {
            public void run() {
                ContentValues cv = new ContentValues();
                cv.put(WordDB.ENWORD, enWord.getText().toString());
                cv.put(WordDB.CNWORD, cnWord.getText().toString());
                cv.put(WordDB.EXPLAIN, explain.getText().toString());
                cv.put(WordDB.TIME, getTime());
                dbWriter.insert(WordDB.TABLE_NAME, null, cv);
            }
        }.start();
    }

    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);
        return str;
    }
}
