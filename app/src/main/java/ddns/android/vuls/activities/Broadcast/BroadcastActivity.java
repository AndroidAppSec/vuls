package ddns.android.vuls.activities.Broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ddns.android.vuls.R;

public class BroadcastActivity extends AppCompatActivity {

    private DosReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
    }

    public void brInfoleak(View view){
        startActivity(new Intent(this, LoginBroadcastActivity.class));
    }

    public void registBR(View view){
        if(receiver==null) {
            receiver = new DosReceiver();
            IntentFilter filter = new IntentFilter("ddns.receiver.dos");
            registerReceiver(receiver, filter);
            Toast.makeText(this, "注册广播接收器", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "已经注册了广播接收器", Toast.LENGTH_LONG).show();
        }
    }

    public void unRegistBR(View view) {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
            Toast.makeText(this, "解注册广播接收器", Toast.LENGTH_LONG).show();
        } else {

        }
    }

    public void sendOrderBr(View view) {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
            Toast.makeText(this, "解注册广播接收器", Toast.LENGTH_LONG).show();
        } else {

        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(receiver!=null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }
}
