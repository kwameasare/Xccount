package genius.xccount;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static genius.xccount.FeedReaderContract.FeedEntry.Col1;
import static genius.xccount.FeedReaderContract.FeedEntry.Col10;
import static genius.xccount.FeedReaderContract.FeedEntry.Col11;
import static genius.xccount.FeedReaderContract.FeedEntry.Col12;
import static genius.xccount.FeedReaderContract.FeedEntry.Col2;
import static genius.xccount.FeedReaderContract.FeedEntry.Col3;
import static genius.xccount.FeedReaderContract.FeedEntry.Col4;
import static genius.xccount.FeedReaderContract.FeedEntry.Col5;
import static genius.xccount.FeedReaderContract.FeedEntry.Col6;
import static genius.xccount.FeedReaderContract.FeedEntry.Col7;
import static genius.xccount.FeedReaderContract.FeedEntry.Col8;
import static genius.xccount.FeedReaderContract.FeedEntry.Col9;
import static genius.xccount.FeedReaderContract.FeedEntry.Table_Name;

public class UserDB extends SQLiteOpenHelper {














    UserDB(Context context) {
        super(context, FeedReaderContract.FeedEntry.DBname, null, 1);
        SQLiteDatabase db= this.getWritableDatabase();
    }


    private static final String CREATE_TABLE_USER = "CREATE TABLE " + Table_Name + "("
            + _ID + " INTEGER PRIMARY KEY," + Col1 + " TEXT," + Col2
            + " TEXT," + Col3 + " TEXT," + Col4 + " DATETIME,"+ Col5
            +" TEXT,"+ Col6 +" TEXT,"+ Col7 +" TEXT," + Col8 +" TEXT,"+ Col9
            +" TEXT,"+ Col10 +" TEXT,"+ Col11 +" TEXT,"+ Col12 +" TEXT"+ ")";





    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USER);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        onCreate(db);

    }
}