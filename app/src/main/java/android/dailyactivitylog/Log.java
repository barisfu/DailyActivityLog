package android.dailyactivitylog;

import android.text.format.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Mack Costin on 25-Sep-17.
 *
 * YOU ARE UP TO PAGE 292
 */

public class Log {
    private UUID mId;
    private String mTitle;
    private String mCommentSection;
    private Date mDate;
    private String mFormattedDate;
    private String mCategory;
    private boolean mIsDataSaved;

    public Log() {
        //Generate unique identifier
        this(UUID.randomUUID());
    }

    public Log(UUID id) {
        mId = id;
        mDate = new Date();
        mFormattedDate = DateFormat.format("dd-MM-yyyy", mDate).toString();
    }

    public void setCategory(String category){
        mCategory = category;
    }

    public String getCategory(){
        return mCategory;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setCommentSection(String comment) {
        mCommentSection = comment;
    }

    public String getCommentSection() {
        return mCommentSection;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Date getDate() {
        return mDate;
    }

    public String getFormattedDate() {
        return mFormattedDate;
    }

    public void updateFormattedDate(){
        mFormattedDate = DateFormat.format("dd-MM-yyyy", mDate).toString();
    }

    public void setDataSaved(boolean dataSaved) {
        mIsDataSaved = dataSaved;
    }

    public boolean getDataSaved(){
        return mIsDataSaved;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
