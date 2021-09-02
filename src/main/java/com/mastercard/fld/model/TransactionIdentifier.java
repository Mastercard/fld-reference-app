package com.mastercard.fld.model;

/**
 * TransactionIdentifierInner
 */
public class TransactionIdentifier {

	private String cfcKey;

	private String cfcValue;

	public TransactionIdentifier(String cfcKey, String cfcValue) {
		this.cfcKey = cfcKey;
		this.cfcValue = cfcValue;
	}

}
