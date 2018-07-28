package ddns.android.vuls.activities.storage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ddns.android.vuls.R;

public class LogcatActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logcat);

        etUsername = findViewById(R.id.et_log_username);
        etPassword = findViewById(R.id.et_log_password);

    }

    public void log(View v) {

//      去除log  https://blog.csdn.net/a2241076850/article/details/78391536
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        String message = "username: " + username + "  password: " + password;

        Log.v("DDNS", message);
        Log.d("DDNS", message);
        Log.i("DDNS", message);
        Log.w("DDNS", message);
        Log.e("DDNS", message);
        Log.println(Log.ASSERT, "DDNS", message);

        System.out.println("DDNS " + message);
        System.err.println("DDNS " + message);


        Toast.makeText(this, "保存完成!", Toast.LENGTH_LONG).show();
    }

}
