package android.dailyactivitylog;

import android.dailyactivitylog.database.DbHelper;

import java.util.UUID;

/**
 * Created by Mack on 09-Oct-17.
 */

public class User {
    private String mUserName = "Username: Mack ";
    private String mUserId = "ID: 1B23TH";
    private String mUserGender = "Gender: Male";
    private String mUserEmail = "Email: sampleEmail@sampleEmail.com";
    private String mUserComment = "Comment";
    private DbHelper dbHelper;

    public User(String userId) {
        mUserId = userId;
    }
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

}
