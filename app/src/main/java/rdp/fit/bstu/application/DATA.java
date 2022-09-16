package rdp.fit.bstu.application;

import android.content.Intent;

import java.io.Serializable;

public class DATA implements Serializable {

    //region Intent references
    protected static String EXTRA_SEX = "EXTRA_SEX";
    protected static String EXTRA_DATE = "EXTRA_DATE";
    protected static String EXTRA_NAME = "EXTRA_NAME";
    protected static String EXTRA_SURNAME = "EXTRA_SURNAME";
    protected static String EXTRA_MARRIED = "EXTRA_MARRIED";
    protected static String EXTRA_PASSPORTNUMBER = "EXTRA_PASSPORTNUMBER";
    //endregion
    //region Singleton
    public static DATA Instance = null;
    public static DATA getInstance(){
        if(Instance==null){
            Instance = new DATA();
            return Instance;
        }
        return Instance;
    }
    public static void setInstance(DATA data){
        Instance = data;
    }
    //endregion
    //region Value
    public String name = null, surName = null,
                  passportNumber = null, data = null,
                  phoneNumber = null, ref_social_network = null,
                  Sex = null;
    public boolean Married = false;
    public Intent intent = null;
    //endregion
    public DATA(){ }
}
