package com.example.pdd.ui.cabinet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pdd.MainActivity;
import com.example.pdd.NewAccountEmail;
import com.example.pdd.R;
import com.example.pdd.Requests.AsyncPattern;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShareCabinet extends Fragment implements AsyncPattern.AsyncPatternCallBack {
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
        button2=root.findViewById(R.id.butOpenAcc);
        button3=root.findViewById(R.id.butDeleteAcc);
        textViewEmaill = root.findViewById(R.id.textViewEmail);
        textViewEmaill2 = root.findViewById(R.id.textViewEmail2);

       /* SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("Token", json.getString("uid"));*/


        return root;
    }
    @Override
    public void onResume() {

        final String email;
        SharedPreferences mSettings;
        mSettings = getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains("email")) {
            email = (mSettings.getString("email",
                    ""));
            button1.setVisibility(View.GONE);
            button2.setVisibility(View.GONE);
            textViewEmaill2.setText(email);
            textViewEmaill.setVisibility(View.GONE);
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Удаление водителя")
                            .setMessage("Вы действительно хотите удалить аккаунт "+email+"?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                    AsyncPattern asyncPattern= new AsyncPattern(getContext(),"account",null,false,false,true);
                                    asyncPattern.registrationAsyncPatternCallBack(ShareCabinet.this);
                                    asyncPattern.execute();
                                }
                            }).create().show();
                }
            });


            if (email.equals("")) {
                button3.setVisibility(View.GONE);
                textViewEmaill2.setVisibility(View.GONE);
                textViewEmaill.setText("Учетная запись отутствует");
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), NewAccountEmail.class);
                        i.putExtra("txt","new");
                        startActivity(i);
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), NewAccountEmail.class);
                        i.putExtra("txt","open");
                        startActivity(i);

                    }
                });

            }
        } else {            Log.e("KURSK","666fr");
            button3.setVisibility(View.GONE);
            textViewEmaill2.setVisibility(View.GONE);
            textViewEmaill.setText("Учетная запись отутствует");
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), NewAccountEmail.class);
                    i.putExtra("txt","new");
                    startActivity(i);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), NewAccountEmail.class);
                    i.putExtra("txt","open");
                    startActivity(i);

                }
            });

        }

        super.onResume();
    }



    @Override
    public void doAsyncPatternCallBack(String answer) {
        try {
            JSONObject jsonObject = new JSONObject(answer);
            if (jsonObject.getString("data").equals("true")){
                SharedPreferences mSettings;
                mSettings = getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSettings.edit();
                editor.remove("email");
                editor.remove("subscribeEmail");
                editor.remove("subscribePush");
                editor.apply();
                Intent i = new Intent(getContext(),MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}