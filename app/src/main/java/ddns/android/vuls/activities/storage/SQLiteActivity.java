package ddns.android.vuls.activities.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ddns.android.vuls.R;

public class SQLiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
    }

    public void save2db(View v) {
        String username = ((EditText) findViewById(R.id.et_db_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.et_db_password)).getText().toString();

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("account.db",
                Context.MODE_WORLD_WRITEABLE | Context.MODE_WORLD_READABLE, null);
        String init = "create table if not exists accounts(" +
                "_id integer primary key autoincrement, " +
                "username varchar," +
                "password varchar)";
        sqLiteDatabase.execSQL(init);
        String insert = "INSERT INTO accounts(username, password)" +
                " VALUES('" +
                    username + "','" +
                    password + "'" +
                ");";
        sqLiteDatabase.execSQL(insert);
        sqLiteDatabase.close();
        Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
    }
}
