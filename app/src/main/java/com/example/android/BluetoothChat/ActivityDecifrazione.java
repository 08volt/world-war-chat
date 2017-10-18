package com.example.android.BluetoothChat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class ActivityDecifrazione extends Activity {

    Button buttonEnigma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //imposto il layout
        setContentView(R.layout.layout_enigma);

        //configuro l'evento Onclick del Button ENIGMA
        buttonEnigma = (Button)findViewById(R.id.button);
        buttonEnigma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recupero la chiave: sequenza di lettere per configurare i rotori
                char[] key = ((EditText)findViewById(R.id.chiavi1)).getText().toString().toUpperCase().toCharArray();

                //reupero il tipo dei tre rotori da inserire
                String tipoRotori = ((EditText)findViewById(R.id.rotori1)).getText().toString();

                //imposto i tre rotori
                Rotore rot1 = new Rotore(key[0],Integer.valueOf(tipoRotori.charAt(0) - 48 ));
                Rotore rot2 = new Rotore(key[1],Integer.valueOf(tipoRotori.charAt(1) - 48 ));
                Rotore rot3 = new Rotore(key[2],Integer.valueOf(tipoRotori.charAt(2) - 48));

                //recupero la configurazione dei cavi e configuro il pannello
                Plugboard p = new PlugboardImpl();
                String[] coppie = ((EditText)findViewById(R.id.plugboard1)).getText().toString().split(" ");
                for(String coppia:coppie)
                {
                    if(coppia.length() == 2)
                        p.aggiungiCavo(coppia.charAt(0),coppia.charAt(1));
                }

                //configuro un riflettore
                Riflettore r = new Riflettore(ReflectorType.B);


                //costrisco la macchina enigma con i "pezzi" configurati in precedenza
                MacchinaEnigma enigmaMachine = new MacchinaEnigma();
                enigmaMachine.CostruisciEnigma(rot1,rot2,rot3,r,p);

                //decripto il messaggio usando la macchina creata
                String pt = ((EditText)findViewById(R.id.plainedittext)).getText().toString();
                String ct = enigmaMachine.crypt(pt);
                ((TextView)findViewById(R.id.criptext)).setText(ct);



            }
        });

        //recupero di messaggio da cifrare e lo scrivo nella casella di testo
        EditText et = (EditText)findViewById(R.id.plainedittext);
        et.setText(getIntent().getStringExtra("messaggio"));


    }



}
