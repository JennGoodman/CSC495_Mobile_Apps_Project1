package jenn.project1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class ViewContact extends ActionBarActivity {

    TextView contactName;
    TextView contactEmail;
    TextView contactClass;

    private final String fileName = "/project1_contacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        contactName = (TextView) findViewById(R.id.textDisplayName);
        contactEmail = (TextView) findViewById(R.id.textDisplayEmail);
        contactClass = (TextView) findViewById(R.id.textDisplayClass);

        String line;
        int id = this.getIntent().getIntExtra("position", 0);

        BufferedReader reader;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(
                                    this.getFilesDir().getPath() + fileName), "UTF-8"));

            for (int x = 0; x < id; x++){ reader.readLine(); }
            line = reader.readLine();

            reader.close();

            contactName.setText(line.substring(0, line.indexOf("|")));
            contactEmail.setText(line.substring(line.indexOf("|") + 1, line.lastIndexOf("|")));
            contactClass.setText(line.substring(line.lastIndexOf("|") + 1));
        }
        catch (IOException e) {
            contactName.setText("");
            contactEmail.setText("");
            contactClass.setText("");
            e.printStackTrace();
        }
    }

    public void onClickBack(View v) {
        finish();
    }
}
