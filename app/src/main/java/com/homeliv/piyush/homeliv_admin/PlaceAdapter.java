package com.homeliv.piyush.homeliv_admin;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by piyush on 29/1/16.
 */
public class PlaceAdapter extends ArrayAdapter<Place> {

    Context mcontext;
    int mLayoutResourceId;
    Place mData[] = null;
    String mname;
    public PlaceAdapter(Context context, int resource, Place[] objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.mLayoutResourceId = resource;
        this.mData = objects;
    }

    @Override
    public Place getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        // inflate the layout for a single line
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        row = layoutInflater.inflate(mLayoutResourceId,parent,false);

        //get a reference  to the different view elements we wish to update

        TextView nameView = (TextView)row.findViewById(R.id.mainTitle);
        TextView codeView = (TextView)row.findViewById(R.id.smalltext);
        TextView statusView = (TextView)row.findViewById(R.id.status);
        ImageView imageView = (ImageView)row.findViewById(R.id.myImage);

        //Get the data from the data array
        Place place = mData[position];

        //setting the data to cretate a view we need
        if(place.mNameofPlace.length() >= 13) {
             mname = place.mNameofPlace.substring(0,12)+"...";
        }
        else {
            mname = place.mNameofPlace;
        }

        if(place.status == "OnGoing") {
            statusView.setTextColor(Color.GREEN);
        }
        if(place.status == "Cancelled") {
            statusView.setTextColor(Color.RED);
        }
        if(place.status == "Completed") {
            statusView.setTextColor(Color.GRAY);
        }
        nameView.setText(mname);
        codeView.setText(String.valueOf(place.mainCode) );
        statusView.setText(place.status);

        int resId =  mcontext.getResources().getIdentifier(place.mNameofImage,"drawable",mcontext.getPackageName());
        imageView.setImageResource(resId);

        //returning  the row view  because it is called after all

        return row;
    }
}
