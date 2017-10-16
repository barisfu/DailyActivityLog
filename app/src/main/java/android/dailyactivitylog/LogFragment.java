package android.dailyactivitylog;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Mack on 25-Sep-17.
 */

public class LogFragment extends Fragment {
    private Log mLog;
    private EditText mTitleField;
    private EditText mComment;
    private Button mDateButton;
    private Button mLocationButton;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private File mPhotoFile;
    private Spinner mCategorySpinner;
    private TextView mLocationText;
    private EditText mDuration;
    private static final String ARG_LOG_ID = "log_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PHOTO = 2;



    public static LogFragment newInstance(UUID logId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_LOG_ID, logId);

        LogFragment fragment = new LogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Loads the image into the ImageView
     */
    private void updatePhotoView() {
        if(mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID logId = (UUID)getArguments().getSerializable(ARG_LOG_ID);
        mLog = LogStore.get(getActivity()).getLog(logId);
        mPhotoFile = LogStore.get(getActivity()).getPhotoFile(mLog);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, container, false);
        PackageManager packageManager = getActivity().getPackageManager();

        mLocationButton = (Button)v.findViewById(R.id.button_location);
        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleMapFragment googleMapFragment = new GoogleMapFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("mLog", mLog);
                googleMapFragment.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, googleMapFragment)
                        .addToBackStack("LogFragment")
                        .commit();
            }
        });

        mDuration = (EditText)v.findViewById(R.id.edittext_duration);
        mDuration.setText(mLog.getDuration());
        mDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mLog.setDuration(editable.toString());
            }
        });


        mLocationText = (TextView)v.findViewById(R.id.textview_display_location);
        mLocationText.setText(mLog.getAddress());

        mCategorySpinner = (Spinner)v.findViewById(R.id.categories_spinner);
        final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.categories_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mCategorySpinner.setAdapter(spinnerAdapter);
        mCategorySpinner.setSelection(mLog.getCategoryPosition());
        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0) {
                    mLog.setCategoryPosition(i);
                } else if (i == 1) {
                    mLog.setCategoryPosition(i);
                } else if (i == 2) {
                    mLog.setCategoryPosition(i);
                } else if (i == 3) {
                    mLog.setCategoryPosition(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Intentionally left blank
            }
        });


        mDateButton = (Button)v.findViewById(R.id.date_button);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mLog.getDate());
                dialog.setTargetFragment(LogFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
                Toast.makeText(getActivity(), mLog.getCategoryPosition(), Toast.LENGTH_SHORT).show();
            }
        });

        mTitleField = (EditText)v.findViewById(R.id.log_title);
        mTitleField.setText(mLog.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLog.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This space intentionally left blank
            }
        });

        mComment = (EditText)v.findViewById(R.id.comment_edittext);
        mComment.setText(mLog.getCommentSection());
        mComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLog.setCommentSection(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPhotoButton = (ImageButton) v.findViewById(R.id.log_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);
        if (canTakePhoto) {
            Uri uri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider", mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        mPhotoView = (ImageView) v.findViewById(R.id.log_photo);
        updatePhotoView();

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if(requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mLog.setDate(date);
            mLog.updateFormattedDate();
            updateDate();
        } else if (requestCode == REQUEST_PHOTO) {
            updatePhotoView();
        }
    }

    private void updateDate() {
        mDateButton.setText(mLog.getFormattedDate());
    }

    @Override
    public void onPause() {
        super.onPause();
        LogStore.get(getActivity())
                .updateLog(mLog);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_log_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_delete_log:
                UUID logId = mLog.getId();
                LogStore.get(getActivity()).deleteLog(logId);

                Toast.makeText(getActivity(), R.string.toast_deleted_confirmation, Toast.LENGTH_SHORT)
                        .show();
                getActivity().finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
