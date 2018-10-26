package ddns.android.vuls.activities.Services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ddns.android.vuls.R;
import ddns.android.vuls.services.SMSService;

public class ServiceActivity extends AppCompatActivity {

    private EditText et_phone;
    private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        et_phone = findViewById(R.id.et_phonenum);
        et_content = findViewById(R.id.et_content);
    }


    public void sendSMS(View view){

        Intent intent = new Intent(this, SMSService.class);
        intent.putExtra("phone", et_phone.getText().toString());
        intent.putExtra("content", et_content.getText().toString());

        startService(intent);
    }
}
