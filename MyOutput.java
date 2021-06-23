/**
 * @author Ahmed Abbas <a href="mailto:ahmed.abbas1@ucalgary.ca">ahmed.abbas1@ucalgary.ca</a>
 * 
 * @version	1.7
 * @since	1.0
 */

import java.util.regex.*;
import java.util.ArrayList;

enum Actions{
	END,
	ENABLE,
	START,
	TEST,
	DISABLE
}

enum Months{
	
	JAN {
		public String toString() {
			return "January";
		}
		public int toInt() {
			return 1;
		}
		public String toLog() {
			return "Jan";
		}
	},
	FEB{
		public String toString() {
			return "February";
		}
		public int toInt() {
			return 2;
		}
		public String toLog() {
			return "Feb";
		}
	},
	MAR{
		public String toString() {
			return "March";
		}
		public int toInt() {
			return 3;
		}
		public String toLog() {
			return "Mar";
		}
	},
	APR{
		public String toString() {
			return "April";
		}
		public int toInt() {
			return 4;
		}
		public String toLog() {
			return "Apr";
		}
	},
	MAY{
		public String toString() {
			return "May";
		}
		public int toInt() {
			return 5;
		}
		public String toLog() {
			return "May";
		}
	},
	JUN{
		public String toString() {
			return "June";
		}
		public int toInt() {
			return 6;
		}
		public String toLog() {
			return "Jun";
		}
	},
	JUL{
		public String toString() {
			return "July";
		}
		public int toInt() {
			return 7;
		}
		public String toLog() {
			return "Jul";
		}
	},
	AUG{
		public String toString() {
			return "August";
		}
		public int toInt() {
			return 8;
		}
		public String toLog() {
			return "Aug";
		}
	},
	SEP{
		public String toString() {
			return "September";
		}
		public int toInt() {
			return 9;
		}
		public String toLog() {
			return "Sep";
		}
	},
	OCT{
		public String toString() {
			return "October";
		}
		public int toInt() {
			return 10;
		}
		public String toLog() {
			return "Oct";
		}
	},
	NOV{
		public String toString() {
			return "November";
		}
		public int toInt() {
			return 11;
		}
		public String toLog() {
			return "Nov";
		}
	},
	DEC{
		public String toString() {
			return "December";
		}
		public int toInt() {
			return 12;
		}
		public String toLog() {
			return "Dec";
		}
	};

	public abstract int toInt();

	public abstract String toString();

	public abstract String toLog();
}

interface FormattedOutput {

	abstract String getFormatted();
}
class ParseLogfile{
	
	private ArrayList<ParseLine> log = new ArrayList<ParseLine>();

	public ParseLogfile(String[] array) {
		for (int i = 0; i < array.length; i++) {
			this.log.add(new ParseLine(array[i]));
		}
	}

	public ParseLine getLine(int index) {
		return this.log.get(index);
	}

	public ArrayList<ParseLine> getLog(){
		return this.log;
	}
}

class ParseLine{
	
	private final String LOGLINE;
	private final Location LOCATION;
	private final Device DEVICE;
	private final Action ACTION;
	private final DateTime DATETIME;
	private final IPv4 IPV4;

	public ParseLine(String line) {
		this.LOGLINE = line;
		this.LOCATION = new Location(line);
		this.DEVICE = new Device(line);
		this.ACTION = new Action(line);
		this.DATETIME = new DateTime(line);
		this.IPV4 = new IPv4(line);
	}

	public IPv4 getIPv4() {
		return this.IPV4;
	}

	public String getLogLine() {
		return this.LOGLINE;
	}

	public Location getLocation() {
		return this.LOCATION;
	}

	public Device getDevice() {
		return this.DEVICE;
	}

	public Action getAction() {
		return this.ACTION;
	}

	public DateTime getDateTime() {
		return this.DATETIME;
	}
}

class DateTime implements FormattedOutput{
	private final int DAY;
	private final int MONTH;
	private final int YEAR;
	private final int HOUR;
	private final int MINUTE;
	private final int SECOND;
	private static final String REGEX = "\\[([0-9]{1,2})/([a-zA-Z]{3})/"
			+ "([0-9]{4}):([0-9]{1,2}):([0-9]{2}):([0-9]{2})\\]";
	private static final Pattern PATTERN = Pattern.compile(REGEX);
	private final String MONTHNAME;
	

	public DateTime(String dateTime) {
		Matcher matcher = PATTERN.matcher(dateTime);
		matcher.matches();
		
		if(matcher.find()) {
			this.DAY = Integer.parseInt(matcher.group(1));
			Months monthEnum = Months.valueOf(matcher.group(2).toUpperCase());
			this.MONTHNAME = monthEnum.toString();
			this.MONTH = monthEnum.toInt();
			this.YEAR = Integer.parseInt(matcher.group(3));
			this.HOUR = Integer.parseInt(matcher.group(4));
			this.MINUTE = Integer.parseInt(matcher.group(5));
			this.SECOND = Integer.parseInt(matcher.group(6));
		}
		else {
			this.DAY = 0;
			this.MONTH = 0;
			this.YEAR = 0;
			this.HOUR = 0;
			this.MINUTE = 0;
			this.SECOND = 0;
			this.MONTHNAME = null;
		}
	}

	public String getFormatted() {
		return "Day: "+ this.DAY + ", Month: " + this.MONTHNAME + ", Year: " 
				+ this.YEAR + ", Hour: " + this.HOUR + ", Minute: " + this.MINUTE 
				+ ", Second: " + this.SECOND;
	}

	public int getSecond() {
		return this.SECOND;
	}

	public int getMinute() {
		return this.MINUTE;
	}

	public int getHour() {
		return this.HOUR;
	}

	public int getDay() {
		return this.DAY;
	}

	public int getMonth() {
		return this.MONTH;
	}

	public int getYear() {
		return this.YEAR;
	}

	public String monthToString() {
		return this.MONTHNAME;
	}

	public String getRegex() {
		return REGEX;
	}
}

class IPv4 implements FormattedOutput{
	private final String IP;
	private final static String REGEX = "((\\d{1,3}\\.){3}\\d{1,3})";
	private final static Pattern PATTERN = Pattern.compile(REGEX);

	public IPv4(String ip) {
		Matcher matcher = PATTERN.matcher(ip);
		
		if(matcher.find()) { this.IP = matcher.group(1); }
		else { this.IP = null; }
	}

	public String getFormatted() {
		return "IPv4: " + this.IP;
	}

	public String getIP() {
		return this.IP;
	}

	public static String getRegex() {
		return REGEX;
	}
}

class Action implements FormattedOutput{
	private final String ACTION;
	private final static String REGEX = "([A-Z]{2,7})";
	private final static Pattern PATTERN = Pattern.compile(REGEX);

	public Action(String action) {
		Matcher matcher = PATTERN.matcher(action);
		matcher.matches();
		
		if(matcher.find()) {
			boolean test = false;
			for (Actions actions : Actions.values()) {
				if(actions.name().equals(matcher.group(0))) { test = true; }
			}
			
			if(test == false) { this.ACTION = null; }
			else { this.ACTION = matcher.group(0); }
		}
		else { this.ACTION = null; }
	}

	public String getFormatted() {
		return "Action: " + this.ACTION;
	}

	public String getAction() {
		return this.ACTION;
	}

	public static String getRegex() {
		return REGEX;
	}
}

class Device implements FormattedOutput{
	private final String DEVICE;
	private final static String REGEX = "([a-z]+)(\\s[a-z]+)?\\s\\(";
	private final static Pattern PATTERN = Pattern.compile(REGEX);

	public Device(String device) {
		Matcher matcher = PATTERN.matcher(device);
		matcher.matches();
		
		if(matcher.find()) { this.DEVICE = matcher.group(0).replace(" (", ""); }
		else { this.DEVICE = null; }
	}

	public String getFormatted() {
		return "Device: " + this.DEVICE;
	}

	public String getDevice() {
		return this.DEVICE;
	}

	public static String getRegex() {
		return REGEX;
	}
}

class Location implements FormattedOutput{
	private final String ROOM;
	private final String BUILDING;
	private final static String REGEX = "\\((([a-zA-Z]+\\s)?[a-zA-Z]+)\\s-\\s(([a-zA-Z]+\\s)?[a-zA-Z]+)";
	private final static Pattern PATTERN = Pattern.compile(REGEX);

	public Location(String location) {
		Matcher matcher = PATTERN.matcher(location);
		matcher.matches();
		
		if(matcher.find()) {
			this.ROOM = matcher.group(1);
			this.BUILDING = matcher.group(3);
		}
		else {
			this.ROOM = null;
			this.BUILDING = null;
		}
	}

	public String getFormatted() {
		return "Room: " + this.ROOM + ", Building: " + this.BUILDING;
	}

	public String getRoom() {
		return this.ROOM;
	}

	public String getBuilding() {
		return this.BUILDING;
	}

	public static String getRegex() {
		return REGEX;
	}
}

public class MyOutput {
	public static void main(String args[]) {
		String[] exampleLog = exampleLog();

		var logfile = new ParseLogfile(exampleLog);
		var line = logfile.getLine(1);
		System.out.println("Log line 1: " + line.getLogLine());

		var ip = line.getIPv4();
		System.out.println("IPv4: "+ip.getIP());

		var dt = line.getDateTime();
		System.out.println("Day: "+dt.getDay());
		System.out.println("Month: "+dt.getMonth());
		System.out.println("Month (named): "+dt.monthToString());
		System.out.println("Year: "+dt.getYear());
		System.out.println("Hour: "+dt.getHour());
		System.out.println("Minute: "+dt.getMinute());
		System.out.println("Second: "+dt.getSecond());

		var act = line.getAction();
		System.out.println("Action: "+act.getAction());

		var dev = line.getDevice();
		System.out.println("Device: "+dev.getDevice());

		var loc = line.getLocation();
		System.out.println("Room: "+loc.getRoom());
		System.out.println("Building: "+loc.getBuilding());

		System.out.println();
		line = logfile.getLine(6);
		System.out.println("Log line 6: " + line.getLogLine());
		System.out.println(line.getIPv4().getFormatted());
		System.out.println(line.getDateTime().getFormatted());
		System.out.println(line.getAction().getFormatted());
		System.out.println(line.getDevice().getFormatted());
		System.out.println(line.getLocation().getFormatted());

		System.out.println("\nExample of toLog() output: " + Months.AUG.toLog());
		System.out.println("\nExample regex (for DateTime): "+dt.getRegex());
	}


	// Contains example data
	public static String[] exampleLog() {
		String[] log = {
"81.220.24.207 - - [2/Mar/2020:10:05:44] \"END sprinkler (Visitor entrance - Building A)\"",
"81.220.24.207 - - [2/Mar/2020:10:05:26] \"ENABLE cooling system (Secured room - Building A)\"",
"81.220.24.207 - - [2/Mar/2020:10:05:39] \"START heating system (Hall - Central)\"",
"81.220.24.207 - - [2/Mar/2020:10:05:52] \"ENABLE door lock (Visitor entrance - Building B)\"",
"81.220.24.207 - - [2/Mar/2020:10:05:21] \"TEST cooling system (Entrance - Building B)\"",
"66.249.73.135 - - [17/May/2020:01:05:17] \"TEST fan (Secured room - Airport location)\"",
"46.105.14.53 - - [17/May/2020:11:05:42] \"TEST cooling system (Secured room - Airport location)\"",
"218.30.103.62 - - [17/May/2020:11:05:11] \"START sprinkler (Secured room - Airport location)\"",
"218.30.103.62 - - [17/May/2020:11:05:46] \"DISABLE fan (Control room - Central)\"",
"218.30.103.62 - - [17/May/2020:11:05:45] \"START door lock (Secured room - Building A)\"",
"66.249.73.135 - - [27/Jun/2020:11:05:31] \"TEST sprinkler (Hall - Building B)\""};
		return log;
	}
}
