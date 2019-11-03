package com.soccermat.ultramed.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soccermat.ultramed.R;
import com.soccermat.ultramed.models.DataExercies;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomeTextAdapter extends  ViewPageAdapter{



    Context mContext;
    LayoutInflater mLayoutInflater;
    List<String> exerciesData;
    HitStatusAPI lst;

    public CustomeTextAdapter(Context context,HitStatusAPI lst) {
        this.exerciesData = exerciesData;
        mContext = context;
        exerciesData=new ArrayList<String>();
        this.lst=lst;

        exerciesData.add("dadadfae1");
        exerciesData.add("dadadfae2");
        exerciesData.add("dadadfae3");

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public View getView(int position, ViewPager pager) {
        ViewGroup itemView = (ViewGroup) mLayoutInflater.inflate(R.layout.custom_text_adapter, null);

        Log.e("--->>>","text");
        TextView imageView = (TextView) itemView.findViewById(R.id.tv_view);
        imageView.setText(exerciesData.get(position));
        Log.e("----pos",position+"");
        if(position==2)
        {
            lst.statusApiText();
        }



       /* Picasso.with(mContext).setLoggingEnabled(true);
        Picasso.with(mContext).load().error( R.drawable.ic_label_green )
                .placeholder( R.drawable.progress_animation).into(imageView);*/

        // imageView.setImageResource(exerciesData.get(position).getFiles());



        return itemView;
    }

}
 interface HitStatusAPI{

    void statusApiText();
}
