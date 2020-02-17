package com.example.pdd.ui.cabinet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pdd.MainActivity;
import com.example.pdd.NewAccountEmail;
import com.example.pdd.R;

public class ShareCabinet extends Fragment {
    private static final String APP_PREFERENCES = "PddSettings";
    private ShareViewModel shareViewModel;
    private Button button1;
    private Button button2;
    private Button button3;
    TextView textViewEmaill;
    TextView textViewEmaill2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cabinet, container, false);

        button1=root.findViewById(R.id.butCreateAcc);

        button3=root.findViewById(R.id.butDeleteAcc);
        textViewEmaill = root.findViewById(R.id.textViewEmail);
        textViewEmaill2 = root.findViewById(R.id.textViewEmail2);

       /* SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("Token", json.getString("uid"));*/


        return root;
    }
    @Override
    public void onResume() {

        String email;
        SharedPreferences mSettings;
        mSettings = getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains("email")) {
            email = (mSettings.getString("email",
                    ""));
            if (email.equals("")) {
                button3.setVisibility(View.GONE);
                textViewEmaill2.setVisibility(View.GONE);
                textViewEmaill.setText("Учетная запись отутствует");
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), NewAccountEmail.class);
                        startActivity(i);
                    }
                });
            }
        } else {
            button3.setVisibility(View.GONE);
            textViewEmaill2.setVisibility(View.GONE);
            textViewEmaill.setText("Учетная запись отутствует");
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), NewAccountEmail.class);
                    startActivity(i);
                }
            });

        }

        super.onResume();
    }

}