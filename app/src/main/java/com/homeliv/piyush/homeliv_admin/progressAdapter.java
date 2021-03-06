package com.homeliv.piyush.homeliv_admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by piyush on 7/2/16.
 */
public class progressAdapter extends ArrayAdapter<Place> {
    Context mcontext;
    int mLayoutResourceId;
    Place mData[] = null;

    public progressAdapter(Context context, int resource, Place[] objects) {
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

        ImageView imageView = (ImageView)row.findViewById(R.id.myprogressimageview);
        TextView myprogresstitle = (TextView)row.findViewById(R.id.myprogresstitle);
        TextView description = (TextView)row.findViewById(R.id.myprogressdescription);
        TextView date = (TextView)row.findViewById(R.id.myprogressDate);


        //Get the data from the data array
        Place place = mData[position];

        //setting the data to cretate a view we need
        int resId =  mcontext.getResources().getIdentifier(place.progressimageview,"drawable",mcontext.getPackageName());
        imageView.setImageResource(resId);


        myprogresstitle.setText(place.progressTitle);
        description.setText(String.valueOf(place.progressDescription) );
        date.setText(place.date);


        //returning  the row view  because it is called after all

        return row;
    }
}
