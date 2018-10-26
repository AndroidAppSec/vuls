package ddns.android.vuls.services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.ArrayList;

import ddns.android.vuls.MainActivity;

public class SMSService extends Service {
    public SMSService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        SmsManager smsManager = SmsManager.getDefault();
        String phoneNumber = intent.getStringExtra("phone");
        String message = intent.getStringExtra("content");
        ArrayList<String> contents = smsManager.divideMessage(message);
        for (String content : contents){
            smsManager.sendTextMessage(phoneNumber, null, content, null, null);
            Log.e("SMSService", "send sms: " + content);
        }

        return super.onStartCommand(intent, flags, startId);
    }

}
