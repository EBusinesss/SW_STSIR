package com.lmcu.siredo.dwp;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.lmcu.siredo.ws.impl.StationImpl;
import com.lmcu.siredo.ws.impl.MarqueImpl;


@ApplicationPath("resource")
public class ApplicationConfig extends Application {

	public Set<Class<?>> getClasses() 
	{
		return getRestClasses();
	}
	
	private Set<Class<?>> getRestClasses()
	{
		Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
		resources.add(StationImpl.class);
		resources.add(MarqueImpl.class);
		return resources;
	}
}


