package com.example.android.BluetoothChat;

public enum ReflectorType {
		
	A("EJMZALYXVBWFCRQUONTSPIKHGD"),
	B("YRUHQSLDPXNGOKMIEBFZCWVJAT"),
	C("FVPJIAOYEDRZXWGCTKUQSBNMHL"),

	B_THIN("ENKQAUYWJICOPBLMDXZVFTHRGS"),
	C_THIN("RDOBJNTKVEHMLFCWZAXGYIPSUQ");
	
	private char[] mapping;
	
	ReflectorType(String charMapping){
		mapping = charMapping.toCharArray();
	}
	
	public char[] getMapping(){
		return mapping;
	}
}
