package jenn.project1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;


public class AddClass extends ActionBarActivity {

    private EditText className;
    private EditText classNumber;
    private final String fileName = "project1_classes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        className = (EditText) findViewById(R.id.txtClassName);
        classNumber = (EditText) findViewById(R.id.txtClassNumber);
    }

    public void onClickSubmit (View v) {
        try {
            FileOutputStream fOut = openFileOutput(fileName, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.write(className.getText().toString()
                    + "-"
                    + classNumber.getText().toString()
                    + "\n");
            osw.flush();
            osw.close();

        }
        catch (IOException e) { e.printStackTrace(); }

        finish();
    }

    public void onClickCancel (View v) {
        finish();
    }
}
