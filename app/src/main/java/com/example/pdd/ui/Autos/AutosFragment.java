package com.example.pdd.ui.Autos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pdd.Edit_Add_Auto_Activity;
import com.example.pdd.R;

public class AutosFragment extends Fragment  {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_autos, container, false);
        Button button=root.findViewById(R.id.addAuto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent41=new Intent(getActivity(), Edit_Add_Auto_Activity.class);
                startActivity(intent41);
            }
        });
        Button button1=root.findViewById(R.id.buttonEditAuto);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent411=new Intent(getActivity(), Edit_Add_Auto_Activity.class);
                startActivity(intent411);
            }
        });


        return root;
    }



}