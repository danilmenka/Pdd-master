package com.example.pdd.ui.cabinet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pdd.R;

public class ShareCabinet extends Fragment implements View.OnClickListener {

    private ShareViewModel shareViewModel;
    private Button button1;
    private Button button2;
    private Button button3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cabinet, container, false);

        button1=root.findViewById(R.id.butCreateAcc);
        button2=root.findViewById(R.id.butEnterAcc);
        button3=root.findViewById(R.id.butDeleteAcc);

        return root;
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.butCreateAcc:
               break;
           case R.id.butEnterAcc:
               break;
           case R.id.butDeleteAcc:
               break;
       }
    }
}