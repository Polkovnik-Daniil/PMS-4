package rdp.fit.bstu.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivitySecond extends AppCompatActivity {
    private static Intent intent = null, nextIntent = null;
    public static Bundle SavedInstanceState = null;
    public static final String _passportnumber_intent = "passportnumber";
    public static final String _date_intent = "date";
    public static final String _sex_intent = "sex";
    public static final String _phone_intent = "phone";


    public static Data data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);
        data = data.Instance;
        intent = getIntent();
        SavedInstanceState = savedInstanceState;

        EditText editText = (EditText)findViewById(R.id.date);
        editText.setText(data._date);

        editText = (EditText)findViewById(R.id.passportnumber);
        editText.setText(data._passportnumber);

        editText = (EditText)findViewById(R.id.Phone);
        editText.setText(data._phone);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        ((RadioButton)radioGroup.getChildAt(data._sex.equals("Man") ? 0 : 1)).setChecked(true);
        //radioGroup.check(data._sex == "Man" ? 0 : 1);

        Button next = (Button) findViewById(R.id.nextMAF2);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivityThird();
            }
        });
        Button email = findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                startActivity(intent);
                Log.d("EMAIL","WAS CLICK!");
            }
        });
    }
    public void MainActivityThird(){
        try {
            Intent nextIntent = new Intent(this, MainActivityThird.class);

            EditText editText = findViewById(R.id.passportnumber);
            String _passportnumber = editText.getText().toString();
            data._passportnumber = _passportnumber;
            nextIntent.putExtra(_passportnumber_intent, data._passportnumber);

            EditText editTextFirst = findViewById(R.id.date);
            String date = editTextFirst.getText().toString();
            data._date = date;
            nextIntent.putExtra(_date_intent, data._date);

            EditText editTextPhone = findViewById(R.id.Phone);
            String phone = editTextPhone.getText().toString();
            data._phone = phone;
            nextIntent.putExtra(_phone_intent, data._phone);


            RadioGroup radioGroup = findViewById(R.id.radioGroup);
            int radioButtonID = radioGroup.getCheckedRadioButtonId();
            View radioButton = radioGroup.findViewById(radioButtonID);
            int idx = radioGroup.indexOfChild(radioButton);
            String sex = idx == 0 ? "Man" : "Woman";
            data._sex = idx == 0 ? "Man" : "Woman";
            nextIntent.putExtra(_sex_intent, sex);
            nextIntent.putExtra(MainActivity._married_intent, intent.getStringExtra(MainActivity._married_intent));
            nextIntent.putExtra(MainActivity._surname_intent, intent.getStringExtra(MainActivity._surname_intent));
            nextIntent.putExtra(MainActivity._name_intent, intent.getStringExtra(MainActivity._name_intent));

            startActivity(nextIntent);
        }catch (Exception exception){
            System.out.println("MainActivitySecond\t" + exception.getMessage());
        }
    }
}