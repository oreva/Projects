package ua.org.oreva.studyNN.beans;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/16/14
 * Time: 9:37 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Message {
	private String phone;
	private String email;
	private String message;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
