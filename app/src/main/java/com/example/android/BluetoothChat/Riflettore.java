package com.example.android.BluetoothChat;


import static com.example.android.BluetoothChat.Util.toInt;

/**
 * Created by Enrico on 15/04/15.
 */
public class Riflettore {
    private char[] sequenza;

    public Riflettore(ReflectorType reflector) {
        sequenza = reflector.getMapping();
    }

    public char rifletti(char c) {
        int value = toInt(c);
        return sequenza[value];
    }
}
