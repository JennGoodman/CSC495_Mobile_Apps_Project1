package jenn.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class AddContact extends ActionBarActivity {

    EditText contactName;
    EditText emailAddress;
    Spinner contactClass;

    private final String fileName = "project1_contacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        contactName = (EditText) findViewById(R.id.txtContactName);
        emailAddress = (EditText) findViewById(R.id.txtContactEmail);
        contactClass = (Spinner) findViewById(R.id.spnClassChoice);
    }

    public void onClickAddClass (View v) {
        startActivity(new Intent(this, AddClass.class));
    }

    public void onClickSave (View v) {

        try {
            FileOutputStream fOut = openFileOutput(fileName, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.write(contactName.getText().toString() + "|"
                    + emailAddress.getText().toString() + "|"
                    + contactClass.getSelectedItem().toString() + "\n");
            osw.flush();
            osw.close();
        }
        catch (IOException e) { e.printStackTrace(); }
        finally { finish(); }
    }

    public void onClickCancel (View v) { finish(); }

    protected void onResume() {
        super.onResume();

        ArrayList<String> lines = new ArrayList<>();
        String line;

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(
                                    this.getFilesDir().getPath() + "/project1_classes"), "UTF-8"));

            lines.add("Select a Class...");
            while ((line = reader.readLine()) != null) { lines.add(line); }

            reader.close();
        }
        catch (IOException e) {
            lines.add("Add a Class...");
            e.printStackTrace();
        }
        finally {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_dropdown_item, lines);

            contactClass.setAdapter(adapter);
        }
    }
}
