package com.pl.lab4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

public class DeleteDialog extends DialogFragment {
    private OnDeleteDialogInteractionListener mListener; //tried to workaround this error,
    // but didn't help (same declaration as in TaskFragment.java)

    static DeleteDialog newInstance(){
        return new DeleteDialog();
    }

    private void mListenerSetter(Context context){
        mListener = (OnDeleteDialogInteractionListener) context;
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        mListenerSetter(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.delete_question));
        builder.setPositiveButton(getString(R.string.dialog_confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.OnDialogPositiveClick(DeleteDialog.this);
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.OnDialogNegativeClick(DeleteDialog.this);
            }
        });
        return builder.create();
    }

    public interface OnDeleteDialogInteractionListener{
        void OnDialogPositiveClick(DialogFragment dialog);
        void OnDialogNegativeClick(DialogFragment dialog);
    }
}
