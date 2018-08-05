package ddns.android.vuls.activities.storage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import ddns.android.vuls.R;

public class ScreenShotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_screen_shot);
    }

    public void save(View view){
        String username = ((EditText) findViewById(R.id.et_ss_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.et_ss_password)).getText().toString();
        Toast.makeText(ScreenShotActivity.this, username + " : " + password, Toast.LENGTH_SHORT).show();
    }

}
