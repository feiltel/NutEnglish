package com.nutstudio.nutenglish.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.nutstudio.nutenglish.Activity.SearchWordActivity;
import com.nutstudio.nutenglish.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {


    public PeopleFragment() {
        // Required empty public constructor
    }
    private String appURL="http://pan.baidu.com/share/link?shareid=1496095071&uk=1427289872";
    private Button erweima,guanwang;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_people, container, false);
        erweima=(Button)rootView.findViewById(R.id.erweima);
        erweima.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {
                Toast.makeText(getActivity(), "扫描二维码，或者长按下载新版APP。", Toast.LENGTH_SHORT).show();
                // TODO: Implement this method
            }


        });
        erweima.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View p1)
            {

                Uri uri=Uri.parse(appURL);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);


                // TODO: Implement this method
                return false;
            }


        });
        guanwang=(Button)rootView.findViewById(R.id.guanwang);
        guanwang.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View p1)
            {

               /* Uri uri=Uri.parse("http://m.feiltel.icoc.cc/");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);*/

                // TODO: Implement this method
            }


        });

        return rootView;
    }


}
