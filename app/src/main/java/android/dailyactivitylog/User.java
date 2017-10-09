package android.dailyactivitylog;

/**
 * Created by Mack on 09-Oct-17.
 */

public class User {
    private String mUserName = "User name: User #1";
    private String mUserId = "ID: 1B23TH";
    private String mUserGender = "Gender: Male";
    private String mUserComment;

   // public User(String id){
   //     mUserId = id;
   // }

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
