package ddns.android.vuls.activities.storage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import ddns.android.vuls.R;

public class ExternalFileActivity extends AppCompatActivity {

    private EditText et_ef_username;
    private EditText et_ef_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_file);
        et_ef_username = findViewById(R.id.ef_username);
        et_ef_password = findViewById(R.id.ef_password);

    }

    public void save(View v) throws Exception {

        //1. 判断sd卡状态, 如果是挂载的状态才继续, 否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String userName = et_ef_username.getText().toString();
            String password = et_ef_password.getText().toString();
            String filesPath = getExternalFilesDir(null).getAbsolutePath();
            String filePath = filesPath + "/account.txt";
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write((userName + ":" + password + "\n").getBytes("utf-8"));
            fos.close();
            Toast.makeText(this, "保存完成", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "sd卡没有挂载", Toast.LENGTH_LONG).show();
        }
    }

    public void save2sdcard(View v) throws IOException {
        //1. 判断sd卡状态, 如果是挂载的状态才继续, 否则提示
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String userName = et_ef_username.getText().toString();
            String password = et_ef_password.getText().toString();
            String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(sdPath+"/ddns");
            if(!file.exists()) {
                file.mkdirs();
            }
            String filePath = sdPath+"/ddns/account.txt";
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write((userName + ":" + password + "\n").getBytes("utf-8"));
            fos.close();
            Toast.makeText(this, "保存完成", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "sd卡没有挂载", Toast.LENGTH_LONG).show();
        }
    }
}
