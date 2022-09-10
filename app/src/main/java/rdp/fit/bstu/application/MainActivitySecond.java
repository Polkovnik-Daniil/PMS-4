package rdp.fit.bstu.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivitySecond extends AppCompatActivity {
    private static Button next = null;
    private static TextInputLayout name = null, surname = null;
    private static TextView textView = null;
    private static RadioGroup radioGroup = null;
    private static Intent intent = null, nextIntent = null;
    private static EditText editText = null, editTextFirst= null;

    private static String passportnumberS = null, dateS = null, sexS = null;
    public static String EXTRA_PASSPORTNUMBER = "EXTRA_PASSPORTNUMBER";
    public static String EXTRA_DATE = "EXTRA_DATE";
    public static String EXTRA_SEX = "EXTRA_SEX";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);

        intent = getIntent();

        radioGroup = findViewById(R.id.radioGroup);

        next = (Button) findViewById(R.id.nextMAF2);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivityThird();
            }
        });
    }
    public void MainActivityThird(){
        try {
            nextIntent = new Intent(this, MainActivityThird.class);

            editText = findViewById(R.id.passportnumber);
            passportnumberS = editText.getText().toString();
            nextIntent.putExtra(EXTRA_PASSPORTNUMBER, passportnumberS);

            editTextFirst = findViewById(R.id.date);
            dateS = editTextFirst.getText().toString();
            nextIntent.putExtra(EXTRA_DATE, dateS);

            sexS = radioGroup.getCheckedRadioButtonId() == 0 ? "Man" : "Woman";
            nextIntent.putExtra(EXTRA_SEX, sexS);

            nextIntent.putExtra(MainActivity.EXTRA_MARRIED, intent.getStringExtra(MainActivity.EXTRA_MARRIED));
            nextIntent.putExtra(MainActivity.EXTRA_SURNAME, intent.getStringExtra(MainActivity.EXTRA_SURNAME));
            nextIntent.putExtra(MainActivity.EXTRA_NAME, intent.getStringExtra(MainActivity.EXTRA_NAME));

            startActivity(nextIntent);
        }catch (Exception exception){
            System.out.println("MainActivitySecond\t" + exception.getMessage());
        }
    }
}