
package com.soccermat.ultramed.models;

public class Errors{
	private String error_Code;
	private String message;

	public void setErrorCode(String errorCode){
		this.error_Code = errorCode;
	}

	public String getErrorCode(){
		return error_Code;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Errors{" + 
			"error_code = '" + error_Code + '\'' +
			",message = '" + message + '\'' + 
			"}";
		}

}
