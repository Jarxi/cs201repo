package parseData;

import java.util.ArrayList;

public class Department {
	private String _name;
	private String _prefix;
	private ArrayList<Course> _courses;
	public String getName(){
		return _name;
	}
	public ArrayList<Course> getCourses(){
		return _courses;
	}
	public String getPrefix(){
		return _prefix;
	}
	
	public Department(String longname, String prefix, ArrayList<Course> courses){
		if (prefix!=""){
			_name = longname + " (" + prefix + ")";
		}
		else{
			_name = longname;
		}
		_prefix = prefix;
		_courses = courses;
	}

	public String toString(){
		String msg = "";
		if (!_name.equals("")){
			msg+=_name;
		}
		return msg;
	}
}
