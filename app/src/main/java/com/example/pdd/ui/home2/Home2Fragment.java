package com.example.pdd.ui.home2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pdd.PaidFines_Activity;
import com.example.pdd.R;
import com.example.pdd.SearchOfNumberPost_Activity;
import com.example.pdd.Unpaid_fines_Activity;

public class Home2Fragment extends Fragment {
    private HomeViewModel2 homeViewModel2;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel2 = ViewModelProviders.of(this).get(HomeViewModel2.class);
        View root = inflater.inflate(R.layout.fragment_home2, container, false);

        setHasOptionsMenu (true);

        return root;
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menuhome2, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.searchNum:
                Intent intent421=new Intent(getActivity(), SearchOfNumberPost_Activity.class);
                startActivity(intent421);
                break;
            case R.id.payww:
                Intent intent4212=new Intent(getActivity(), PaidFines_Activity.class);
                startActivity(intent4212);
                break;
            case R.id.notpayww:
                Intent intent4122=new Intent(getActivity(), Unpaid_fines_Activity.class);
                startActivity(intent4122);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}