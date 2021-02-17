package com.example.h7editori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText text; // tiedoston sisältämä teksti
    EditText file; // tiedoston nimi

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this; //tällä voidaan saada esim. tämänhetkinen kansiorakenne

        System.out.println(context.getFilesDir());

        file = (EditText) findViewById(R.id.fileText); //etsitään syötekentät
        text = (EditText) findViewById(R.id.writeText);
    }

    public void loadFile (View x) {
        try {
            InputStream ins = context.openFileInput(file.getText().toString()); //TODO Filen nimi tähän

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = ""; // tähän tulee tiedoston teksti

            text.setText(""); // tyhjennetään ensin tekstikenttä johon kirjoitetaan
            while ((s=br.readLine()) != null) {
                text.append(s);
            }

            ins.close();

        } catch (IOException e) {
            Log.e("IOException", "Virheellinen syöte.");
        } finally {
            System.out.println("Reading Done");
        }
    }

    public void saveFile (View x) {
        //System.out.println("here");
        try {
            // tarvitaan tiedoston nimi ja miten se avataan
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(file.getText().toString(), Context.MODE_PRIVATE)); //TODO Filen nimi tähän

            String s = ""; // tähän tulee tiedoston teksti
            s = text.getText().toString(); // tallennetaan teksti

            // kirjoitetaan teksti:
            osw.write(s);

            osw.close();

        } catch (IOException e) {
            Log.e("IOException", "Virheellinen syöte.");
        } finally {
            System.out.println("Writing Done");
        }
    }
}
