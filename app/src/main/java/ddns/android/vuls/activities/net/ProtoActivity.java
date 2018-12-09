package ddns.android.vuls.activities.net;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import ddns.android.vuls.Beans.LoginRequestOuterClass;
import ddns.android.vuls.Beans.MessageOuterClass;
import ddns.android.vuls.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ProtoActivity extends AppCompatActivity {

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((Button)findViewById(R.id.btn_ac_save)).setText("登录");
    }

    public void login(View view){
        username = ((EditText) findViewById(R.id.et_ac_username)).getText().toString();
        password = ((EditText) findViewById(R.id.et_ac_password)).getText().toString();
        String url = "http://192.168.8.233/pblogin";

        final LoginRequestOuterClass.LoginRequest
                loginRequest = LoginRequestOuterClass.LoginRequest
                                                            .newBuilder()
                                                            .setUsername(username)
                                                            .setPassword(password)
                                                            .build();


        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        RequestBody requestBody = RequestBody.create(
                                    MediaType.parse("application/pb"),
                                    loginRequest.toByteArray());

        Request request = new Request.Builder().url(url).post(requestBody).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ProtoActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                LoginRequestOuterClass.LoginResponse
                        loginResponse = LoginRequestOuterClass.LoginResponse.parseFrom(body.bytes());
                final int code = loginResponse.getCode();
                MessageOuterClass.Message responseMsg = loginResponse.getMsg();
                final int id = responseMsg.getId();
                final String content = responseMsg.getContent();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (id == 1){
                            Toast.makeText(ProtoActivity.this, content, Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(ProtoActivity.this, content + code, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
