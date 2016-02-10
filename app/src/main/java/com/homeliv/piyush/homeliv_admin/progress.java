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
public class progress extends ActionBarActivity implements View.OnClickListener{
    Place[] myPlacesArray = new Place[] {

            new Place("kumb","Progress Drawing-1","This is a smaple progress","1/1/2015"),
            new Place("kumb","Progress Drawing-2","This is a smaple progress","1/1/2015"),
            new Place("kumb","Progress Drawing-3","This is a smaple progress","1/1/2015"),
            new Place("kumb","Progress Drawing-4","This is a smaple progress","1/1/2015"),
            new Place("kumb","Progress Drawing-5","This is a smaple progress","1/1/2015"),
            new Place("kumb","Progress Drawing-6","This is a smaple progress","1/1/2015"),
            new Place("kumb","Progress Drawing-7","This is a smaple progress","1/1/2015"),
            new Place("kumb","Progress Drawing-8","This is a smaple progress","1/1/2015"),
            new Place("kumb","Progress Drawing-9","This is a smaple progress","1/1/2015"),
            new Place("kumb","Progress Drawing-10","This is a smaple progress","1/1/2015"),

    };


    private ListView mListViewprogress;
    ImageButton add_new_progress;

    private progressAdapter mPlaceAdapter;
    String project_title="",project_status="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        init();


   /*     setListAdapter(new ArrayAdapter(
                this, android.R.layout.simple_list_item_1,
                this.populate()));

*/
        mPlaceAdapter = new progressAdapter(getApplicationContext(),R.layout.progress_layout,myPlacesArray);
        if(mListViewprogress!=null) {
            mListViewprogress.setAdapter(mPlaceAdapter);
        }

        mListViewprogress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("Place", myPlacesArray[position].progressTitle);
            }
        });


        add_new_progress.setOnClickListener(this);
    }

    public void init() {
        add_new_progress = (ImageButton) findViewById(R.id.add_new_progress);
        mListViewprogress = (ListView)findViewById(R.id.myprogresslist);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_new_progress:
                startActivity(new Intent(progress.this,Progress_create.class));
        }

    }

}