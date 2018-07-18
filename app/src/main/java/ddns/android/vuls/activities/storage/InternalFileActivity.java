package ddns.android.vuls.activities.storage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

import ddns.android.vuls.R;

public class InternalFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_file);
    }

    public void save(View v) throws IOException {

        String username = ((EditText)findViewById(R.id.if_username)).getText().toString();
        String password = ((EditText)findViewById(R.id.if_password)).getText().toString();

        FileOutputStream fosDangerous = openFileOutput("account_dangerous.txt",
                Context.MODE_WORLD_WRITEABLE | Context.MODE_WORLD_READABLE);
        fosDangerous.write((username + " : " + password + "\n").getBytes("utf-8"));
        fosDangerous.close();

        FileOutputStream fosSafe = openFileOutput("account_safe.txt", Context.MODE_PRIVATE);
        fosSafe.write((username + " : " + password + "\n").getBytes("utf-8"));
        fosSafe.close();

        Toast.makeText(this, "保存完成", Toast.LENGTH_LONG).show();
    }
}
