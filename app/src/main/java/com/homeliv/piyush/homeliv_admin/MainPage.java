package com.homeliv.piyush.homeliv_admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainPage extends Activity implements View.OnClickListener{

    Place[] myPlacesArray = new Place[] {

            new Place("Project Test 1",78701,"ic_action_present","OnGoing","This project is for java"),
            new Place("Project Test 2",78702,"ic_action_present","Cancelled","This project is for C"),
            new Place("Project Test 3",78703,"ic_action_present","Completed","This project is for C++"),
            new Place("Project Test 4",78704,"ic_action_present","Cancelled","This project is for Python"),
            new Place("Project Test 5",78705,"ic_action_present","OnGoing","This project is for Go"),
            new Place("Project Test 6",78706,"ic_action_present","Completed","This project is for SQL"),
            new Place("Project Test 7",78707,"ic_action_present","Cancelled","This project is for APACHE"),
            new Place("Project Test 8",78708,"ic_action_present","OnGoing","This project is for owowo"),
            new Place("Project Test 9",78709,"ic_action_present","Cancelled","This project is for java"),
            new Place("Project Test 10",787010,"ic_action_present","Completed","This project is for java"),
            new Place("Project Test 11",787011,"ic_action_present","Cancelled","This project is for java"),
            new Place("Project Test 12",787012,"ic_action_present","OnGoing","This project is for java"),


    };


    String[] myStringArray = new String[] {
            "Project Test 1",
            "Project Test 2",
            "Project Test 3",
            "Project Test 4",
            "Project Test 5",
            "Project Test 6",
            "Project Test 7",
            "Project Test 8",
            "Project Test 9",
            "Project Test 10",
            "Project Test 11",
            "Project Test 12",
            "Project Test 13",
            "Project Test 14",
            "Project Test 15",
            "Project Test 16"
    };



    Contact user;
    TextView fullName,logout;
    String email1,username;
    public static final String SERVER_ADDRESS = "http://homelivtestserver.comxa.com/";
    ImageButton add_a_new_project;
    public static int project_id = 0;
    UserLocalStore userLocalStore;
    private ListView mListView;

    private PlaceAdapter mPlaceAdapter;
    String project_title="",project_status="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        init();


   /*     setListAdapter(new ArrayAdapter(
                this, android.R.layout.simple_list_item_1,
                this.populate()));

*/
        /*mPlaceAdapter = new PlaceAdapter(getApplicationContext(),R.layout.row,);
        if(mListView!=null) {
            mListView.setAdapter(mPlaceAdapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("Place", myPlacesArray[position].mNameofPlace);
            }
        });*/

        add_a_new_project.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
        logout.setOnClickListener(this);
      // mListView.setOnItemClickListener(this);

    }

    @Override
    protected void onStart() {

        if(authenticate()) {
            Contact user = UserLocalStore.getLoggedInUser();
            String name = user.fname+" "+user.lname;
            fullName.setText(name);
            String emaill = user.email;
           // Toast.makeText(this,"Email is "+emaill,Toast.LENGTH_LONG).show();
            Contact authenicate_contact = new Contact(emaill);
            //authenticateproject(authenicate_contact);
            checkProject(authenicate_contact);

        }
        else {
            startActivity(new Intent(MainPage.this, Login.class));
        }
        super.onStart();
    }


    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }

    void init () {
        fullName = (TextView)findViewById(R.id.fullname);
        add_a_new_project = (ImageButton)findViewById(R.id.add_new);
        mListView = (ListView) findViewById(R.id.myListView);
        logout = (TextView) findViewById(R.id.logoutmain);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_new:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Enter the Project's Name:");
                final EditText input = new EditText(this);
                input.setBackgroundColor(Color.BLACK);
                input.setTextColor(Color.WHITE);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("CREATE IT..", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Contact useragain = UserLocalStore.getLoggedInUser();
                        project_id = project_id + 1;
                        email1 = useragain.email;
                        username = useragain.uname;
                        project_title = input.getText().toString();
                        project_status = "OnGoing";

                        Contact user = new Contact(project_id + "", email1, project_title, project_status, username);
                        registerProjectTable(user);
                        Contact useremail = new Contact(email1);
                        // authenticateproject(useremail);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                break;

            case R.id.myListView:

                startActivity(new Intent(MainPage.this, Project_test.class));
            case R.id.logoutmain:
                startActivity(new Intent(MainPage.this,Login.class));
            default:
        }
    }

    private void registerProjectTable(Contact user) {
        ProjectRequest projectRequest = new ProjectRequest(this);
        ProjectRequest.storeUserDataInBackground(user, new GetUserCallback() {

            @Override
            public void done(Contact returnedUser) {

            }
        });

    }


    private void authenticateproject(Contact user) {
     //   Toast.makeText(this,"Entered in the authentication Block",Toast.LENGTH_LONG).show();
        ProjectRequest projectRequest = new ProjectRequest(this);
        projectRequest.fetchUserDataInBackground(user, new GetUserCallback() {

            @Override
            public void done(Contact returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    checkProject(returnedUser);
                }
            }
        });
    }

    private void checkProject(Contact returneduser) {
     //   Toast.makeText(this,"Entered in the checkProject Block",Toast.LENGTH_LONG).show();
        userLocalStore.storeUserData(returneduser);
        userLocalStore.setUserLoggedIn(true);
        String myDataArray[] = {};
         Log.d("Mohit", "UserLocal Store Successful");
        Contact retUser = UserLocalStore.getLoggedInUser();
        myDataArray = retUser.myData;
       // ArrayAdapter<String> stringAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.row,myDataArray);
        Log.d("Mohit", "Success in the ArrayAdapter");
        PlaceAdapter placeAdapter = new PlaceAdapter(getApplicationContext(),R.layout.row,myPlacesArray);
        if(mListView != null ) {
           // Toast.makeText(this,"Entered in the mlistView Scope",Toast.LENGTH_LONG).show();
            mListView.setAdapter(placeAdapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.v("Place", myPlacesArray[position].mNameofPlace);

                    startActivity(new Intent(MainPage.this, Project_test.class));
                }
            });
        }


    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainPage.this);
        dialogBuilder.setMessage("Incorrect Project Details...");
        dialogBuilder.show();
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
                startActivity(new Intent(MainPage.this,MainActivity.class));
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_rateup:
                Toast.makeText(this, "This App RULES...", Toast.LENGTH_LONG).show();
            case R.id.logout:
                Toast.makeText(this,"Logout Button Pressed.",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainPage.this,Login.class));
                return true;
             default:
                return super.onOptionsItemSelected(item);
        }
    }


   }
