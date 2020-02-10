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
import com.example.pdd.MyHttpClient;
import com.example.pdd.Objects.Car;
import com.example.pdd.Objects.Driver;
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

public class AsyncPattern extends AsyncTask<String,String,String> {
    private Context context;
    private String answerHTTP;
    private String tokenString="";
    private String request="";
    private Boolean postValue = true;
    private List nameValuePairs;
    private SharedPreferences mSettings;
    private ProgressDialog dialog;
    private static final String APP_PREFERENCES = "PddSettings";
    public interface AsyncPatternCallBack{
        void doAsyncPatternCallBack(String answer);
    }
    AsyncPatternCallBack asyncPatternCallBack;
    public void registrationAsyncPatternCallBack(AsyncPatternCallBack asyncPatternCallBack){
        this.asyncPatternCallBack = asyncPatternCallBack;
    }

    public AsyncPattern(Context context, String request, List nameValuePairs, Boolean postValue){
        this.context = context;
        this.request = request;
        this.nameValuePairs = nameValuePairs;
        this.postValue = postValue;
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
            tokenString = (mSettings.getString("Token",
                    ""));
            if (tokenString.equals("")) tokenString = getToken(); }
        else {tokenString = getToken();}

        if (postValue){
        answerHTTP = getStringPOST(request, nameValuePairs);} //Запрос с параметрами
        else {
        //Запрос на водителей
        answerHTTP = getStringGET(request, nameValuePairs);} //Запрос с параметрами
        return null;
    }

    public String getStringPOST(String request, List name) {
        String answer = "ErrorPOST void " + request;
        try {
            HttpClient httpclient = new MyHttpClient(context);
            HttpPost htopost = new HttpPost(context.getString(R.string.URL)+ request);
            if(request.hashCode()!="user".hashCode())
                htopost.setHeader(new BasicHeader("X-AUTH-TOKEN",tokenString));
            HttpResponse response;
            htopost.setEntity(new UrlEncodedFormEntity(name, "UTF-8"));
            response = httpclient.execute(htopost);
            HttpEntity entity = response.getEntity();
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
        asyncPatternCallBack.doAsyncPatternCallBack(answerHTTP);
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
}