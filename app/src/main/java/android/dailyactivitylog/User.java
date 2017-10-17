package android.dailyactivitylog;

/**
 * Created by Mack on 09-Oct-17.
 */

public class User {
    private String mUserName = "Example Username ";
    private String mUserId = "ExampleID";
    private String mUserGender = "Male";
    private String mUserEmail = "sampleEmail@sampleEmail.com";
    private String mUserComment = "Comment";

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
