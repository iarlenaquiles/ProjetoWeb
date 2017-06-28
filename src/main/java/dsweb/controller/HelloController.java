package dsweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController {

	public String ola(HttpServletRequest request, HttpServletResponse response) {
		return "hello";
	}
	
	public String ola2(HttpServletRequest request, HttpServletResponse response) {
		return "hello2";
	}
	
}
