package data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.MyPreferences;


/**
 * Created by Umeed-e-Nau on 12/23/2016.
 */
public class LocalDataManager {
    Context mContext;
 public static     SQLiteDatabase database;



    public LocalDataManager(Context context) {
       // try {
            this.mContext = context;
            database = new DBHelper(context).getWritableDatabase();


      /*  } catch (Exception e) {
             Toast.makeText(context,"Error in Localdata Mangager",Toast.LENGTH_LONG).show();
        }

        */
    }


    public List<String> getLogList(String status) {

        ArrayList<String> list = new ArrayList<>();

        try {

          String  query = "select id,Q1 from ttable where Interview_status = '%s' order by id ASC";
            query = String.format(query, status);

            database.beginTransaction();
            Cursor c = database.rawQuery(query, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {


                        list.add(c.getString(1) + "/" + c.getString(0));





                    } while (c.moveToNext());
                }
            }
        } finally {
            database.setTransactionSuccessful();
            database.endTransaction();
            database.close();
        }
        return list;
    }







}





