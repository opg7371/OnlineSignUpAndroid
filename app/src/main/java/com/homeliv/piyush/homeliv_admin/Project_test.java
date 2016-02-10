package com.homeliv.piyush.homeliv_admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by piyush on 30/1/16.
 */
public class Project_test extends ActionBarActivity implements View.OnClickListener {
    public TextView go_back,scopeofwork,drawings,boq,progress;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            Log.d("Jesus","Entered in the project_test before onCreate");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.project_test);
            init();
            Toast.makeText(this, "Project Test Finally Opended", Toast.LENGTH_LONG).show();

            go_back.setOnClickListener(this);
            scopeofwork.setOnClickListener(this);
            drawings.setOnClickListener(this);
            boq.setOnClickListener(this);
            progress.setOnClickListener(this);

        }

    public void init() {
        go_back = (TextView)findViewById(R.id.go_back);
        scopeofwork = (TextView)findViewById(R.id.scopeofwork);
        drawings = (TextView)findViewById(R.id.drawings);
        boq = (TextView)findViewById(R.id.boq);
        progress = (TextView)findViewById(R.id.progress);

    }


    public void onClick(View v) {
        try {

        switch (v.getId()) {
                case R.id.scopeofwork:
                    startActivity(new Intent(Project_test.this, scopeofwork.class));
                    break;
                case R.id.drawings:
                    startActivity(new Intent(Project_test.this,drawings.class));
                   break;
                case R.id.boq:
                    startActivity(new Intent(Project_test.this,boq.class));
                    break;
                case R.id.progress:
                    startActivity(new Intent(Project_test.this,progress.class));
                    break;
                case R.id.go_back:
                    Intent i = new Intent(Project_test.this,MainPage.class);
                    startActivity(i);
                    break;
            }
        }catch (Exception e) {
            e.printStackTrace();
            Log.d("Jesus","The error is "+e.getMessage());
        }
        }
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);

            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.action_settings:
                    Toast.makeText(this, "Settings Selected", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.action_rateup:
                    Toast.makeText(this, "This App RULES...", Toast.LENGTH_LONG).show();
                default:
                    return super.onOptionsItemSelected(item);
            }
        }




    }



