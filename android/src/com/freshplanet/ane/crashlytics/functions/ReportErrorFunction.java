package com.freshplanet.ane.crashlytics.functions;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.crashlytics.android.Crashlytics;
import com.freshplanet.ane.crashlytics.models.CustomException;

/**
 * A function  that handles the actionscript error passed from the client. Builds an error with the given parameters, and
 * reports it to Crashlytics.
 */
public class ReportErrorFunction extends BaseFunction {
	
	public FREObject call(FREContext context, FREObject[] args){
		String TAG = "CRASHLYTICS_REPORT_ERROR";
		try {
			String rawStackTrace = args[0].getAsString();
			String[] lines = rawStackTrace.split("\n");
			String[] stackTrace = new String[lines.length-1];
			
			for(int i = 1 ; i < lines.length ; i++) 
				stackTrace[i-1] = lines[i];
			
			Crashlytics.logException(new CustomException(lines[0], stackTrace));
		}
		catch(Exception e) {
			Log.i(TAG, "Error when reporting error : " + e);
		}
		
		return null;
	}
}
