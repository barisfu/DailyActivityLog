package android.dailyactivitylog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Mack on 09-Oct-17.
 */

public class UserFragment extends Fragment {
    private User mUser;
    private TextView mDisplayUserName;
    private TextView mDisplayUserId;
    private TextView mDisplayUserGender;
    private EditText mEnterUserName;
    private EditText mEnterId;
    private Spinner mSelectUserGender;
    private EditText mUserComment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = new User();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_usercreation, container, false);
        final ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource
                (this.getContext(),R.array.gender_array, android.R.layout.
                        simple_spinner_dropdown_item);


        mEnterUserName = (EditText)v.findViewById(R.id.edittext_username_entry);
        mEnterUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUser.setUserName(charSequence.toString());
                mDisplayUserName.setText("User name: " + mUser.getUserName());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEnterId = (EditText)v.findViewById(R.id.edittext_id_entry);
        mEnterId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUser.setUserId("ID: " + charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSelectUserGender = (Spinner)v.findViewById(R.id.spinner_select_gender);
        mSelectUserGender.setAdapter(genderSpinnerAdapter);
        mSelectUserGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0) {
                    mUser.setUserGender("Gender: Male");
                } else if(i == 1) {
                    mUser.setUserGender("Gender: Female");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Intentionally left blank
            }
        });

        mDisplayUserName = (TextView)v.findViewById(R.id.textview_username);
        mDisplayUserName.setText(mUser.getUserName());

        mDisplayUserId = (TextView)v.findViewById(R.id.textview_id);
        mDisplayUserId.setText(mUser.getUserId());

        mDisplayUserGender = (TextView)v.findViewById(R.id.textview_gender);
        mDisplayUserGender.setText(mUser.getUserGender());

        mUserComment = (EditText) v.findViewById(R.id.edittext_user_comment);
        mUserComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUser.setUserComment(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return v;
    }
}
