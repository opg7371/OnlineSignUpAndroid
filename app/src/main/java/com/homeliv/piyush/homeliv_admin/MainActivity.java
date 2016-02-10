package com.homeliv.piyush.homeliv_admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    Button signupmain ,loginmain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"Main Activity Finally Opended",Toast.LENGTH_LONG).show();
        signupmain = (Button)findViewById(R.id.signup_button);
        loginmain = (Button)findViewById(R.id.login_button);

        signupmain.setOnClickListener(this);
        loginmain.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_button:
                Toast.makeText(v.getContext(),"Sign up  clicked",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
                break;
            case R.id.login_button:
                Toast.makeText(v.getContext(),"Login Button Clicked",Toast.LENGTH_LONG).show();
                Intent j = new Intent(MainActivity.this, Login.class);
                startActivity(j);
                break;
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
