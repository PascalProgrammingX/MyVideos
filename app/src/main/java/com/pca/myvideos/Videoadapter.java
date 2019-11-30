package com.pca.myvideos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class Videoadapter extends BaseAdapter {

    private ArrayList<Videos> videos;
    private Context context;

    public Videoadapter(ArrayList<Videos> videos, Context context){
        this.videos = videos;
        this.context = context;
    }


    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View videoView = LayoutInflater.from(context).inflate(R.layout.single_video, null);

        Videos video = videos.get(position);

        ImageView thumbnail;
        TextView title;
        View view;

        thumbnail = videoView.findViewById(R.id.thumbnail);
        title = videoView.findViewById(R.id.title);
        view = convertView;

        thumbnail.setImageBitmap(video.image);
        title.setText(video.title);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return videoView;
    }


}
