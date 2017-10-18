package com.example.android.BluetoothChat;

import android.util.Log;


import static com.example.android.BluetoothChat.Util.ASCII_OFFSET;

/**
 * Created by Enrico on 14/04/15.
 */
public class MacchinaEnigma {

    Rotore[] rotori = new Rotore[3];
    Plugboard pannello;
    Riflettore riflettore;

    //i rotori sono gia configurati con le posizioni iniziali
    public void CostruisciEnigma(Rotore r1,Rotore r2, Rotore r3, Riflettore r,Plugboard pan){
        rotori[0] = r1;
        rotori[1] = r2;
        rotori[2] = r3;
        riflettore = r;
        pannello = pan;

    }
    private void MuoviRotori()
    {
        rotori[0].stepRotor();
        if(rotori[0].GiroFatto())
        {
            rotori[1].stepRotor();
            if(rotori[1].GiroFatto())
            {
                rotori[2].stepRotor();
            }
        }
    }
    private char converti(char p){

        MuoviRotori();

        char c = pannello.converti(p);

        for(int i = 0; i < 3; i++){

            c = rotori[i].in(c);

        }

        c = riflettore.rifletti(c);

        for(int i = rotori.length ; i > 0; i--){
            c = rotori[i].out(c);
        }

        c = pannello.converti(c);


        return c;
    }

    public String crypt(String plain)
    {
        plain = plain.toUpperCase();
        String cript = "";
        char[] plainchararray = plain.toCharArray();

        for (char p : plainchararray) {
            int charValue = (int) p - ASCII_OFFSET;
            if (charValue >= 0 && charValue <= 25) {
                p = converti(p);
            }
            cript += p;
        }

        return cript;
    }
}
