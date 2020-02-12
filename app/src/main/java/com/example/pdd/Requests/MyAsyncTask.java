package com.example.pdd.Requests;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.example.pdd.DBHelp.DBHelperCars;
import com.example.pdd.DBHelp.DBHelperDrivers;
import com.example.pdd.DBHelp.DBHelperFines;
import com.example.pdd.DBHelp.DBHelperUnpaidFines;
import com.example.pdd.MyHttpClient;
import com.example.pdd.Objects.Car;
import com.example.pdd.Objects.Driver;
import com.example.pdd.Objects.Fine;
import com.example.pdd.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MyAsyncTask extends AsyncTask<String,String,String> {
    private Context context;
    private String answerHTTP;
    String tokenString="";
    private SharedPreferences mSettings;
    private ProgressDialog dialog;
    private static final String APP_PREFERENCES = "PddSettings";
    public interface MyAsyncCallBack{
        void doMyAsyncCallBack(String answer);
    }
    public MyAsyncCallBack myAsyncCallBack;
    public void registrationMyAsyncCallBack(MyAsyncCallBack myAsyncCallBack){
        this.myAsyncCallBack = myAsyncCallBack;
    }
    //My astyc m
    public MyAsyncTask(Context context){
        this.context = context;
    }

    protected void onPreExecute() {
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        //Всплывающее окно при работе потока
      /*  dialog = new ProgressDialog(context);
        dialog.setMessage("Загрузка...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.show();*/
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        //Запрос на токен
        if (mSettings.contains("Token")) {
            tokenString = "45bc1ca635f090393ffc0236c6e6666ab0c876b1";
          /*  tokenString = (mSettings.getString("Token",
                    ""));*/

            if (tokenString.equals("")) tokenString = getToken(); }
            else {tokenString = getToken();}

            //Запрос на авто
        answerHTTP = getStringGET("cars", null); //Запрос с параметрами
        try {
            DBHelperCars dbHelper;
            dbHelper = new DBHelperCars(context);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            database.delete(DBHelperCars.TABLE_CARS, null, null);
        } catch (Exception e){
            Log.e("Error DBCars: ","Db is not insert");
        }
        try {

            JSONArray jsonArray = new JSONArray(answerHTTP);
            Car cars[]= new Car[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getString(i).toString() != "false") {
                    cars[i]= new Car(jsonArray.getJSONObject(i));
                    cars[i].insertDbCar(context);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Запрос на водителей
        answerHTTP = getStringGET("drivers", null); //Запрос с параметрами
        try {
            DBHelperDrivers dbHelper;
            dbHelper = new DBHelperDrivers(context);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            database.delete(DBHelperDrivers.TABLE_DRIVERS, null, null);
        } catch (Exception e){
            Log.e("Error DBDrivers: ","Db is not insert");
        }
        try {
            JSONObject json1 = new JSONObject(answerHTTP);
            JSONArray jsonArray = new JSONArray(json1.getString("data"));
            Driver drivers[]= new Driver[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getString(i).toString() != "false") {
                    drivers[i]= new Driver(jsonArray.getJSONObject(i));
                    drivers[i].insertDbDriver(context);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getUnpaidFine("fine/unpaid");
        getFine("fine/paid");
        return null;
    }



    public String getStringPOST(String request, List name) {
            String answer = "ErrorPOST void " + request;
            try {
                HttpClient httpclient = new MyHttpClient(context);
                HttpPost htopost = new HttpPost(context.getString (R.string.URL)+ request);
                if(request.hashCode()!="user".hashCode())
                htopost.setHeader(new BasicHeader("X-AUTH-TOKEN",tokenString));
                HttpResponse response;
                htopost.setEntity(new UrlEncodedFormEntity(name, "UTF-8"));
                response = httpclient.execute(htopost);
                HttpEntity entity = response.getEntity();
                Log.e("HTTP ", String.valueOf(entity));
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    String result = convertStreamToString(instream);
                    Log.e("ZZZ",result);
                    answer = result;
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return answer;
    }
    public String getStringGET(String request, List name) {
        String answer = "ErrorPOST void " + request;

        try {
            HttpClient httpclient = new MyHttpClient(context);
            HttpGet htoget = new HttpGet(context.getString (R.string.URL)+ request);
            htoget.setHeader(new BasicHeader("X-AUTH-TOKEN",tokenString));
            HttpResponse response;
            response = httpclient.execute(htoget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);
                answer = result;
                instream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }




    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
    @Override

    protected void onPostExecute(String result) {
       // dialog.dismiss();
        myAsyncCallBack.doMyAsyncCallBack(answerHTTP);
        super.onPostExecute(result);
    }
    public String getToken(){
        String TOKEN = "";
        List nameValuePairs;
        nameValuePairs= new ArrayList(4);
        nameValuePairs.add(new BasicNameValuePair("model", Build.MODEL));
        nameValuePairs.add(new BasicNameValuePair("platform", "android"));
        nameValuePairs.add(new BasicNameValuePair("version", String.valueOf(Build.VERSION.SDK_INT)));
        nameValuePairs.add(new BasicNameValuePair("uuid", Build.ID));
        answerHTTP = getStringPOST("user",nameValuePairs);
        try {
            JSONObject json;
            json = new JSONObject(answerHTTP);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString("Token", json.getString("uid"));
            editor.apply();
            TOKEN = json.getString("uid");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return TOKEN;
    }
    public void getFine(String url){
        List nameValuePairs;
        int pages=0;
        String items="";
        nameValuePairs= new ArrayList(1);
        nameValuePairs.add(new BasicNameValuePair("page", "1"));
        answerHTTP = getStringPOST(url,nameValuePairs);
        //Узнаем кол-во страниц
        try {
        JSONObject j1 = new JSONObject(answerHTTP);
        pages = j1.getInt("totalPage");
        items = j1.getString("items");
        }
        catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                DBHelperFines dbHelper;
                dbHelper = new DBHelperFines(context);
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                database.delete(DBHelperFines.TABLE_FINES, null, null);
            } catch (Exception e) {
                Log.e("Error DBCars: ", "Db is not insert");
            }
            try {
                JSONArray jsonArray = new JSONArray(items);
                Fine fines[] = new Fine[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                        fines[i] = new Fine(jsonArray.getJSONObject(i));
                        fines[i].insertDbFine(context);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
                if (pages>1)
                for (int k = 1;k<pages;k++){
                    nameValuePairs= new ArrayList(1);
                    nameValuePairs.add(new BasicNameValuePair("page",Integer.toString(k+1)));
                    answerHTTP = getStringPOST(url,nameValuePairs);
                    try {
                        JSONObject j1 = new JSONObject(answerHTTP);
                        items = j1.getString("items");
                        try {
                            JSONArray jsonArray = new JSONArray(items);
                            Fine fines[] = new Fine[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                fines[i] = new Fine(jsonArray.getJSONObject(i));
                                fines[i].insertDbFine(context);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } catch (Exception e) {
                        Log.e("Error DBCars: ", "Db is not insert");
                    }

                }

    }
    public void getUnpaidFine(String url){
        List nameValuePairs;
        int pages=0;
        String items="";
        nameValuePairs= new ArrayList(1);
        nameValuePairs.add(new BasicNameValuePair("page", "1"));
        answerHTTP = getStringPOST(url,nameValuePairs);
        //Узнаем кол-во страниц
        try {
            JSONObject j1 = new JSONObject(answerHTTP);
            pages = j1.getInt("totalPage");
            items = j1.getString("items");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            DBHelperUnpaidFines dbHelper;
            dbHelper = new DBHelperUnpaidFines(context);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            database.delete(DBHelperUnpaidFines.TABLE_UNPAID_FINES, null, null);
        } catch (Exception e) {
            Log.e("Error DBCars: ", "Db is not insert");
        }
        try {
            JSONArray jsonArray = new JSONArray(items);
            Fine fines[] = new Fine[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                fines[i] = new Fine(jsonArray.getJSONObject(i));
                fines[i].insertDbUnpaidFine(context);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (pages>1)
            for (int k = 1;k<pages;k++){
                nameValuePairs= new ArrayList(1);
                nameValuePairs.add(new BasicNameValuePair("page",Integer.toString(k+1)));
                answerHTTP = getStringPOST(url,nameValuePairs);
                try {
                    JSONObject j1 = new JSONObject(answerHTTP);
                    items = j1.getString("items");
                    try {
                        JSONArray jsonArray = new JSONArray(items);
                        Fine fines[] = new Fine[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            fines[i] = new Fine(jsonArray.getJSONObject(i));
                            fines[i].insertDbUnpaidFine(context);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    Log.e("Error DBCars: ", "Db is not insert");
                }

            }





    }
}
