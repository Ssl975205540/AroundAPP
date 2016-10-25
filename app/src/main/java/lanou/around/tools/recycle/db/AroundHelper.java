package lanou.around.tools.recycle.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dllo on 16/10/25.
 */

public class AroundHelper extends SQLiteOpenHelper {


    public AroundHelper(Context context) {
        super(context,"around.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table homegettopbanner (_id integer primary key autoincrement," + "url text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
