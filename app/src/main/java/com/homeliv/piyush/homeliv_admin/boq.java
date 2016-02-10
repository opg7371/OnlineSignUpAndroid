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
public class boq extends ActionBarActivity implements View.OnClickListener {
    Place[] myPlacesArray = new Place[] {

            new Place("Particular Item 1",10,"Sqft.",10,100),
            new Place("Particular Item 2",10,"Sqft.",10,100),
            new Place("Particular Item 3",10,"Sqft.",10,100),
            new Place("Particular Item 4",10,"Sqft.",10,100),
            new Place("Particular Item 5",10,"Sqft.",10,100),
            new Place("Particular Item 6",10,"Sqft.",10,100),
            new Place("Particular Item 7",10,"Sqft.",10,100),
            new Place("Particular Item 8",10,"Sqft.",10,100),
            new Place("Particular Item 9",10,"Sqft.",10,100),
            new Place("Particular Item 10",10,"Sqft.",10,100),
    };


    ImageButton add_new_boq;
    private ListView mListViewboq;

    private boqAdapter mPlaceAdapter;
    String project_title = "", project_status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boq);
        init();


        mPlaceAdapter = new boqAdapter(getApplicationContext(),R.layout.boq_layout,myPlacesArray);
        if(mListViewboq!=null) {
            mListViewboq.setAdapter(mPlaceAdapter);
        }

        mListViewboq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("Place", myPlacesArray[position].particular);
                startActivity(new Intent(boq.this,Boq_histroy.class));
            }
        });



        add_new_boq.setOnClickListener(this);
    }

    public void init() {
        add_new_boq = (ImageButton) findViewById(R.id.add_new_boq);
        mListViewboq = (ListView)findViewById(R.id.myboqlistview);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_new_boq:
                startActivity(new Intent(boq.this, boq_create.class));
        }

    }

}