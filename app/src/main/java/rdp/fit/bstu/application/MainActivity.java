package rdp.fit.bstu.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import java.io.IOException;

public class MainActivity extends Activity {
    public static final String _name_intent = "name";
    public static final String _surname_intent = "surname";
    public static final String _married_intent = "married";

    private static Intent intent = null;
    public static Data data = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = Data.getInstance();
        try {
            Data.JSONDeserialise("/data/data/rdp.fit.bstu.application/", "JSON.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Button next = (Button) findViewById(R.id.nextMAF1);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivitySecond();
            }
        });
        TextInputLayout name = findViewById(R.id.name);
        name.getEditText().setText(data._name);//data._name
        TextInputLayout surname = findViewById(R.id.surname);
        surname.getEditText().setText(data._surname);//data._surname
        CheckBox married =  findViewById(R.id.married);
        married.setChecked(data._married);//data._married
    }
    public void onClickPhoto(View view){
        try{
            Intent intent = new
                Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, 1);
        }
        }catch (Exception exception){
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText("!WAS CLICKED!");
        }

    }

    public void MainActivitySecond(){
        TextInputLayout name = findViewById(R.id.name);
        TextInputLayout surname = findViewById(R.id.surname);

        data = Data.Instance;
        data._name = name.getEditText().getText().toString();
        data._surname = surname.getEditText().getText().toString();

        intent = new Intent(this, MainActivitySecond.class);

        intent.putExtra(_name_intent, data._name);
        intent.putExtra(_surname_intent, data._surname);
        intent.putExtra(_married_intent, data._married);

        startActivity(intent);
    }

    public void onCheckBox(View view) {
        data._married = !data._married;
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
        TextInputLayout name = findViewById(R.id.name);
        TextInputLayout surname = findViewById(R.id.surname);
        data._name = name.getEditText().getText().toString();
        data._surname = surname.getEditText().getText().toString();
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Save();
        outState.putString(_name_intent, data._name);
        outState.putString(_surname_intent, data._surname);
        outState.putString(_married_intent, data._married == true ? "true" : "false");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        data._name = savedInstanceState.getString(_name_intent);
        data._surname = savedInstanceState.getString(_surname_intent);
        data._married = savedInstanceState.getString(_married_intent) == "true" ? true : false;
        TextInputLayout name = findViewById(R.id.name);
        TextInputLayout surname = findViewById(R.id.surname);

        name.getEditText().setText(data._name);
        surname.getEditText().setText(data._surname);

        CheckBox checkBox = findViewById(R.id.married);
        checkBox.setChecked(data._married);
    }
}