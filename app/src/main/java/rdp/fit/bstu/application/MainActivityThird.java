package rdp.fit.bstu.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;


public class MainActivityThird extends AppCompatActivity {
    public static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_third);
        TextView textView = (TextView)findViewById(R.id.result);
        try {

            Intent intent = getIntent();
            //можно занести в метод итд но мне лень
            Button button = (Button) findViewById(R.id.name);
            button.setText("Name: " + intent.getStringExtra(MainActivity._name_intent));
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Activity(true);
                }
            });

            button = null;
            button = (Button) findViewById(R.id.surname);
            button.setText("Surname: "+intent.getStringExtra(MainActivity._surname_intent));
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Activity(true);
                }
            });

            Bundle passedDate = getIntent().getExtras();
            button = null;
            button = (Button) findViewById(R.id.married);
            button.setText("married: " + (passedDate.getBoolean(MainActivity._married_intent) == true ? " Not Married" : "Married"));
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Activity(true);
                }
            });

            button = null;
            button = (Button) findViewById(R.id.passportnumber);
            button.setText("Passnum: "+intent.getStringExtra(MainActivitySecond._passportnumber_intent));
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Activity(false);
                }
            });

            button = null;
            button = (Button) findViewById(R.id.phone);
            button.setText("Phone: "+intent.getStringExtra(MainActivitySecond._phone_intent));

            button = null;
            button = (Button) findViewById(R.id.date);
            button.setText("Date: "+intent.getStringExtra(MainActivitySecond._date_intent));
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Activity(false);
                }
            });

            button = null;
            button = (Button) findViewById(R.id.sex);
            button.setText("Sex: " + intent.getStringExtra(MainActivitySecond._sex_intent));
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Activity(false);
                }
            });

            Data.JSONSerialise("/data/data/rdp.fit.bstu.application/", "JSON.json", Data.Instance);
        }catch (Exception exception){
            System.out.println("MainActivityThird\t"+exception.getMessage());
            textView.setText(exception.getMessage());
        }
    }
    protected void Activity(boolean value){
        Intent intent = new Intent(this, value == true ? MainActivity.class : MainActivitySecond.class);
        startActivity(intent);
    }
    public void onClickPhone(View view){
        Button phonenum = (Button)findViewById(R.id.phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phonenum.getText().toString()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}