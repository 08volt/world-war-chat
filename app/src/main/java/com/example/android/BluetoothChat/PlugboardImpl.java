package com.example.android.BluetoothChat;

import static com.example.android.BluetoothChat.Util.toChar;
import static com.example.android.BluetoothChat.Util.toInt;
import static com.example.android.BluetoothChat.Util.validChar;

public class PlugboardImpl implements Plugboard {
	
	private int[] plugs = new int[26];
	
	public PlugboardImpl(){
		for (int i = 0; i < plugs.length; i++){
			plugs[i] = -1;
		}
	}

	@Override
	public char converti(char c) {
		
		if (validChar(c)){
			int plug = toInt(c);
			if (plugs[plug] == -1){
				return c;
			} else {
				return toChar(plugs[plug]);
			}
		}
		return c;
	}

	@Override
	public void aggiungiCavo(char a, char b) {
		
		if (validChar(a) && validChar(b) || a == b){
			plugs[toInt(a)] = toInt(b);
			plugs[toInt(b)] = toInt(a);
		}

	}
}
