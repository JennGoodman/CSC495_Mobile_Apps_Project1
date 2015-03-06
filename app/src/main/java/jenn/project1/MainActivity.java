package jenn.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView contactList;
    private final String fileName = "/project1_contacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = (ListView) findViewById(R.id.listContacts);
    }

    public void onClickAddContact(View v) {
        if (v == findViewById(R.id.btnAddContact)) {
            startActivity(new Intent(this, AddContact.class));
        }
    }

    protected void onResume() {
        super.onResume();

        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream(
                                this.getFilesDir().getPath() + fileName), "UTF-8"));

            String line;

            while ((line = reader.readLine()) != null) {
                line = line.substring(0, line.indexOf("|"));
                lines.add(line); }

            reader.close();

            contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent out = new Intent(parent.getContext(), ViewContact.class);
                    out.putExtra("position", position);
                    startActivity(out);
                }
            });
        }
        catch (IOException e) {
            lines.add("No Contacts Found");
            e.printStackTrace();
        }
        finally {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, lines);

            contactList.setAdapter(adapter);
        }
    }
}
