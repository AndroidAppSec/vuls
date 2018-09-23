package ddns.android.vuls.Common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;

public class AntiTools {

    private static String TAG = "AntiTools";

    public static boolean checkSignature(Context context, String hash){
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try{
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);
            Signature signature = packageInfo.signatures[0];
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            byte[] signHash = messageDigest.digest(signature.toByteArray());
            if (hash.equalsIgnoreCase(toHex(signHash))){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private static String toHex(byte[] bytes) {

        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            if (Integer.toHexString(0xFF & bytes[i]).length() == 1) {
                stringBuffer.append("0").append(Integer.toHexString(0xFF & bytes[i]));
            } else {
                stringBuffer.append(Integer.toHexString(0xFF & bytes[i]));
            }
        }
        String result = stringBuffer.toString();
        Log.e(TAG, "signature: " + result);
        return result;
    }

    public static boolean checkProxy(){
        String proxyHost = System.getProperty("http.proxyHost");
        String proxyPort = System.getProperty("http.proxyPort");
        Log.e(TAG, "proxy info: " + proxyHost + ":" + proxyPort);
        if (proxyHost != null & proxyPort != null){
            return  true;
        }
        return false;
    }

    public static boolean checkPort(int port){
        try {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            Socket socket = new Socket(inetAddress, port);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkHook(){
        boolean flag = false;
        String mapsFilename="/proc/"+android.os.Process.myPid()+"/maps";
        Log.e(TAG, mapsFilename);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(mapsFilename));
            String line;
            while((line=reader.readLine())!=null){
                if(line.contains("com.saurik.substrate")) {
                    Log.e(TAG, "Substrate shared object found: " + line);
                    flag = true;
                    break;
                }
                if(line.contains("XposedBridge.jar")) {
                    Log.e(TAG, "Xposed JAR found: " + line);
                    flag = true;
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
            Log.wtf("HookDetection", e.toString());
        }
        return flag;
    }

}
