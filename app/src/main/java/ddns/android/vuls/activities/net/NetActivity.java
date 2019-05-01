package ddns.android.vuls.activities.net;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import ddns.android.vuls.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        findViewById(R.id.btn_HttpsURLConnection).setOnClickListener(this);
        findViewById(R.id.btn_SSLPinning).setOnClickListener(this);
        findViewById(R.id.btn_anti).setOnClickListener(this);
        findViewById(R.id.btn_proto).setOnClickListener(this);
        findViewById(R.id.btn_zipper).setOnClickListener(this);
        findViewById(R.id.btn_protostuff).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_HttpsURLConnection:
                startActivity(new Intent(this, HttpsURLConnectionActivity.class));
                break;
            case R.id.btn_SSLPinning:
                startActivity(new Intent(this, SSLPinningActivity.class));
                break;
            case R.id.btn_anti:
                startActivity(new Intent(this, AntiActivity.class));
                break;
            case R.id.btn_proto:
                startActivity(new Intent(this, ProtoActivity.class));
                break;
            case R.id.btn_protostuff:
                startActivity(new Intent(this, ProtoStuffActivity.class));
                break;
            case R.id.btn_zipper:
                zipperDown();
                break;
            default:
                break;

        }
    }

    private void zipperDown(){
        String url = "http://www.example.com/test.zip";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).get().build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("zipperDown", "zipperDown fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.e("zipperDown", response.body().bytes().length + "");
                String dstPath = getCacheDir().toString();
                ZipEntry zipEntry = null;
                ZipInputStream zipInputStream = new ZipInputStream(response.body().byteStream());
                while ((zipEntry = zipInputStream.getNextEntry()) != null){
                    String entryName = zipEntry.getName();
                    if (zipEntry.isDirectory()){
                        entryName = entryName.substring(0, entryName.length() -1);
                        File folder = new File(dstPath + File.separator + entryName);
                        folder.mkdirs();
                    }else {
                        String fileName = dstPath + File.separator + entryName;
                        Log.e("zipperDown", fileName);
                        File file = new File(fileName);
                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];
                        int n = 0;
                        while ((n = zipInputStream.read(buffer, 0 , 1024)) != -1){
                            fileOutputStream.write(buffer, 0 , n);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                }
                zipInputStream.close();
            }
        });

    }
}
