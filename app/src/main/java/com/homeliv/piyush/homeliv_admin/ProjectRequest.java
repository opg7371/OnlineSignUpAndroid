package com.homeliv.piyush.homeliv_admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by piyush on 31/1/16.
 */
public class ProjectRequest {

    static ProgressDialog progressDialog;
        public static final int CONNECTION_TIMEOUT = 1000 * 15;
        public static final String SERVER_ADDRESS = "http://homelivtestserver.comxa.com/";
       public ProjectRequest(Context context) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Processing...");
            progressDialog.setMessage("Please Wait....");

        }

        public static void storeUserDataInBackground(Contact user, GetUserCallback userCallback) {
            progressDialog.show();
            new StoreUserDataAsynTask(user,userCallback).execute();
        }


    public void fetchUserDataInBackground(Contact user,GetUserCallback callback) {
        progressDialog.show();
        new fetchUserDataAsynTask(user,callback).execute();

    }

    public static class StoreUserDataAsynTask extends AsyncTask<Void,Void,Void> {
            Contact user;
            GetUserCallback userCallback;


            public StoreUserDataAsynTask(Contact user,GetUserCallback userCallback) {

                this.user = user;
                this.userCallback = userCallback;


            }
            @Override
            protected Void doInBackground(Void... params) {
                ArrayList<NameValuePair> dataToSend = new ArrayList<>();
                dataToSend.add(new BasicNameValuePair("project_id",user.project_id));
                dataToSend.add(new BasicNameValuePair("email",user.email));
                dataToSend.add(new BasicNameValuePair("project_title",user.project_name));
                dataToSend.add(new BasicNameValuePair("project_status",user.project_status));
                dataToSend.add(new BasicNameValuePair("username",user.uname));


                BasicHttpParams httpRequestParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
                HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

                HttpClient client = new DefaultHttpClient(httpRequestParams);
                HttpPost post = new HttpPost(SERVER_ADDRESS+"project_register.php");
                try {
                    post.setEntity(new UrlEncodedFormEntity(dataToSend));
                    client.execute(post);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                progressDialog.dismiss();
                userCallback.done(null);
                super.onPostExecute(aVoid);
            }
        }

    public class fetchUserDataAsynTask extends AsyncTask<Void,Void,Contact> {
        Contact user;
        String myDataArray[] = {};
        GetUserCallback userCallback;

        public fetchUserDataAsynTask(Contact user,GetUserCallback userCallback) {

            this.user = user;
            this.userCallback = userCallback;
        }


        @Override
        protected Contact doInBackground(Void... params) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("email",user.email));

            BasicHttpParams httpRequestParams = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS+"FetchUserData_project.php");
            Contact returnedUser=null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);

                try {
                    JSONObject jObject = new JSONObject(result);
                    JSONArray myJSON = jObject.getJSONArray("email");

                    myDataArray = new String[myJSON.length()];

                    for (int i = 0; i < myJSON.length(); i++) {
                        JSONObject jsonObject = myJSON.getJSONObject(i);
                      myDataArray[i] = jsonObject.getString("project_title");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                returnedUser = new Contact(myDataArray);

              /*  if(jObject.length() == 0) {
                    returnedUser = null;
                }
                else {
                    String project_id = jObject.getString("project_id");
                    String email = jObject.getString("email");
                    String project_title = jObject.getString("project_title");
                    String project_status = jObject.getString("project_status");
                    String username = jObject.getString("username");
                    returnedUserone = new Contact(project_id,email,project_title,project_status,username);
                    returnedUser = new Contact[i++];


                }*/

            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return returnedUser ;
        }

        protected void onPostExecute(Contact returnedUser) {
            progressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }

    }


