package id.jmlcode.sample.ui.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import id.jmlcode.sample.R;
import id.jmlcode.sample.model.bean.Data;
import id.jmlcode.sample.model.bean.Header;
import id.jmlcode.sample.ui.dialogfragment.service.ClickView;


public class ExampleDialogFragment extends DialogFragment {
    TextView scheduleOption;
    Header header;
    ClickView clickView;

    public void setScheduleOption(TextView scheduleOption){
        this.scheduleOption = scheduleOption;
    }

    public void setClickView(ClickView clickView){
        this.clickView = clickView;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View conversationSchedulerLayout = View.inflate(getActivity(), R.layout.activity_main, null);

        builder.setView(conversationSchedulerLayout)

                .setPositiveButton("Yes", null)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        scheduleOption.setText("Oke");
                        header = Data.getData();
                        clickView.onClickView(header);
                        dialog.dismiss();
                    }
                });

        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleOption.setText("Oh No");
                header = Data.getData();
                clickView.onClickView(header);
                dialog.dismiss();
            }
        });
        return dialog;
    }
}
