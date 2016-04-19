package com.blstream.stalker.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blstream.stalker.R;
import com.blstream.stalker.view.interfaces.IErrorMessageView;

public class ErrorMessageView extends Fragment implements IErrorMessageView {

    private String message;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
         return inflater.inflate(R.layout.error_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView errorText = (TextView) view.findViewById(R.id.errorTextView);
        errorText.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        errorText.setText(message);
    }

    @Override
    public void setErrorMessage(String message) {
        this.message = message;
    }
}
