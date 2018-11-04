package ddns.android.vuls.activities.Broadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ddns.android.vuls.R;

public class LoginBroadcastActivity extends AppCompatActivity {

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_broadcast);
    }

    public void login(View view){
        username = ((EditText) findViewById(R.id.et_br_username)).getText().toString();
        password = ((EditText) findViewById(R.id.et_br_password)).getText().toString();
        Intent intent = new Intent();
        intent.setAction("ddns.action.Token");
        if ("admin".equalsIgnoreCase(username) && "123456".equalsIgnoreCase(password)){
            intent.putExtra("success", true);
            intent.putExtra("token", "f39uewuf09wu3u9jfi3");
            sendOrderedBroadcast(intent, null);
            Toast.makeText(this, "恭喜登录成功！", Toast.LENGTH_LONG).show();
        }else {
            intent.putExtra("success", false);
            sendOrderedBroadcast(intent, null);
            Toast.makeText(this, "账号或密码错误！", Toast.LENGTH_LONG).show();
        }
    }
}
