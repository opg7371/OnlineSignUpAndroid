package com.homeliv.piyush.homeliv_admin;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by piyush on 9/2/16.
 */
public class Progress_create extends ActionBarActivity implements View.OnClickListener {

    private static final int REQUEST_CAMERA = 1;
    private ListView mListView;
    ImageView myprogress_image;
    private PlaceAdapter mPlaceAdapter;
    String project_title = "", project_status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_create);
        init();

    }

    public void init() {
        myprogress_image = (ImageView) findViewById(R.id.myprogressimagecreate);
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.myprogressimagecreate:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);


            default:
        }
    }
}
