package com.homeliv.piyush.homeliv_admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by piyush on 7/2/16.
 */
public class boqAdapter extends ArrayAdapter<Place> {
    Context mcontext;
    int mLayoutResourceId;
    Place mData[] = null;
    String mname;
    public boqAdapter(Context context, int resource, Place[] objects) {
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

        TextView particulars = (TextView)row.findViewById(R.id.particular);
        TextView quantity = (TextView)row.findViewById(R.id.quantity);
        TextView unit = (TextView)row.findViewById(R.id.unit);
        TextView rate = (TextView)row.findViewById(R.id.rate);
        TextView total = (TextView)row.findViewById(R.id.total);
        //Get the data from the data array
        Place place = mData[position];

        //setting the data to cretate a view we need
        if(place.particular.length() >= 13) {
            mname = place.particular.substring(0,12)+"...";
        }
        else {
            mname = place.particular;
        }

        particulars.setText(place.particular);
        quantity.setText(String.valueOf(place.quantity) );
        unit.setText(place.Unit);
        rate.setText(String.valueOf(place.rate));
        total.setText(String.valueOf(place.total));



        //returning  the row view  because it is called after all

        return row;
    }
}

