package ddns.android.vuls.activities.storage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ddns.android.vuls.R;

public class UidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uid);

        try {
            Process getUid = Runtime.getRuntime().exec("id");
            InputStream inputStream = getUid.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            while( (line = bufferedReader.readLine()) != null ){
                stringBuilder.append(line);
            }

            ((TextView)findViewById(R.id.tv_uid)).setText("uid:\n" + stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
