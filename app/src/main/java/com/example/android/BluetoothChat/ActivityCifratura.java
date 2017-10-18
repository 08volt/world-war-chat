package com.example.android.BluetoothChat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.BluetoothChat.Util.toChar;
import static com.example.android.BluetoothChat.Util.toInt;


public class ActivityCifratura extends Activity {

    Button buttonEnigma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup the window
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_activity_crypt);

        // Set result CANCELED in case the user backs out
        setResult(Activity.RESULT_CANCELED);

        buttonEnigma = (Button)findViewById(R.id.inviabtn);
        buttonEnigma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recupero la chiave: sequenza di lettere per configurare i rotori
                char[] key = ((EditText)findViewById(R.id.chiavi)).getText().toString().toUpperCase().toCharArray();

                //reupero il tipo dei tre rotori da inserire
                String tipoRotori = ((EditText)findViewById(R.id.rotori)).getText().toString();

                //imposto i tre rotori
                Rotore rot1 = new Rotore(key[0],Integer.valueOf(tipoRotori.charAt(0) - 48 ));
                Rotore rot2 = new Rotore(key[1],Integer.valueOf(tipoRotori.charAt(1) - 48 ));
                Rotore rot3 = new Rotore(key[2],Integer.valueOf(tipoRotori.charAt(2) - 48));

                //recupero la configurazione dei cavi e configuro il pannello
                Plugboard p = new PlugboardImpl();
                String[] coppie = ((EditText)findViewById(R.id.plugboard)).getText().toString().split(" ");
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

                //cripto il messaggio con la macchina creata
                String pt = getIntent().getStringExtra("messaggio");
                String ct = enigmaMachine.crypt(pt);
                Intent i = new Intent();
                i.putExtra("messaggio",ct);
                setResult(RESULT_OK,i);
                finish();





            }
        });



    }




}
