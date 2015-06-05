package com.freshplanet.ane.crashlytics.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A throwable class that allows to put a given stacktrace for the Exception.
 */
public class CustomException extends Throwable {
	/** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -3042686055658047285L;
	
	
	// CONSTRUCTOR :
	/**
	 * Creates a new instance of CustomException with the given message, and the given stack trace.
	 * @param message		The message associated with the Exception. Usually, the first line of the raw stacktrace retieved from AS with Error.getStackTrace()
	 * @param stackTrace	The actual stack trace lines.
	 */
	public CustomException(String message, String[] stackTrace) {
		super(message);
		
		int length = stackTrace.length;
		StackTraceElement[] stackTraceLines = new StackTraceElement[stackTrace.length];
		
		String declaringClass;
		String methodName;
		String fileName;
		int lineNumber;
		
		Pattern pattern = Pattern.compile("(\\t)*at (.*)\\/(.*)(\\[(.*):(\\d*)\\])?");
		Matcher matcher;
		
		
		// Parses each stack trace line to put it in the exception.
		for(int i = 0 ; i < length ; i++) {
			matcher = pattern.matcher(stackTrace[i]);
			matcher.find();
			declaringClass = matcher.group(2);
			methodName = matcher.group(3);
			fileName = matcher.group(4);
			
			lineNumber = -1;
			try { 
				lineNumber = Integer.parseInt(matcher.group(5));
			}
			catch(Exception e) {  }
			
			stackTraceLines[i] = new StackTraceElement(declaringClass, methodName, fileName, lineNumber);
		}
		
		this.setStackTrace(stackTraceLines);
	}
}
