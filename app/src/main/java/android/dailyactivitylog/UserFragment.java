package android.dailyactivitylog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mack on 09-Oct-17.
 */

public class UserFragment extends Fragment {
    private User mUser;
    private TextView mDisplayUserName;
    private TextView mDisplayUserId;
    private TextView mDisplayUserGender;
    private TextView mDisplayUserEmail;
    private EditText mEnterUserName;
    private EditText mEnterUserEmail;
    private EditText mUserComment;
    private Spinner mSelectUserGender;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(UserStore.get(getActivity()).getUser("123") == null){
            mUser = new User("123");
        } else {
            mUser = UserStore.get(getActivity()).getUser("123");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_usercreation, container, false);

        mDisplayUserId = (TextView)v.findViewById(R.id.textview_id);
        mDisplayUserId.setText("User ID: " + mUser.getUserId());

        mDisplayUserName = (TextView)v.findViewById(R.id.textview_username);
        mDisplayUserName.setText("User Name: " + mUser.getUserName());

        mDisplayUserGender = (TextView)v.findViewById(R.id.textview_gender);
        mDisplayUserGender.setText("Gender: " + mUser.getUserGender());

        mDisplayUserEmail = (TextView)v.findViewById(R.id.textview_display_email);
        mDisplayUserEmail.setText("Email: " + mUser.getUserEmail());


        mEnterUserEmail = (EditText)v.findViewById(R.id.edittext_email_entry);
        mEnterUserEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUser.setUserEmail(charSequence.toString());
                mDisplayUserEmail.setText("Email: " + mUser.getUserEmail());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

        final ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource
                (this.getContext(),R.array.gender_array, android.R.layout.
                        simple_spinner_dropdown_item);
        mSelectUserGender = (Spinner)v.findViewById(R.id.spinner_select_gender);
        mSelectUserGender.setAdapter(genderSpinnerAdapter);
        mSelectUserGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0) {
                        mUser.setUserGender("Male");

                } else if(i == 1) {
                        mUser.setUserGender("Female");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        mUserComment = (EditText) v.findViewById(R.id.edittext_user_comment);
        mUserComment.setText(mUser.getUserComment());
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_user_creation_menu, menu);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(UserStore.get(getActivity()).getUser("123") != null) {
            UserStore.get(getActivity()).updateUser(mUser);
            Toast.makeText(getActivity(), "User Details Updated", Toast.LENGTH_SHORT).show();
        } else {
            UserStore.get(getActivity()).addUser(mUser);
            Toast.makeText(getActivity(), "User Details Added", Toast.LENGTH_SHORT).show();
        }
    }
}
