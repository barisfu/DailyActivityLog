package android.dailyactivitylog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Mack on 06-Oct-17.
 */

public class CategoryPickerFragment extends DialogFragment {
    final CharSequence[] items = {"Work", "Leisure", "Study", "Sport"};
    String selection;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick an activity type").setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch(i){
                    case 0:
                        selection = (String) items[i];
                        break;
                    case 1:
                        selection = (String) items[i];
                        break;
                    case 2:
                        selection = (String) items[i];
                        break;
                    case 3:
                        selection = (String) items[i];
                        break;
                }
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
              //  Toast.makeText(getActivity(), "", duration)
            }
        });

        return super.onCreateDialog(savedInstanceState);
    }
}
