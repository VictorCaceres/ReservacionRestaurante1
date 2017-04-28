package victorcaceres.reservacionrestaurante1;

import android.app.Application;

/**
 * Created by HOMONY on 3/24/2015.
 */

public class MyApp extends Application {

    private String mGlobalVarValue="";

    public String getGlobalVarValue() {
        return mGlobalVarValue;
    }

    public void setGlobalVarValue(String str) {
        mGlobalVarValue += str+"\n";
    }
    public void setGlobalClear() {
        mGlobalVarValue ="";
    }
}