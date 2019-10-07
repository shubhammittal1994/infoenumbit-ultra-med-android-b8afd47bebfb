package com.soccermat.ultramed.models;

public class LoginResponse1{
	private int status_code;
	//private LoginData data;
	private String message;
//	private Errors errors;
	private String status;

	public void setStatusCode(int statusCode){
		this.status_code = statusCode;
	}

	public int getStatusCode(){
		return status_code;
	}

	/*public void setData(LoginData data){
		this.data = data;
	}

	public LoginData getData(){
		return data;
	}*/

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	/*public void setErrors(Errors errors){
		this.errors = errors;
	}

	public Errors getErrors(){
		return errors;
	}
*/
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
			/*",data = '" + data + '\'' +
			",message = '" + message + '\'' + 
			",errors = '" + errors + '\'' + */
			",status = '" + status + '\'' + 
			"}";
		}
}

 class LoginData{
	private String access_Token;
	private String zipcode;
	private String country;
	private String firstname;
	private int user_Id;
	private String city;
	private String name;
	private String email;
	private String lastname;
	private String isverified;

	public void setaccessToken(String access_Token){
		this.access_Token = access_Token;
	}

	public String getaccessToken(){
		return access_Token;
	}

	public void setZipcode(String zipcode){
		this.zipcode = zipcode;
	}

	public String getZipcode(){
		return zipcode;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setuserId(int user_Id){
		this.user_Id = user_Id;
	}

	public int getuserId(){
		return user_Id;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}

	public void setIsverified(String isverified){
		this.isverified = isverified;
	}

	public String getIsverified(){
		return isverified;
	}

	@Override
	public String toString(){
		return
				"Data{" +
						"access_token = '" + access_Token + '\'' +
						",zipcode = '" + zipcode + '\'' +
						",country = '" + country + '\'' +
						",firstname = '" + firstname + '\'' +
						",user_id = '" + user_Id + '\'' +
						",city = '" + city + '\'' +
						",name = '" + name + '\'' +
						",email = '" + email + '\'' +
						",lastname = '" + lastname + '\'' +
						",isverified = '" + isverified + '\'' +
						"}";
	}
}

