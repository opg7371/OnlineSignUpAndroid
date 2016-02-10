package com.homeliv.piyush.homeliv_admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Login extends ActionBarActivity implements View.OnClickListener{

    EditText etemail,etpassword;
    CheckBox saveDetails;
    Button login_log;
    TextView tvisConnected,tvregisterLink;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Jesus", "Entered in the login after onCreate");
        setContentView(R.layout.login);
        init();

        checkConnection();
        tvregisterLink.setOnClickListener(this);
       login_log.setOnClickListener(this);
        saveDetails.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(Login.this,MainActivity.class));
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_rateup:
                Toast.makeText(this, "This App RULES...", Toast.LENGTH_LONG).show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public void init() {
        etemail = (EditText)findViewById(R.id.email1);
        etpassword = (EditText)findViewById(R.id.pass1);
        saveDetails = (CheckBox)findViewById(R.id.checkbox);
        login_log = (Button)findViewById(R.id.submitlogin);
        Log.i("dee", login_log.getId() + "");
        tvisConnected = (TextView)findViewById(R.id.tvIsConnected);
        tvregisterLink = (TextView)findViewById(R.id.tvRegisterLink);

    }



    public void checkConnection() {
        if (isConnected()) {
            tvisConnected.setBackgroundColor(Color.GREEN);
            tvisConnected.setText("You are Connected");


        } else {
            tvisConnected.setBackgroundColor(Color.RED);
            tvisConnected.setText("You are not Connected");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitlogin:
                try {
                    String emaill = etemail.getText().toString();
                    String passwor = etpassword.getText().toString();
                    Contact user = new Contact(emaill, passwor);
                    //authenticate(user);
                    userLocalStore.setUserLoggedIn(true);
                    userLocalStore.storeUserData(user);
                    Log.d("Jesus", "Entered in the login on Click View");
                    startActivity(new Intent(Login.this, MainPage.class));
                    Log.d("Jesus", "Entered in the login intent onClick LoginActivity");
                }catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Jesus","Error is "+e.getMessage());
                }
                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(v.getContext(),SignUp.class));
                break;
        }
    }
    private void authenticate(Contact user) {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(Contact returnedUser) {
                if(returnedUser == null) {
                    showErrorMessage();
                }
                else {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect User Details...");
        dialogBuilder.show();
    }

    private void logUserIn(Contact returneduser) {
         userLocalStore.storeUserData(returneduser);
        userLocalStore.setUserLoggedIn(true);

        Intent intent = new Intent(Login.this, MainPage.class);
        //intent.putExtra("user", returneduser);
        startActivity(intent);
    }

   //PERSISTANCE CODE STARTS FROM HERE

    private static final String DATA_FILE = "my file";
    private static final String DATA_FILE1="my_file_1";

    @Override
    protected void onPause() {
        super.onPause();
       if(saveDetails.isChecked()) {
           saveTextFile(etemail.getText().toString());
           saveTextFilePass(etpassword.getText().toString());
       }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(saveDetails.isChecked()) {
            etemail.setText(getTextFile());
            etemail.setText(getTextFilePass());
        }
    }

    public String getTextFile() {
        FileInputStream fileInputStream = null;
        String filedata = null;
        try {
            fileInputStream = openFileInput(DATA_FILE);
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            filedata = new String(buffer,"UTF-8");


        } catch (FileNotFoundException e) {
            Log.d("Hi","Error in SaveTextFile..");
            e.printStackTrace();
        }catch (IOException e) {
            Log.d("Hi","Error in SaveTextFile..");
            e.printStackTrace();
        }
        finally {
            try {
                if(fileInputStream!=null){
                    fileInputStream.close();
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filedata;
    }


    public String saveTextFile(String content) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(DATA_FILE1,Context.MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());

        } catch (FileNotFoundException e) {
            Log.d("Hi","Error in SaveTextFile..");
            e.printStackTrace();
        }catch (IOException e) {
            Log.d("Hi","Error in SaveTextFile..");
            e.printStackTrace();
        }
        finally {
            try {
                if(fileOutputStream!=null){
                    fileOutputStream.close();
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getTextFilePass() {
        FileInputStream fileInputStream = null;
        String filedata = null;
        try {
            fileInputStream = openFileInput(DATA_FILE1);
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            filedata = new String(buffer, "UTF-8");

        } catch (IOException e) {
                Log.d("Hi","Error in SaveTextFile..");
                e.printStackTrace();
      }
        finally {
            try {
                if(fileInputStream!=null){
                    fileInputStream.close();
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filedata;
    }


    public String saveTextFilePass(String content) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(DATA_FILE, Context.MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());

        } catch (FileNotFoundException e) {
            Log.d("Hi", "Error in SaveTextFile..");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("Hi", "Error in SaveTextFile..");
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
