package com.homeliv.piyush.homeliv_admin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

/**
 * Created by piyush on 7/2/16.
 */
public class boq_create extends ActionBarActivity {

    private ListView mListView;

    private PlaceAdapter mPlaceAdapter;
    String project_title = "", project_status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boq_create);
        init();

}

public void init() {


}
}