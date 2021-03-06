package com.fabbychat;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.provider.ProviderManager;

import com.fabbychat.sasl.SASLFacebookMechanism;


public class FbChatConnection {
	
	private static XMPPConnection conn;
	
	public static XMPPConnection getConnection() {
		if (conn == null) {
			ProviderManager.getInstance().addIQProvider("vCard", "vcard-temp",
				new org.jivesoftware.smackx.provider.VCardProvider());
        	// register Facebook SASL mechanism
        	SASLAuthentication.registerSASLMechanism(
        		SASLFacebookMechanism.NAME,
                SASLFacebookMechanism.class);
        	SASLAuthentication.supportSASLMechanism(
        		SASLFacebookMechanism.NAME, 0);

        	// create a connection
        	ConnectionConfiguration config = 
        		new ConnectionConfiguration("chat.facebook.com", 5222);
            conn = new XMPPConnection(config);
            // by default, reconnection is allowed; is this okay?
		}
		return conn;
	}

}
