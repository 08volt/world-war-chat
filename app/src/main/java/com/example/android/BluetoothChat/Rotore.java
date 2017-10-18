package com.example.android.BluetoothChat;

import android.util.Log;



import static com.example.android.BluetoothChat.Util.toChar;
import static com.example.android.BluetoothChat.Util.toInt;

/**
 * Created by Enrico on 15/04/15.
 */
public class Rotore {

    String[] tipi = {"EKMFLGDQVZNTOWYHXUSPAIBRCJ","AJDKSIRUXBLHWTMCQGZNPYFVOE","BDFHJLCPRTXVZNYEIWGAKMUSQO",
            "ESOVPZJAYQUIRHXLNFTGKDCMWB","VZBRGITYUPSDNHLXAWMJQOFECK","JPGVOUMFYQBENHZRDKASXLICTW",
            "NZJHGRCXMYSWBOUFAIVLPEKQDT","FKQHTLXOCBJSPDZRAMEWNIUYGV",
            // inverses
            "UWYGADFPVZBECKMTHXSLRINQOJ","AJPCZWRLFBDKOTYUQGENHXMIVS","TAGBPCSDQEUFVNZHYIXJWLRKOM",
            "HZWVARTNLGUPXQCEJMBSKDYOIF","QCYLXWENFTZOSMVJUDKGIARPHB","SKXQLHCNWARVGMEBJPTYFDZUIO",
            "QMGYVPEDRCWTIANUXFKZOSLHJB","QJINSAYDVKBFRUHMCPLEWZTGXO"};

    int contatorepassi = 0;
    char[] sequenza;
    char[] sequenzaInversa;

    public Rotore(char inizio, int tipo) {


        sequenza = tipi[tipo-1].toCharArray();

        while(inizio != sequenza[0])
        {
            this.stepRotor();
        }

        reverseMapping();
    }


    private void reverseMapping() {
        sequenzaInversa = new char[26];
        for (int i = 0; i < sequenza.length; i++){
            int j = toInt(sequenza[i]);
            sequenzaInversa[j] = toChar(i);
        }
    }



    public char in(char charInput) {
       int posizione = toInt(charInput);
       return sequenza[posizione];
    }


    public char out(char charInput){
        int posizione = toInt(charInput);
        return sequenzaInversa[posizione];
    }
    public void stepRotor(){
        char primo = sequenza[0];
        for(int i = 0; i < sequenza.length -1; i++) {
            sequenza[i] = sequenza[i+1];
        }
        sequenza[sequenza.length -1] = primo;
        reverseMapping();
        contatorepassi++;
        if(contatorepassi == 26)
        {
            contatorepassi = 0;
        }
        String s = String.copyValueOf(sequenza);
        Log.i("sequenza", s+ " passi: "+contatorepassi);


    }

    public boolean GiroFatto()
    {
        return contatorepassi==0;
    }



}
