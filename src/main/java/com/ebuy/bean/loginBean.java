package com.ebuy.bean;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebuy.model.Person;
import com.ebuy.service.PersonService;



@Component
@ManagedBean(name="loginBean")
@SessionScoped
public class loginBean implements Serializable  {

	
	@Autowired
	PersonService personService ;
	
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String passowrd;
	private boolean admin;
	private String error;

	private Person person=new Person();
	
	/////////
	public PersonService getPersonService() {
		return personService;
	}

	
	/////////////
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}


	public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/tempLogin.jsf?faces-redirect=true";
	}
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@PostConstruct
	public void init(){
		System.out.println("init......");
		this.username="ahmedamin";
		setUsername("ahmedamin");
		this.person=personService.findByUserName(username);
		System.out.println("init......2");
		admin=true;
	}
	
	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public Person findPerson(){
		
		return personService.findByUserName(username);
	}
	
	public String getfirstName(){
		return personService.findByUserName(username).getFirstName();
	}
	
	
	public String getlastName(){
		return personService.findByUserName(username).getLastName();
	}

	public String getLocation() throws UnknownHostException{
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
		        .getRequest();

		String ip = request.getRemoteAddr();
		if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
		    InetAddress inetAddress = InetAddress.getLocalHost();
		    String ipAddress = inetAddress.getHostAddress();
		    ip = ipAddress;
		    System.out.println("ipAddress:>>>>  "+ip);
		    
		}
		return "/adminHome.jsf";
	}
        //String ip = request.getHeader("X-Forwarded-For");
//        ip = request.getHeader("Proxy-Client-IP");
//        System.out.println("ipAddress: "+ip);
//        ip = request.getHeader("WL-Proxy-Client-IP");
//        System.out.println("ipAddress: "+ip);
//        ip = request.getHeader("HTTP_CLIENT_IP");
//        System.out.println("ipAddress: "+ip);
//        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        System.out.println("ipAddress: "+ip);
//        
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
		

    public String login(){

    	System.out.println("enter login");
		setUsername("ahmedamin");
		
		admin=true;
		System.out.println("enter login" + this.username);
    	return "/adminHome.xhtml";
    }
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassowrd() {
		return passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public loginBean() {
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
