package rdp.fit.bstu.application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity {
    private static Button next = null;
    private static TextInputLayout name = null, surname = null;
    private TextView textView = null;
    private static String nameS = null, surnameS = null;
    private static Intent intent = null;
    private static boolean married = false;
    public static String EXTRA_NAME = "EXTRA_NAME";
    public static String EXTRA_SURNAME = "EXTRA_SURNAME";
    public static String EXTRA_MARRIED = "EXTRA_MARRIED";
    public static Bundle SavedInstanceState = null;
    public static DATA data = null;
    public static ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = DATA.getInstance();
        try {
            DATA.DeSerializable();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        next = (Button) findViewById(R.id.nextMAF1);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivitySecond();
            }
        });
        name = findViewById(R.id.name);
        name.getEditText().setText(data.name);
        surname = findViewById(R.id.surname);
        surname.getEditText().setText(data.surName);
        CheckBox married =  findViewById(R.id.married);
        married.setChecked(data.Married == "true" ? true : false);
    }
    public void onClickPhoto(View view){
        try{
            Intent intent = new
                Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, 1);
        }
        }catch (Exception exception){
            textView = (TextView) findViewById(R.id.textView);
            textView.setText("!WAS CLICKED!");
        }

    }

    public void MainActivitySecond(){
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        data = DATA.getInstance();
        data.name = name.getEditText().getText().toString();
        nameS = data.name;
        data.surName = surname.getEditText().getText().toString();
        surnameS = data.surName;

        intent = new Intent(this, MainActivitySecond.class);

        intent.putExtra(EXTRA_NAME, nameS);
        intent.putExtra(EXTRA_SURNAME, surnameS);
        intent.putExtra(EXTRA_MARRIED, married == true ? "true" : "false");

        startActivity(intent);
    }

    public void onCheckBox(View view) {
        married = !married;
    }

    @Override
    public void onPause(){
        super.onPause();
    }
    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void Save(){
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
//        nameS = name.getEditText().getText().toString();
        data.name = name.getEditText().getText().toString();
//        surnameS = surname.getEditText().getText().toString();
        data.surName = surname.getEditText().getText().toString();
        data.Married = married == true ? "true" : "false";
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Save();
        outState.putString("nameS", data.name);//nameS
        outState.putString("surnameS", data.surName);//surnameS
        outState.putString("married", married == true ? "true" : "false");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //        nameS = savedInstanceState.getString("nameS");
        data.name = savedInstanceState.getString("nameS");
//        surnameS = savedInstanceState.getString("surnameS");
        data.surName = savedInstanceState.getString("surnameS");
        married = savedInstanceState.getString("married") == "true" ? true : false;
        data.Married = married == true ? "true" : "false";
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);

        name.getEditText().setText(data.name);//nameS
        surname.getEditText().setText(data.surName);//surnameS

        CheckBox checkBox = findViewById(R.id.married);
        checkBox.setChecked(data.Married == "true" ? true : false);//married
    }
}