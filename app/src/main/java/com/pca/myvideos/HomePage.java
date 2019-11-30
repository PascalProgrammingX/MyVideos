package com.pca.myvideos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Objects;

public class HomePage extends AppCompatActivity {

    private  ArrayList<Videos> videos;
    private GridView videoGrid;
    private ProgressBar loading;
    private Videoadapter videoadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

         videos = new ArrayList<>();
        videoGrid = findViewById(R.id.videos);
        loading = findViewById(R.id.loading);

        //Fetching Videos from Device.
        new FetchVideos().execute();


    }



    public  class FetchVideos extends AsyncTask<Void, Void, ArrayList<Videos>> {

        @Override
        protected ArrayList<Videos> doInBackground(Void... voids) {

            ArrayList<Videos> videos = new  ArrayList<>();
            String[] mediaColumns = {MediaStore.Video.Media._ID, MediaStore.Video.Media.DATA, MediaStore.Video.Media.TITLE, MediaStore.Video.Media.SIZE};
            ContentResolver contentResolver = getContentResolver();
            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            Cursor cursor = contentResolver.query(uri, mediaColumns, MediaStore.Video.Media._ID, null, MediaStore.Video.Media.DATE_ADDED + " DESC");

            if (cursor != null && cursor.moveToFirst()) {

                do {
                    Videos newVVI = new Videos();
                    newVVI.filepath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                    newVVI.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                    newVVI.image = ThumbnailUtils.createVideoThumbnail(newVVI.filepath, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);

                    videos.add(newVVI);

                } while (cursor.moveToNext());
                cursor.close();
            }
            return videos;

        }


        @Override
        protected void onPostExecute(ArrayList<Videos> result) {
            super.onPostExecute(videos);
            loading.setVisibility(View.GONE);
            videos = result;
             videoadapter = new Videoadapter(videos, getApplicationContext());
            videoGrid.setAdapter(videoadapter);
        }
    }


}
