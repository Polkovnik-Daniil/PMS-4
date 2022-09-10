package rdp.fit.bstu.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MainActivityThird extends AppCompatActivity {
    private Intent intent = null;
    private String result = null;
    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_third);

            intent = getIntent();

            result = " ";
            result += "\nNAME:\t\t"+intent.getStringExtra(MainActivity.EXTRA_NAME);
            result += "\nSURNAME:\t\t"+intent.getStringExtra(MainActivity.EXTRA_SURNAME);

            result += "\nMARRIED:\t\t"+intent.getStringExtra(MainActivity.EXTRA_MARRIED) == "true" ? "Married" : "Not Married";
            result += "\nDATE:\t\t"+intent.getStringExtra(MainActivitySecond.EXTRA_DATE);
            result += "\nSEX:\t\t"+intent.getStringExtra(MainActivitySecond.EXTRA_SEX);
            result += "\nPASSPORTNUMBER:\t\t"+intent.getStringExtra(MainActivitySecond.EXTRA_PASSPORTNUMBER);

            textView = (TextView)findViewById(R.id.result);
            textView.setText(result);
            FileWriter fWriter;
            File sdCardFile = new File(Environment.getExternalStorageDirectory() + "\\filename.txt");
            JSONObject object = new JSONObject();
            object.put("NAME", intent.getStringExtra(MainActivity.EXTRA_NAME));
            object.put("SURNAME", intent.getStringExtra(MainActivity.EXTRA_SURNAME));
            object.put("MARRIED", intent.getStringExtra(MainActivity.EXTRA_MARRIED) == "true" ? "Married" : "Not Married");
            object.put("DATE", intent.getStringExtra(MainActivitySecond.EXTRA_DATE));
            object.put("PASSPORTNUMBER", intent.getStringExtra(MainActivitySecond.EXTRA_PASSPORTNUMBER));
            object.put("SEX", intent.getStringExtra(MainActivitySecond.EXTRA_SEX));
            fWriter = new FileWriter(sdCardFile, true);
            fWriter.write(object.toString());
            fWriter.flush();
            fWriter.close();
        }catch (Exception exception){
            System.out.println("MainActivityThird\t"+exception.getMessage());
            textView.setText(exception.getMessage());
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