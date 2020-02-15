package com.example.pdd.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pdd.PaidFines_Activity;
import com.example.pdd.R;
import com.example.pdd.SearchOfNumberPost_Activity;
import com.example.pdd.Unpaid_fines_Activity;

import java.util.zip.Inflater;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        EditText editText1=root.findViewById(R.id.editAddNameAuto);
        EditText editText2=root.findViewById(R.id.editAddRegistrNumber);
        EditText editText3=root.findViewById(R.id.editAddRegistr);
        Button button=root.findViewById(R.id.butOfEditAddAuto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // кнопка поиска штрафов








            }
        });
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
}