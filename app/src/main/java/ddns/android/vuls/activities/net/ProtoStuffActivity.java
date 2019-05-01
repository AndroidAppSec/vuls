package ddns.android.vuls.activities.net;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import ddns.android.vuls.Beans.LoginRequestOuterClass;
import ddns.android.vuls.Beans.MessageOuterClass;
import ddns.android.vuls.Beans.User;
import ddns.android.vuls.R;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ProtoStuffActivity extends AppCompatActivity {

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proto_stuff);
        username = ((EditText)findViewById(R.id.et_ps_username)).getText().toString();
        password = ((EditText)findViewById(R.id.et_ps_password)).getText().toString();
    }

    public void login(View view){
        String url = "http://10.102.166.186/pslogin";

        RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);
        User user = new User();
        user.setId(3);
        user.setName("tom");
        user.setAge(8);
        user.setPassword("123");

        byte[] bytes = ProtostuffIOUtil.toByteArray(user, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

        Log.e("pb", bytesToHexString(bytes));


        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/pb"),
                bytes);

        Request request = new Request.Builder().url(url).post(requestBody).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ProtoStuffActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                final String string = body.string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (1 == 1){
                            Toast.makeText(ProtoStuffActivity.this, string, Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(ProtoStuffActivity.this, string, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
