package com.homeliv.piyush.homeliv_admin;

/**
 * Created by piyush on 29/1/16.
 */
public class Place {

    public String mNameofPlace;
    public int mainCode;
    public String mNameofImage;
    public String status,particular,Unit;
    public int  quantity,total,rate;
    public String mPopup,drawingTitle,drawingDescription,progressTitle,progressDescription,date;
    public String drawingImageview,progressimageview;

    public Place (String mNameofPlace, int startmaincode,String startNameOfImage,String startStatus,String startPopUp) {
        this.mNameofPlace = mNameofPlace;
        this.mainCode = startmaincode;
        this.mNameofImage = startNameOfImage;
        this.status = startStatus;
        this.mPopup = startPopUp;
    }

    //for boq.xml
    public Place(String particular,int quantity,String unit,int rate,int total) {
        this.particular = particular;
        this.quantity = quantity;
        this.Unit = unit;
        this.rate = rate;
        this.total = total;
    }
    //for drawing.xml
    public Place(String drawingImageview,String drawingTitle, String drawingDescription) {
        this.drawingImageview = drawingImageview;
        this.drawingTitle = drawingTitle;
        this.drawingDescription = drawingDescription;
    }

    // For progress.xml
    public Place(String myprogressimageview,String progressTitle,String progressDescription,String date) {
        this.progressimageview = myprogressimageview;
        this.progressTitle = progressTitle;
        this.progressDescription = progressDescription;
        this.date = date;
    }

}

