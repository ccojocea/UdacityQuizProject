package com.example.android.ccojocea.javaquiz;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ccojo on 1/23/2018.
 */

public class ConfirmSubmitDialogFragment extends AppCompatDialogFragment {

    private Button mNegativeButton;
    private Button mPositiveButton;
    private TextView questions;
    private ConfirmDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.confirm_dialog, null);

        //on this view we can later call findViewById to find our edittext ?!?!?!

        builder.setView(view);

        mNegativeButton = view.findViewById(R.id.dialog_negative_button);
        mPositiveButton = view.findViewById(R.id.dialog_positive_button);
        questions = view.findViewById(R.id.dialog_number_of_questions_tv);
        questions.setText(getArguments().getString("msg"));

        mNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.submitResults();
                dismiss();
            }
        });

        return builder.create();
    }

    /**
     * Needed to send action from the dialog to the main activity
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ConfirmDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement ConfirmDialogListener");
        }
    }

    public interface ConfirmDialogListener{
        void submitResults();
    }

    /**
     * this is responsible for sending a message from the main activity to the dialog when instancing the dialog
     * @param msg
     * @return
     */
    public static ConfirmSubmitDialogFragment newInstance(String msg){
        ConfirmSubmitDialogFragment fragment = new ConfirmSubmitDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putString("msg", msg);
        fragment.setArguments(bundle);

        return fragment;
    }
}
