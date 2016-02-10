package com.homeliv.piyush.homeliv_admin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by piyush on 7/2/16.
 */
public class scopeofwork extends ActionBarActivity implements View.OnClickListener {
    ImageButton edit,save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Jesus","Enter in the scopeofwork System.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scope_of_work);
        Log.d("Jesus", "After setContent view in scopeofwork.");
        init();

        edit.setOnClickListener(this);
        save.setOnClickListener(this);

    }

    public void init() {
        edit = (ImageButton)findViewById(R.id.edit_scopeof_work);
        save = (ImageButton)findViewById(R.id.save_scope_of_work);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_scopeof_work:
                Toast.makeText(getApplicationContext(),"Edit Button Clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.save_scope_of_work:
                Toast.makeText(getApplicationContext(),"Save Button Clicked",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
