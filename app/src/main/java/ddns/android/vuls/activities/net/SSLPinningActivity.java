package ddns.android.vuls.activities.net;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import ddns.android.vuls.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SSLPinningActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sslpinning);
    }

    public void okhttp3(View view) {
        CertificatePinner leafCertPinner = new CertificatePinner.Builder()
                .add("www.baidu.com", "sha256/M9Hz16jmJjXzPUkH8FUKG2GZLd/55sT6yCNiQgwfNtk=")
                .add("www.sohu.com", "sha256/MQ==")
                .build();

        CertificatePinner intermediateCertPinner = new CertificatePinner.Builder()
                .add("*.baidu.com", "sha256/IQBnNBEiFuhj+8x6X8XLgh01V9Ic5/V3IRQLNFFc7v4=")
                .build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .certificatePinner(leafCertPinner)
                .certificatePinner(intermediateCertPinner)
                .build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("https://www.baidu.com/").build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SSLPinningActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final int code = response.code();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SSLPinningActivity.this, "请求成功" + code, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

}
