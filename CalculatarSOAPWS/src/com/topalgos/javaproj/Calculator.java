package com.topalgos.javaproj;

import javax.jws.*;

@WebService(serviceName="Basic Calculator")
public class Calculator {

	@WebMethod
	public int add(int param1, int param2) {
		return param1 + param2;
	}
	
}
// http://localhost:8080/CalculatorSOAPWS/services/Calculator?wsdl
// http://localhost:8080/CalculatorSOAPWS/services