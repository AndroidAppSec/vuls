package ddns.android.vuls.activities.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import ddns.android.vuls.R;

public class SpActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;

    private SharedPreferences spDangerous;
    private SharedPreferences spSafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_sp);

        etUsername = findViewById(R.id.et_sp_username);
        etPassword = findViewById(R.id.et_sp_password);

    }

    public void save(View v) {

        spDangerous = getSharedPreferences("account_dangerous",
                Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
        spSafe = getSharedPreferences("account_safe", Context.MODE_PRIVATE);

        SharedPreferences.Editor editDangerous = spDangerous.edit();
        SharedPreferences.Editor editSafe = spSafe.edit();

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        editDangerous.putString("username", username).commit();
        editDangerous.putString("password", password).commit();
        editSafe.putString("username", username).commit();
        editSafe.putString("password", password).commit();

        Toast.makeText(this, "保存完成!", Toast.LENGTH_LONG).show();
    }

}
