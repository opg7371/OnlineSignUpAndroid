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
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by piyush on 24/1/16.
 */
public class ServerRequest {

   ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://homelivtestserver.comxa.com/";

    public ServerRequest(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please Wait....");
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
    }

    public void storeUserDataInBackground(Contact user,GetUserCallback userCallback) {
            progressDialog.show();
        new StoreUserDataAsynTask(user,userCallback).execute();
    }

    public void fetchUserDataInBackground(Contact user,GetUserCallback callback) {
        progressDialog.show();
        new fetchUserDataAsynTask(user,callback).execute();

    }

    public class StoreUserDataAsynTask extends AsyncTask<Void,Void,Void> {
        Contact user;
        GetUserCallback userCallback;

        public StoreUserDataAsynTask(Contact user,GetUserCallback userCallback) {

            this.user = user;
            this.userCallback = userCallback;


        }
        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("fname",user.fname));
            dataToSend.add(new BasicNameValuePair("lname",user.lname));
            dataToSend.add(new BasicNameValuePair("email",user.email));
            dataToSend.add(new BasicNameValuePair("username",user.uname));
            dataToSend.add(new BasicNameValuePair("password",user.pass));
            dataToSend.add(new BasicNameValuePair("mobile", user.mobile));

            BasicHttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS+"register.php");
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
        GetUserCallback userCallback;

        public fetchUserDataAsynTask(Contact user,GetUserCallback userCallback) {

            this.user = user;
            this.userCallback = userCallback;
        }


        @Override
        protected Contact doInBackground(Void... params) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("email",user.email));
            dataToSend.add(new BasicNameValuePair("password",user.pass));

            BasicHttpParams httpRequestParams = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS+"FetchUserData.php");
            Contact returnedUser=null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if(jObject.length() == 0) {
                    returnedUser = null;
                }
                else {
                    String fname = jObject.getString("firstname");
                    String lname = jObject.getString("lastname");
                    String email = jObject.getString("email");
                    String username = jObject.getString("username");
                    String mobile = jObject.getString("mobile");

                    returnedUser = new Contact(fname,lname,email,username,"",mobile);


                }

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
