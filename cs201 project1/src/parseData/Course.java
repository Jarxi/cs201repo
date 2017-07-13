package parseData;


import java.util.ArrayList;
public class Course {
	private String _number;
	private String _term;
	private String _year;
	private ArrayList<StaffMember> _staffMember;
	private ArrayList<Meeting> _meetings;
	public String getNumber(){
		return _number;
	}
	
	public String getTerm(){
		return _term;
	}
	
	public String getYear(){
		return _year;
	}
	
	public ArrayList<StaffMember> getStaffMember(){
		return _staffMember;
	}
	
	public ArrayList<Meeting> getMeetings(){
		return _meetings;
	}
	public String getName(){
		return (_number+" " + _term+" " + _year);
	}
	public Course(String number, String term, String year, ArrayList<StaffMember> staffMembers,
			ArrayList<Meeting> meetings){
		_number =number;
		_term = term;
		_year = year;
		_staffMember = staffMembers;
		_meetings = meetings;
	}
}
