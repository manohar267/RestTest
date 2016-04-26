package com.javacodegeeks.enterprise.rest.resteasy;

public class StaticTestClass {

	public StaticTestClass(String name){
		System.out.println("Calls From constructor");
	}
	public static String name="murali";
	
	static{
		
		System.out.println("Name value is"+name);
	}
	
	public static String getMethod(){
		System.out.println("Static Method Returns"+ name);
		return name;
	}
}

