package rdp.fit.bstu.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static Button next = null;
    private static TextInputLayout name = null, surname = null;
    private static String nameS = null, surnameS = null;
    private static Intent intent = null;
    private static boolean married = false;
    public static String EXTRA_NAME = "EXTRA_NAME";
    public static String EXTRA_SURNAME = "EXTRA_SURNAME";
    public static String EXTRA_MARRIED = "EXTRA_MARRIED";
    public static Bundle SavedInstanceState = null;
    public static ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SavedInstanceState = savedInstanceState;
        next = (Button) findViewById(R.id.nextMAF1);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivitySecond();
            }
        });

    }

    public void MainActivitySecond(){
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);

        nameS = name.getEditText().getText().toString();
        surnameS = surname.getEditText().getText().toString();

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
        onSaveInstanceState(SavedInstanceState);
    }
    @Override
    public void onResume(){
        super.onResume();
        onRestoreInstanceState(SavedInstanceState);
    }
    @Override
    public void onStop(){
        super.onStop();
        onSaveInstanceState(SavedInstanceState);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        onSaveInstanceState(SavedInstanceState);
    }

    protected void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);

        nameS = name.getEditText().getText().toString();
        surnameS = surname.getEditText().getText().toString();

        bundle.putString("nameS", nameS);
        bundle.putString("surnameS", surnameS);
        bundle.putString("married", married == true ? "true" : "false");

    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        nameS = savedInstanceState.getString("nameS");
        surnameS = savedInstanceState.getString("surnameS");
        married = savedInstanceState.getString("married") == "true" ? true : false;

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);

        name.getEditText().setText(nameS);
        surname.getEditText().setText(surnameS);

        CheckBox checkBox = findViewById(R.id.married);
        checkBox.setChecked(married);
    }
}