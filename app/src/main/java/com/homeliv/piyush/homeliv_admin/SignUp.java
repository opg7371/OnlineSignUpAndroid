package com.homeliv.piyush.homeliv_admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by piyush on 20/1/16.
 */

    public class SignUp extends ActionBarActivity implements View.OnClickListener{
        EditText fname,lname,email,pass,cpass,mob,username;
        Button register;
        Boolean flag = false ;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.signup);

            init();

            register.setOnClickListener(this);

        }

        public void init () {
            fname = (EditText)findViewById(R.id.fname);
            lname = (EditText)findViewById(R.id.lname);
            email = (EditText)findViewById(R.id.email);
            pass = (EditText)findViewById(R.id.pass);
            username = (EditText)findViewById(R.id.username);
            cpass = (EditText)findViewById(R.id.conf_pass);
            mob = (EditText)findViewById(R.id.mobile);
            register = (Button)findViewById(R.id.register);
        }



        @Override
        public void finish() {
            finish();
        }

    @Override
    public void onClick(View v) {
        if(authenticate_form()) {
            switch (v.getId()) {
                case R.id.register:
                    String firstname = fname.getText().toString();
                    String lastname = lname.getText().toString();
                    String email1 = email.getText().toString();
                    String uname = username.getText().toString();
                    String password = pass.getText().toString();
                    String mobile = mob.getText().toString();
                    Contact user = new Contact(firstname, lastname, email1, uname, password, mobile);

                    registerUser(user);
                    break;
            }
        }
        else {
            Toast.makeText(v.getContext(),"Please Try Again",Toast.LENGTH_LONG).show();
        }
    }

    public boolean authenticate_form() {
        if(fname.getText().length()==0) {
            Toast.makeText(this,"First Name cannot be empty.!!",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(lname.getText().length()==0) {
            Toast.makeText(this,"Last Name cannot be empty.!!",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(email.getText().length()==0) {
            Toast.makeText(this,"Email cannot be empty.!!",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(username.getText().length()==0) {
            Toast.makeText(this,"UserName cannot be empty.!!",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(pass.getText().length() == 0) {
            Toast.makeText(this,"Password cannot be empty.!!",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(cpass.getText().length()==0) {
            Toast.makeText(this,"Confirm Password blank.!!",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(mob.getText().length()==0) {
            Toast.makeText(this,"Contact Information Blank.!!",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(mob.getText().length()!=10) {
            Toast.makeText(this,"Mobile Number is Not Valid. Please Enter a 10 digit valid number. ",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(pass.getText().length()<8 && cpass.getText().length()<8) {
            Toast.makeText(this,"Password length greater than 8 required.!!",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!(pass.getText().toString().equals(cpass.getText().toString()))) {
            Toast.makeText(this,"Password Mismatch",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!(email.getText().toString().contains("@"))) {
            Toast.makeText(this,"Email seems to be INVALID....",Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            Toast.makeText(this,"Form Validation Success",Toast.LENGTH_SHORT).show();
            return true;
        }
    }



    private void registerUser(Contact user) {

        ServerRequest serverRequest = new ServerRequest(this);
        //Toast.makeText(this,)
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(Contact returnedUser) {
                startActivity(new Intent(SignUp.this, Login.class));

            }
        });
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
            case android.R.id.home:
                startActivity(new Intent(SignUp.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}


