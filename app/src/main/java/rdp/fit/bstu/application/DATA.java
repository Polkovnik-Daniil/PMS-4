package rdp.fit.bstu.application;

import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.jar.Attributes;

public class DATA implements Serializable {

    //region Intent references
    protected static String EXTRA_SEX = "sex";
    protected static String EXTRA_PHONE = "phone";
    protected static String EXTRA_DATE = "date";
    protected static String EXTRA_NAME = "name";
    protected static String EXTRA_SURNAME = "surname";
    protected static String EXTRA_MARRIED = "married";
    protected static String EXTRA_PASSPORTNUMBER = "passportnumber";
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
    public static String name = null, surName = null,
                  passportNumber = null, date = null,
                  phoneNumber = null, ref_social_network = null,
                  Sex = null, phone = null, Married = null;
    public Intent intent = null;
    //endregion
    public DATA(){ }
    public static void DeSerializable() throws IOException, JSONException {
        File file = new File("/data/app/rdp.fit.bstu.application-EouPMJ3XPB78WGxWoqxR9g==/", "JSON.json");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        // This responce will have Json Format String
        String responce = stringBuilder.toString();
        JSONObject jsonObject  = new JSONObject(responce);
        name = jsonObject.get(EXTRA_NAME).toString();
        surName = jsonObject.get(EXTRA_SURNAME).toString();
        passportNumber = jsonObject.get(EXTRA_PASSPORTNUMBER).toString();
        date = jsonObject.get(EXTRA_DATE).toString();
        phone = jsonObject.get(EXTRA_PHONE).toString();
        Sex = jsonObject.get(EXTRA_SEX).toString();
        Married = jsonObject.get(EXTRA_MARRIED).toString();
    }

    public void Serializable() throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(EXTRA_SEX, Sex);
        jsonObject.put(EXTRA_PHONE, phone);
        jsonObject.put(EXTRA_DATE, date);
        jsonObject.put(EXTRA_NAME, name);
        jsonObject.put(EXTRA_SURNAME, surName);
        jsonObject.put(EXTRA_MARRIED, Married);
        jsonObject.put(EXTRA_PASSPORTNUMBER, phoneNumber);
        // Convert JsonObject to String Format
        String userString = jsonObject.toString();
        // Define the File Path and its Name
        File file = new File("/data/app/rdp.fit.bstu.application-EouPMJ3XPB78WGxWoqxR9g==/", "JSON.json");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();
    }
}
