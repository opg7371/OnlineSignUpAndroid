package com.homeliv.piyush.homeliv_admin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by piyush on 7/2/16.
 */
public class drawing_create extends ActionBarActivity implements View.OnClickListener {
    private static final int SELECT_FILE = 2;
    private ListView mListView;
    private final int SELECT_PHOTO = 1;
    private ImageView drawingimageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawings_create);

         drawingimageView.setOnClickListener(this);

    }

    public void init() {
        drawingimageView = (ImageView) findViewById(R.id.drawingcreateimageview);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.drawingcreateimageview:
 ;
            default:
        }

    }
}
