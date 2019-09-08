package com.soccermat.ultramed.models;

public class LoginResponse{
	private int status_code;
	private String message;
	private String status;

	public void setStatusCode(int statusCode){
		this.status_code = statusCode;
	}

	public int getStatusCode(){
		return status_code;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"status_code = '" + status_code + '\'' +
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
