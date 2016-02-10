package com.homeliv.piyush.homeliv_admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * Created by piyush on 7/2/16.
 */
public class drawings extends ActionBarActivity implements View.OnClickListener{
    Place[] myPlacesArray = new Place[] {

            new Place("kumb","Drawing - 1","This image represents a landscape of India"),
            new Place("kumb","Drawing - 2","This image represents a landscape of India"),
            new Place("kumb","Drawing - 3","This image represents a landscape of India"),
            new Place("kumb","Drawing - 4","This image represents a landscape of India"),
            new Place("kumb","Drawing - 5","This image represents a landscape of India"),
            new Place("kumb","Drawing - 6","This image represents a landscape of India"),
            new Place("kumb","Drawing - 7","This image represents a landscape of India"),
            new Place("kumb","Drawing - 8","This image represents a landscape of India"),
            new Place("kumb","Drawing - 9","This image represents a landscape of India"),



    };


    ImageButton add_new_drawings;
    private ListView mListViewdraw;

    private drawingAdapter mPlaceAdapter;
    String project_title = "", project_status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Jesus","Entered in the drawing's part before oncreate");
        super.onCreate(savedInstanceState);
        Log.d("Jesus", "Entered in the drawing's part before setCOntentView");
        setContentView(R.layout.drawings);
        Log.d("Jesus", "Entered in the drawing's part after oncreate");
        init();

        try {
            mPlaceAdapter = new drawingAdapter(getApplicationContext(), R.layout.drawings_layout, myPlacesArray);
            Log.d("Jesus", "mPlaceAdapter is created Successfully. " + mListViewdraw.toString());
            if (mListViewdraw != null) {
                mListViewdraw.setAdapter(mPlaceAdapter);
            }

            mListViewdraw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.v("Place", myPlacesArray[position].drawingTitle);
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
            Log.d("Jesus",e.getMessage());
        }
        add_new_drawings.setOnClickListener(this);
    }

    public void init() {
        add_new_drawings = (ImageButton) findViewById(R.id.add_new_drawings);
        mListViewdraw = (ListView)findViewById(R.id.mydrawingslist);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_new_drawings:
                startActivity(new Intent(drawings.this, drawing_create.class));
        }

    }

}