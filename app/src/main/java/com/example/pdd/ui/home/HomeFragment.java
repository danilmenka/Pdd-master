package com.example.pdd.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pdd.MainActivity;
import com.example.pdd.PaidFines_Activity;
import com.example.pdd.R;
import com.example.pdd.Requests.AsyncPattern;
import com.example.pdd.SearchOfNumberPost_Activity;
import com.example.pdd.Unpaid_fines_Activity;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeFragment extends Fragment implements AsyncPattern.AsyncPatternCallBack{

    private HomeViewModel homeViewModel;
    String a1 ="ф1";
    String a2 ="ф2";
    String a3 ="ф3";
    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
      button=root.findViewById(R.id.butOfEditAddAut111);
        editText1=root.findViewById(R.id.editAddNameAut111);
        editText2=root.findViewById(R.id.editAddRegistrNumbe111);
        editText3=root.findViewById(R.id.editAddRegist111);
        setHasOptionsMenu (true);
        return root;
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.searchNumber:
                Intent intent421=new Intent(getActivity(), SearchOfNumberPost_Activity.class);
                startActivity(intent421);
                break;
            case R.id.pay:
                Intent intent4212=new Intent(getActivity(), PaidFines_Activity.class);
                startActivity(intent4212);
                break;
            case R.id.notpay:
                Intent intent4122=new Intent(getActivity(), Unpaid_fines_Activity.class);
                startActivity(intent4122);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean validateStsNum(final String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^\\d{2}[а-яА-Я0-9]{2}\\d{6}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
    public boolean validateRegNum(final String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN =
                "(^[а-яА-Я]+\\d{3}[а-яА-Я]{2}\\d{2,3})|(^[а-яА-Я]{1,2}\\d{3,4}\\d{2,3})|(^\\d{3,4}[а-яА-Я]{1,2}\\d{2,3})$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    @Override
    public void onResume() {



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1 = String.valueOf(editText1.getText());
                a2 = String.valueOf(editText2.getText());
                a3 = String.valueOf(editText3.getText());

                if (validateRegNum(a1)==false){
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            "Регистрационный номер автомобиля введен неверно", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (validateStsNum(a2)==false){
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            "Номер свидетельства о регистрации введен неверно", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (validateStsNum(a3)==false){
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            "Номер водительского удостоверения введен неверно", Toast.LENGTH_SHORT);
                    toast.show();
                } else{


                    List nameValuePairs = new ArrayList(1);
                    nameValuePairs.add(new BasicNameValuePair("stsnum",a1));
                    nameValuePairs.add(new BasicNameValuePair("regnum",a2));
                    nameValuePairs.add(new BasicNameValuePair("driverlicense",a3));

                    AsyncPattern asyncPattern= new AsyncPattern(getActivity(),"fine/search/bypostnum",nameValuePairs,true,false,false);
                    asyncPattern.registrationAsyncPatternCallBack(HomeFragment.this);
                    asyncPattern.execute();


                }






            }
        });




        super.onResume();
    }
    @Override
    public void doAsyncPatternCallBack(String answer) {
        Intent i = new Intent(getActivity(), MainActivity.class);
        i.putExtra("nameClass","first");
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}