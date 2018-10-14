package ddns.android.vuls.activities.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ddns.android.vuls.R;

public class LoginActivity extends AppCompatActivity {

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void login(View view){
        username = ((EditText) findViewById(R.id.et_ac_username)).getText().toString();
        password = ((EditText) findViewById(R.id.et_ac_password)).getText().toString();
        if ("admin".equalsIgnoreCase(username) && "123456".equalsIgnoreCase(password)){
            startActivity(new Intent(this, AccountActivity.class));
        }else {
            Toast.makeText(this, "账号或密码错误！", Toast.LENGTH_LONG).show();
        }
    }
}
