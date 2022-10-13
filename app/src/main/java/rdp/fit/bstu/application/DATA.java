package rdp.fit.bstu.application;

import android.content.Intent;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.jar.Attributes;

public class Data {

    //region Intent references
    public String _sex = "sex";
    public String _phone = "phone";
    public String _date = "date";
    public String _name = "name";
    public String _surname = "surname";
    public boolean _married = false;
    public String _passportnumber = "";
    //endregion
    //region Singleton
    public static Data getInstance(){
        if (Instance == null) {
            Instance = new Data();
        }
        return Instance;
    }
    public static Data Instance = null;
    public Intent intent = null;
    //endregion
    public Data(){ }
    public static Boolean JSONSerialise(String path, String filename, Data data) throws IOException {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(path, filename), data);
            return true;
        }catch (Exception exception){
            Log.d("Exception!", exception.getMessage());
            return false;
        }
    }
    public static Data JSONDeserialise(String path, String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //InputStream is = Data.class.getResourceAsStream(path + filename);
        InputStream is = new FileInputStream(path + filename);
        if(isFileExists(new File(path, filename))) {
            Data data = mapper.readValue(is, Data.class);
            return data;
        }
        return null;
    }
    public static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
    }
}
