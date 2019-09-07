package com.soccermat.ultramed.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soccermat.ultramed.R;

public class DialogueUtils {

    Context context;
    AlertDialog alertDialog=null;
    AlertDialogListener callBack;
    Activity current_activity;

    public DialogueUtils(Context context)
    {
        this.context = context;
        this.current_activity = (Activity) context;
        callBack = (AlertDialogListener) context;
    }


    public static void alertDialogShow(Context context, String message)
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    public void showAlertDialog(String title, String message, String positive, String negative, String neutral, final int from, boolean isCancelable)
    {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(current_activity);

        if(!TextUtils.isEmpty(title))
            alertDialogBuilder.setTitle(title);
        if(!TextUtils.isEmpty(message))
            alertDialogBuilder.setMessage(message);

        if(!TextUtils.isEmpty(positive)) {
            alertDialogBuilder.setPositiveButton(positive,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            callBack.onPositiveClick(from);
                            alertDialog.dismiss();
                        }
                    });
        }
        if(!TextUtils.isEmpty(neutral)) {
            alertDialogBuilder.setNeutralButton(neutral,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            callBack.onNeutralClick(from);
                            alertDialog.dismiss();
                        }
                    });
        }
        if(!TextUtils.isEmpty(negative))
        {
            alertDialogBuilder.setNegativeButton(negative,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            callBack.onNegativeClick(from);
                            alertDialog.dismiss();
                        }
                    });
        }

        alertDialogBuilder.setCancelable(isCancelable);


        alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        if(TextUtils.isEmpty(negative))
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(false);
    }
    public interface AlertDialogListener
    {
        public void onPositiveClick(int from);
        public void onNegativeClick(int from);
        public void onNeutralClick(int from);
    }


}
