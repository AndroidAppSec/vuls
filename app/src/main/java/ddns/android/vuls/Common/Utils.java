package ddns.android.vuls.Common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {

    public static String exec(String cmd){

        StringBuilder result = new StringBuilder();

        try{
            Process process = Runtime.getRuntime().exec(new String[]{"/system/bin/sh", "-c", cmd});
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while( (line = bufferedReader.readLine()) != null ){
                result.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return result.toString();
    }
}
