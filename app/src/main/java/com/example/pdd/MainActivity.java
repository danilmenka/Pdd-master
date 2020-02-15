package com.example.pdd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.pdd.DBHelp.DBHelperCars;
import com.example.pdd.DBHelp.DBHelperDrivers;
import com.example.pdd.DBHelp.DBHelperFines;
import com.example.pdd.DBHelp.DBHelperUnpaidFines;
import com.example.pdd.Requests.MyAsyncTask;
import com.example.pdd.ui.Autos.AutosFragment;
import com.example.pdd.ui.Osago.OsagoFrag;
import com.example.pdd.ui.cabinet.ShareCabinet;
import com.example.pdd.ui.driver.driverFragment;
import com.example.pdd.ui.home.HomeFragment;
import com.example.pdd.ui.home2.Home2Fragment;
import com.example.pdd.ui.push.PushFragment;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements MyAsyncTask.MyAsyncCallBack,NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private static final String APP_PREFERENCES = "PddSettings";
    private SharedPreferences mSettings;
    AutosFragment autosFragment;
    ShareCabinet shareCabinet;
    driverFragment driverFragment2;
    HomeFragment homeFragment;
    Home2Fragment homeFragment2;
    OsagoFrag osagoFrag;
    PushFragment pushFragment;
    FragmentTransaction fragmentTransaction;
    Boolean dbAvailable;

  /*  public interface MainActyCallBack{
        void callingBack(){};
    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(R.string.menu_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbAvailable = false;
        autosFragment= new AutosFragment();
        shareCabinet=new ShareCabinet();
        driverFragment2=new driverFragment();
        homeFragment=new HomeFragment();
        homeFragment2=new Home2Fragment();
        osagoFrag=new OsagoFrag();
        pushFragment=new PushFragment();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //сюда условия какой фрагмент будет открыт по умолчанию первым
        //if( такое то такое то){
        //selectMenuItem(R.id.nav_home);
        // }else if  selectMenuItem2(R.id.nav_home);
        dbAvailable = false;

        try {
            Bundle arguments = getIntent().getExtras();
            String txtIntent = arguments.get("nameClass").toString();
            if (txtIntent.equals("first"))dbAvailable=true;
        }catch (Exception e){}



        try {
            DBHelperCars dbHelper;
            dbHelper = new DBHelperCars(MainActivity.this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            Cursor cursor = database.query(DBHelperCars.TABLE_CARS, null, null, null, null, null, null);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            long numRows = DatabaseUtils.queryNumEntries(db, DBHelperCars.TABLE_CARS);
            if ((int)numRows>0){
                dbAvailable = true;
            }
        } catch (Exception e){
            Log.e("DB", "DB Cars is not found");
        }
        try {
            if (dbAvailable==false){
            DBHelperDrivers dbHelper;
            dbHelper = new DBHelperDrivers(MainActivity.this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            Cursor cursor = database.query(DBHelperDrivers.TABLE_DRIVERS, null, null, null, null, null, null);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            long numRows = DatabaseUtils.queryNumEntries(db, DBHelperDrivers.TABLE_DRIVERS);
            if ((int)numRows>0){
                dbAvailable = true;
            }}
        } catch (Exception e){
            Log.e("DB", "DB Drivers is not found");
        }





        if (dbAvailable){
            selectMenuItem2(R.id.nav_home);}
        else
            selectMenuItem(R.id.nav_home);

        String txtTwo="";
        try {
            Bundle arguments = getIntent().getExtras();
        txtTwo = arguments.get("TR").toString();
        }catch (Exception e){}

        if (txtTwo.equals("")){
        ////////////////////////////////////////////////////////////////////////////////////////////////
        MyAsyncTask myAsyncTask = new MyAsyncTask(MainActivity.this);
        myAsyncTask.registrationMyAsyncCallBack(this);
        myAsyncTask.execute();}
    }



    private void selectMenuItem(int index) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (index == R.id.nav_home) {
            fragmentTransaction.replace(R.id.nav_host_fragment, homeFragment);
        }
        fragmentTransaction.commit();
    }
    private void selectMenuItem2(int index) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (index == R.id.nav_home) {
            fragmentTransaction.replace(R.id.nav_host_fragment, homeFragment2);
        }
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        final int id = menuItem.getItemId();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        menuItem.setChecked(true);
        assert getSupportActionBar() != null;
        if (id == R.id.nav_home) {

            if (dbAvailable)
                fragmentTransaction.replace(R.id.nav_host_fragment, homeFragment2);
            else
                fragmentTransaction.replace(R.id.nav_host_fragment, homeFragment);


            getSupportActionBar().setTitle(R.string.menu_home);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }  else if (id == R.id.nav_gallery) {
            fragmentTransaction.replace(R.id.nav_host_fragment, osagoFrag);
            getSupportActionBar().setTitle(R.string.menu_gallery);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else if (id == R.id.nav_slideshow) {
            fragmentTransaction.replace(R.id.nav_host_fragment, autosFragment);
            getSupportActionBar().setTitle(R.string.menu_slideshow);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else if (id == R.id.nav_tools) {
            fragmentTransaction.replace(R.id.nav_host_fragment, driverFragment2);
            getSupportActionBar().setTitle(R.string.menu_tools);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else if (id == R.id.nav_share) {
            fragmentTransaction.replace(R.id.nav_host_fragment, shareCabinet);
            getSupportActionBar().setTitle(R.string.menu_share);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else if (id == R.id.nav_send) {
            fragmentTransaction.replace(R.id.nav_host_fragment, pushFragment);
            getSupportActionBar().setTitle(R.string.menu_send);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void doMyAsyncCallBack(String answer) {
        try {


            Intent intent = getIntent();
            intent.putExtra("TR","ones");
            finish();
            startActivity(intent);
        }catch (Exception e){}


        /*Log.e("TESTING",answer);

        DBHelperUnpaidFines dbHelper;
        dbHelper = new DBHelperUnpaidFines(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(DBHelperUnpaidFines.TABLE_UNPAID_FINES, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_TOTALSUMA);
            int nameIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_POSTNUM);
            int emailIndex = cursor.getColumnIndex(DBHelperUnpaidFines.KEY_PAID);
            do {
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", email = " + cursor.getString(emailIndex));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");

        cursor.close();*/






       /* JSONObject json = null;
        try {
            json = new JSONObject(answer);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString("Token", json.getString("uid"));
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("TESTING", String.valueOf(json.length()));
*/


    }
}
