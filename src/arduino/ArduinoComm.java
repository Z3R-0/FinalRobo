package arduino;

import arduino.Arduino;

public class ArduinoComm {
	private String bppCOM = "COM4";
	private String tspCOM = "COM3";
	private int baudRate = 9600;
	
	Arduino bppBot = new Arduino(bppCOM, baudRate);
	Arduino tspBot = new Arduino(tspCOM, baudRate);

	bppBot.serialWrite("ping");
	
	

}
