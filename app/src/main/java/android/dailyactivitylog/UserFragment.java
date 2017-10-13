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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by Mack on 09-Oct-17.
 */

public class UserFragment extends Fragment {
    private User mUser;
    private UserStore mUserStore;
    private TextView mDisplayUserName;
    private TextView mDisplayUserId;
    private TextView mDisplayUserGender;
    private TextView mDisplayUserEmail;
    private EditText mEnterUserName;
    private EditText mEnterId;
    private EditText mEnterUserEmail;
    private EditText mUserComment;
    private Spinner mSelectUserGender;
    private Button mSaveDetailsButton;
    private static final String ARG_USER_ID = "user_id";

    public static UserFragment newInstance(UUID userId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, userId);

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mDisplayUserId.setText(mUser.getUserId());

        mDisplayUserName = (TextView)v.findViewById(R.id.textview_username);
        mDisplayUserName.setText(mUser.getUserName());

        mDisplayUserGender = (TextView)v.findViewById(R.id.textview_gender);
        mDisplayUserGender.setText(mUser.getUserGender());

        mDisplayUserEmail = (TextView)v.findViewById(R.id.textview_display_email);
        mDisplayUserEmail.setText(mUser.getUserEmail());

        final ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource
                (this.getContext(),R.array.gender_array, android.R.layout.
                        simple_spinner_dropdown_item);


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

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mUser.setUserName(editable.toString());
                mDisplayUserName.setText("User name: " + mUser.getUserName());
            }
        });

        mEnterId = (EditText)v.findViewById(R.id.edittext_id_entry);
        mEnterId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUser.setUserId(charSequence.toString());
                mDisplayUserId.setText("ID: " + mUser.getUserId());
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

        mSaveDetailsButton = (Button)v.findViewById(R.id.button_save_user);
        mSaveDetailsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "User Details Saved", Toast.LENGTH_SHORT).show();
            }
        });


        if(mUser.getUserName() == null) {
            mDisplayUserName.setText(mUser.getUserName());
        } else {
            mDisplayUserName.setText(mUser.getUserName());
        }

        if(mUser.getUserId() == null) {
            mDisplayUserId.setText(mUser.getUserId());
        }

        if(mUser.getUserGender() == null) {
            mDisplayUserGender.setText(mUser.getUserGender());
        }

        if(mUser.getUserEmail() == null) {
            mDisplayUserEmail.setText(mUser.getUserEmail());
        }

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

    @Override
    public void onPause() {
        super.onPause();
        if(UserStore.get(getActivity()).getUser("123") != null) {
            UserStore.get(getActivity()).updateUser(mUser);
        } else {
            UserStore.get(getActivity()).addUser(mUser);
        }
    }
}
