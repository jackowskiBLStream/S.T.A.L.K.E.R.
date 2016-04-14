package com.blstream.stalker.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blstream.stalker.R;
import com.blstream.stalker.view.interfaces.IErrorMessageFragment;

public class ErrorMessageFragment extends Fragment implements IErrorMessageFragment {

    private String message;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.error_layout, container, false);
        TextView errorText = (TextView) view.findViewById(R.id.errorTextView);
        errorText.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        errorText.setText(message);
        return view;
    }
    @Override
    public void setErrorMessage(String message) {
        this.message = message;
    }
}
