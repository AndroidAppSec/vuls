package ddns.android.vuls.activities.net;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;

import ddns.android.vuls.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NoProxyActivity extends AppCompatActivity {

    String testurl = "http://tool.chinaz.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_proxyf);
    }

    public void httpURLConnection_noproxy(View view) {

        new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.8.233",8080));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(testurl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(10000);
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    if (200 == responseCode) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(NoProxyActivity.this, "请求成功", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(NoProxyActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }

    public void okhttp3_noproxy(View view) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .proxy(Proxy.NO_PROXY)
                .build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(testurl).build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(NoProxyActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final int code = response.code();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(NoProxyActivity.this, "请求成功" + code, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
