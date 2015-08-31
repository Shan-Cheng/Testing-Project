package edu.ucsc.extension.soap;

import java.rmi.RemoteException;

import org.junit.Test;

import NET.webserviceX.www.StockQuoteSoap;
import NET.webserviceX.www.StockQuoteSoapProxy;

import com.w3schools.www.webservices.TempConvertSoap;
import com.w3schools.www.webservices.TempConvertSoapProxy;

public class Tests {
	
	
	public void testWebService() throws RemoteException {
		// Your code here
		TempConvertSoap client = new TempConvertSoapProxy();
		String answer = client.celsiusToFahrenheit("100");
		System.out.println(answer);
		System.out.println(client);
	}
	@Test
	public void testWebService2() throws RemoteException {
		// Your code here
		StockQuoteSoap client = new StockQuoteSoapProxy();
		String netflix = client.getQuote("NFLX");
		System.out.println(netflix);
		String ucsc = client.getQuote("UCSCEXTN");
		System.out.println(ucsc);
	}

}
