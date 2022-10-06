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

    // Метод проверки, существует ли файл и не является ли он каталогом
    public static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
    }

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
            File sdCardFile = new File("filename.txt");
            if(!isFileExists(sdCardFile)){
                Serialize.Serialize("filename.txt",DATA.getInstance());
                return;
            }

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