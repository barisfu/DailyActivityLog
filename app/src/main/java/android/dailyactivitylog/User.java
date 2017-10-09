package android.dailyactivitylog;

import android.content.Context;
import android.dailyactivitylog.database.LogDbHelper;

import java.util.UUID;

/**
 * Created by Mack on 09-Oct-17.
 */

public class User {
    private String mUserName = "User name: User #1";
    private String mUserId = "ID: 1B23TH";
    private String mUserGender = "Gender: Male";
    private String mUserEmail = "Email: sampleEmail@sampleEmail.com";
    private String mUserComment;
    private LogDbHelper dbHelper;

   public void setUserEmail(String email) {
       mUserEmail = email;
   }

   public String getUserEmail(){
       return mUserEmail;
   }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public void setUserGender(String userGender) {
        mUserGender = userGender;
    }

    public void setUserComment(String userComment) {
        mUserComment = userComment;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getUserGender() {
        return mUserGender;
    }

    public String getUserComment() {
        return mUserComment;
    }

    /**Retrieves all user details from database
     *
     * @param dbHelper
     */
    public void getAllUserDetails() {
        dbHelper.getUserDetails();
    }
}
