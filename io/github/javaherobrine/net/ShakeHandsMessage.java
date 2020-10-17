package io.github.javaherobrine.net;
import java.beans.*;
@JavaBean
public class ShakeHandsMessage {
	int id;
	TransmissionStatus status;
	TransmissionFormat format;
	boolean connected;
}
