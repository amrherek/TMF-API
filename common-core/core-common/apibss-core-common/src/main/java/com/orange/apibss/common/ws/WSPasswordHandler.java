package com.orange.apibss.common.ws;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class WSPasswordHandler implements CallbackHandler {

	private String password;


	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {

		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
		if (pc.getIdentifier() != null && password != null) {
			pc.setPassword(password);
		}
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
     * @param password
     *            the password to set
     */
	public void setPassword(String password) {
		this.password = password;
	}

}
