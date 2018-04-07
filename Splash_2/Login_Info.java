package Splash_2_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

public class Login_Info {
	 /*
	  * Day and time of the day 
	  * Weather
	  * Login preferences
	  * Random quotes for MOTIVATION
	  */
	private Calendar def_cal;
	private String today_date, day_of_the_week, today_time, day_timePhrase, today_minute_string, today_hour_string, //time
				API_KEY = "XXXXXXX", latitude = "38.897957", longitude = "-77.036560",		//weather
				temperature, apparent_Temp, dailyHigh, dailyLow;
	private String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}, weather_arr = new String[4];
	private boolean isAM = false;
	private int today_hour, today_minute;
	private URL weather_url;
	
	public Login_Info() {
		default_info();
	}
	
	//default information 
	public void default_info() {
		def_cal = Calendar.getInstance();
		today_date = (def_cal.get(Calendar.MONTH)+1) + "-" + def_cal.get(Calendar.DATE) + "-" + def_cal.get(Calendar.YEAR);
		today_hour = def_cal.get(Calendar.HOUR);
		today_minute = def_cal.get(Calendar.MINUTE);
		day_of_the_week = strDays[def_cal.get(Calendar.DAY_OF_WEEK)-1];
		today_time = today_hour + ":" + today_minute;
		if (def_cal.get(Calendar.AM_PM)==Calendar.AM) 
			isAM = true;
		getTimePhrase(today_hour, isAM);
	}
	
	//returns the phrase according to the time of the day
	private void getTimePhrase(int hour, boolean isAM) {
		if (hour==0) hour = 12;
		if (hour>=7&& isAM && hour!=12) {
			day_timePhrase = "good morning";
		}
		else if ((hour==12 && !isAM) || (hour>=1 && hour<=4 && !isAM)) {
			day_timePhrase = "good afternoon";
		}
		else if (hour>4 && hour<=6 && !isAM) {
			day_timePhrase = "good evening";
		}
		else if ((hour>6 && !isAM) || (hour==12 && isAM) || (hour>=1 && hour<7 && isAM)) {
			day_timePhrase = "good night";
		}
	}
	
	//returns the default day of the week
	public String getDOTW() {
		return day_of_the_week;
	}
	
	//returns the default time phrase
	public String getTimePhrase() {
		return day_timePhrase;
	}
	
	//returns the current default time
	public String getTime() {
		def_cal = Calendar.getInstance(); //Updates the time 
		today_hour = def_cal.get(Calendar.HOUR);
		today_minute = def_cal.get(Calendar.MINUTE);
		
		if (today_hour==0) today_hour = 12;
		if (today_hour<10)	today_hour_string = "0 " + today_hour;
		else {
			today_hour_string = "" + today_hour;
			today_hour_string = today_hour_string.substring(0,1) + " " + today_hour_string.substring(1);
		}
		
		if (today_minute == 0)	today_minute_string = "0 0";
		else if (today_minute < 10) today_minute_string = "0 " + today_minute;
		else {
			today_minute_string = ""+today_minute;
			today_minute_string = today_minute_string.substring(0,1) + " " + today_minute_string.substring(1);
		}
		today_time = today_hour_string + "\n" + today_minute_string;
		return today_time;
	}
	
	/* 
	 * connects to the online weather JSON file from the Dark Sky API
	 * adds the temperature, "feels like" temperature, high and low temperature to the array
	 */
	public String[] getWeather() {
		try {
			weather_url = new URL("https://api.darksky.net/forecast/"+API_KEY+"/"+latitude+","+longitude);
			URLConnection con = weather_url.openConnection();
			InputStream is = con.getInputStream();
			
			BufferedReader read = new BufferedReader(new InputStreamReader(is));
			String line = read.readLine();
			
			temperature = line.substring(line.indexOf("\"temperature\"")+14,line.indexOf("\"apparentTemperature\"")-1) + " °F";
			apparent_Temp = line.substring(line.indexOf("apparentTemperature")+21,line.indexOf("\"dewPoint\"")-1) + " °F";
			
			String highlow_text = line.substring(line.indexOf("\"daily\""));
			dailyHigh = highlow_text.substring(highlow_text.indexOf("\"temperatureHigh\"")+18,highlow_text.indexOf("\"temperatureHighTime\"")-1) + " °F";
			dailyLow = highlow_text.substring(highlow_text.indexOf("\"temperatureLow\"")+17,highlow_text.indexOf("\"temperatureLowTime\"")-1) + " °F";
			
			weather_arr[0] = temperature; weather_arr[1] = apparent_Temp; weather_arr[2] = dailyHigh; weather_arr[3] = dailyLow;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			weather_arr[0] = "No Connection";
			for (int i=1; i<weather_arr.length; i++) {
				weather_arr[i] = "N/A";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return weather_arr;
	}

	public static void main(String[] args) {
		//Testing components
	//	Login_Info w = new Login_Info();
	}
}
