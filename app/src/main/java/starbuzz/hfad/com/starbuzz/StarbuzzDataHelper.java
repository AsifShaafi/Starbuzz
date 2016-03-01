package starbuzz.hfad.com.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shaafi on 01-Mar-16, 2016.
 */
public class StarbuzzDataHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz"; // name of the database

    private static final int DB_VERSION = 1; // the version of the database

    public StarbuzzDataHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE DRINK (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                "NAME TEXT" +
                "DESCRIPTION TEXT" +
                "IMAGE_RESOURCE_ID INTEGER );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void upgradeDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {

            insertDrink(db, "Latte", "A couple of espresso shots with steamed milk",
                    R.drawable.latte);
            insertDrink(db, "Cappuccino", "Espresso, hot milk, and a steamed milk foam",
                    R.drawable.cappuccino);
            insertDrink(db, "Filter", "Highest quality beans roasted and brewed fresh",
                    R.drawable.filter);
        }

        if (oldVersion < 2) {

            ContentValues contentValues = new ContentValues();
            contentValues.put("DESCRIPTION", "Espresso, Highest quality beans roasted and brewed fresh");

            db.execSQL("AFTER TABLE DRINK ADD COLUMN FAVOURITE NUMERIC ;");
            db.update("DRINK", contentValues, "NAME = ?", new String[]{"Cappuccino"});
        }
    }

    private static void insertDrink(SQLiteDatabase db, String name, String description, int resourceID) {

        ContentValues drinkValues = new ContentValues();

        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceID);

        db.insert("DRINK", null, drinkValues);

    }
}
