package ddns.android.vuls.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import ddns.android.vuls.Common.Utils;

public class NetService extends Service {
    public NetService() {
    }


    public IGetNetStatusInterface.Stub netStub = new IGetNetStatusInterface.Stub(){
        @Override
        public String getNetStatus(String host){
            String cmd = "ping -c 1 " + host;
            Log.e("NetService", cmd);
            String result = Utils.exec(cmd);
            Log.e("NetService result", result);
            if (result.contains("1 received")){
                Log.e("NetService", "net is avaliable");
            }else {
                Log.e("NetService", "net is unavaliable");
            }
            return result;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return netStub;
    }

}
