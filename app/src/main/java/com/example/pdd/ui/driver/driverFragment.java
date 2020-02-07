package com.example.pdd.ui.driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pdd.Edit_Add_Driver_Activity;
import com.example.pdd.R;

public class driverFragment extends Fragment {

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_driver, container, false);

        Button button=root.findViewById(R.id.addDriver);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent41=new Intent(getActivity(), Edit_Add_Driver_Activity.class);
                startActivity(intent41);
            }
        });
        Button button2=root.findViewById(R.id.buttonEditDriver);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent412=new Intent(getActivity(), Edit_Add_Driver_Activity.class);
                startActivity(intent412);
            }
        });
        return root;
    }
}