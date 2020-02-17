package com.example.pdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pdd.DBHelp.DBHelperPhoto;
import com.example.pdd.Requests.AsyncPattern;
import com.github.chrisbanes.photoview.PhotoView;

import org.json.JSONObject;

public class PhotoDetail extends AppCompatActivity implements AsyncPattern.AsyncPatternCallBack{
    PhotoView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Фото штрафа");
        String a1[] = new String[6];
        photoView= (PhotoView) findViewById(R.id.photo_view);
        try {
            Bundle arguments = getIntent().getExtras();
            String id = (String)arguments.get("id");
            int numButton = (int)arguments.get("numButton");

            try {
                DBHelperPhoto dbHelper;
                dbHelper = new DBHelperPhoto(PhotoDetail.this);
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                Cursor cursor = database.rawQuery("select * from " + DBHelperPhoto.TABLE_PHOTO + " where " + DBHelperPhoto.KEY_IDOBJECT + "='" + id + "'", null);
                int lisenceNumIndex = cursor.getColumnIndex(DBHelperPhoto.KEY_BASE64);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                int k = 0;
                if (cursor.moveToFirst()) {
                    do {
                        a1[k] = cursor.getString(lisenceNumIndex);
                        k++;
                    } while (cursor.moveToNext());
                } else Log.d("mLog", "0 rows");
                cursor.close();
                Log.d("fRE", String.valueOf(k));
                switch (numButton) {
                    case (1):
                        fromBase64(a1[0],photoView);
                        break;
                    case (2):
                        fromBase64(a1[1],photoView);
                        break;
                    case (3):
                        fromBase64(a1[2],photoView);
                        break;
                    case (4):
                        fromBase64(a1[3],photoView);
                        break;
                    case (5):
                        fromBase64(a1[4],photoView);
                        break;
                    case (6):
                        fromBase64(a1[5],photoView);
                        break;
                }


            } catch (Exception e){
                Log.e("DB","DB not found");
            }
        }catch (Exception e){Log.e("DB","not detail find");}

        try {

            Bundle arguments = getIntent().getExtras();
            String id = (String)arguments.get("id");
            String dock = (String)arguments.get("dock");
            if(dock.equals("delta")){
                AsyncPattern asyncPattern = new AsyncPattern(PhotoDetail.this,"payment/order/"+id,null,false,false,false);
                asyncPattern.registrationAsyncPatternCallBack(PhotoDetail.this);
                asyncPattern.execute();
            }
}

        catch (Exception e){
            Log.e("DB","not detail dock");
        }


    }
    public void fromBase64(String image, ImageView myImag) {
        // Декодируем строку Base64 в массив байтов
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        // Декодируем массив байтов в изображение
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        // Помещаем изображение в ImageView
        myImag.setImageBitmap(decodedByte);
    }



    @Override
    public void doAsyncPatternCallBack(String answer) {
        try {
            JSONObject jsonObject = new JSONObject(answer);
                       String k = jsonObject.getString("data");
            k = k.substring(2,k.length()-2);
            if (k.equals("")){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Документ об оплате не найден", Toast.LENGTH_SHORT);
                toast.show();

            }else
            fromBase64(k,photoView);

        }

        catch (Exception e){Toast toast = Toast.makeText(getApplicationContext(),
                "Документ об оплате не найден", Toast.LENGTH_SHORT);
            toast.show();}



    }
}
