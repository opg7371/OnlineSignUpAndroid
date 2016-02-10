package com.homeliv.piyush.homeliv_admin;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * Created by piyush on 9/2/16.
 */
public class Boq_histroy extends ActionBarActivity implements View.OnClickListener {

    ImageButton update_partcular_histroy;
    ListView boqListView;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.boq_histroy);
        init();

        update_partcular_histroy.setOnClickListener(this);
    }

    public void init() {
        boqListView = (ListView)findViewById(R.id.myboqupdatelistview);
        update_partcular_histroy= (ImageButton)findViewById(R.id.update_particular_boq);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.update_particular_boq:
                startActivity(new Intent(Boq_histroy.this,boq_create.class));
        }
    }


}
