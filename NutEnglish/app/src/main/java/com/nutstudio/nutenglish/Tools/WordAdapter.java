package com.nutstudio.nutenglish.Tools;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nutstudio.nutenglish.R;
import java.util.List;
import java.util.Random;

/**
 * Created by fei on 2015/11/4.
 */
public class WordAdapter extends PagerAdapter {
    private int[] topcolorSet = {R.color.color1, R.color.color2, R.color.color3, R.color.color4, R.color.color5, R.color.color6,
            R.color.color7, R.color.color8, R.color.color9};
    private int[] bottomcolorSet = {R.color.mcolor1, R.color.mcolor2, R.color.mcolor3, R.color.mcolor4, R.color.mcolor5,
            R.color.mcolor6, R.color.mcolor7, R.color.mcolor8, R.color.mcolor9};
    private String en, cn, explain;
    private List<View> content;
    private Context context;
    private Cursor cursor;
    Random random = new Random();
    private int a;

    public interface ToMainClickListener {
        void onShareBtnClick();
        void onVoiceBtnClick();
        void onMoreInfoClick();
    }

    public WordAdapter(List<View> content, Context context, Cursor cursor) {
        this.content = content;
        this.context = context;
        this.cursor = cursor;
    }

    //viewpager
    @Override
    public int getCount() {
        return content.size();
    }

    @Override
    public boolean isViewFromObject(View p1, Object p2) {
        return p1 == p2;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        View view = content.get(position);
        ImageView shareImg = (ImageView) view.findViewById(R.id.item0Share);
        ImageView voiceImg = (ImageView) view.findViewById(R.id.item0Voice);
        LinearLayout topLin = (LinearLayout) view.findViewById(R.id.lin_item_top);
        LinearLayout bottomLin = (LinearLayout) view.findViewById(R.id.lin_item_bottom);
        TextView itemDc = (TextView) view.findViewById(R.id.item0Dc);
        TextView itemDcN = (TextView) view.findViewById(R.id.item0DcN);
        TextView itemDcText = (TextView) view.findViewById(R.id.item0Text);
        TextView moreInfo = (TextView) view.findViewById(R.id.tv_moreinfo);
        cursor.moveToPosition(position);
        en = cursor.getString(cursor.getColumnIndex(WordDB.ENWORD));
        cn = cursor.getString(cursor.getColumnIndex(WordDB.CNWORD));
        explain = cursor.getString(cursor.getColumnIndex(WordDB.EXPLAIN));
        itemDc.setText(en);
        itemDcN.setText(cn);
        itemDcText.setText(explain);
        a = Math.abs(random.nextInt(bottomcolorSet.length));
        topLin.setBackgroundColor(context.getResources().getColor(topcolorSet[a]));
        bottomLin.setBackgroundColor(context.getResources().getColor(bottomcolorSet[a]));
        shareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof ToMainClickListener) {
                    ((ToMainClickListener) context).onShareBtnClick();
                }
            }
        });
        voiceImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof ToMainClickListener) {
                    ((ToMainClickListener)context).onVoiceBtnClick();
                }
            }
        });
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof ToMainClickListener) {
                    ((ToMainClickListener)context).onMoreInfoClick();
                }
            }
        });
        ((ViewPager) container).addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView(content.get(position));
    }
}
