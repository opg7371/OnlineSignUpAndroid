package com.homeliv.piyush.homeliv_admin;

import java.io.Serializable;

/**
 * Created by piyush on 10/1/16.
 */
public class Contact implements Serializable{

    String fname,lname,email,uname,pass,mobile,project_name ,project_id,project_status;
    String myData[] = {};
    public Contact(String fname,String lname,String email,String uname,String pass,String mobile) {
        this.fname = fname;
        this.lname = lname;
        this.project_name="";
        this.email = email;
        this.uname = uname;
        this.pass = pass;
        this.mobile = mobile;

    }
    public Contact(String email,String pass) {
        this.fname = "";
        this.lname = "";
        this.email = email;
        this.uname = "";
        this.pass = pass;
        this.project_name="";
        this.mobile = "";
    }

    public Contact(String project_id,String email,String project_name,String project_status,String uname) {
        this.email = email;
        this.project_name=project_name;
        this.fname = "";
        this.lname = "";
        this.project_status=project_status;
        this.uname = uname;
        this.mobile = "";
        this.project_id = project_id;
    }

    public Contact(String email) {

            this.email = email;
            this.project_name = "";
            this.fname = "";
            this.lname = "";
            this.project_status = "";
            this.uname = "";
            this.mobile = "";
            this.project_id = "";

    }
        public Contact(String[] myData) {
                this.myData = myData;
                this.email = "";
                 this.project_name ="";
                 this.fname = "";
                this.lname = "";
                 this.project_status = "";
                this.uname = "";
                 this.mobile = "";
             this.project_id = "";
        }

}
