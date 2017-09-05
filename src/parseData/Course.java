package parseData;


import java.util.ArrayList;
public class Course {
	private String _number;
	private String _term;
	private String _year;
	private String _title;
	private String _unit;
	private String _syllabus;
	private ArrayList<MainLecture> _mainLectures;
	private ArrayList<StaffMember> _staffMember;
	private ArrayList<Meeting> _meetings;
	private ArrayList<Assignment> _assignments;
	private Exam _exams;
	public ArrayList<Assignment> getAssignments(){
		return _assignments;
	}
	public String getNumber(){
		return _number;
	}
	
	public String getTerm(){
		return _term;
	}
	
	public String getYear(){
		return _year;
	}
	public String getTitle(){
		return _title;
	}
	public String getUnit(){
		return _unit;
	}
	public ArrayList<StaffMember> getStaffMember(){
		return _staffMember;
	}
	
	public ArrayList<Meeting> getMeetings(){
		return _meetings;
	}
	public ArrayList<MainLecture> getLectures(){
		return _mainLectures;
	}
	public Exam getExams(){
		return _exams;
	}
	public String getName(){
		return (_number+" " + _term+" " + _year);
	}
	public String getSyllabus(){
		return _syllabus;
	}
	public Exam getExam(){
		return _exams;
	}
	public Course(String number, String term, String year, ArrayList<StaffMember> staffMembers,
			ArrayList<Meeting> meetings, String title, String unit,String syllabus,
			ArrayList<MainLecture> mainLecture,
			ArrayList<Assignment> assignments,
			Exam exams){
		_number =number;
		_term = term;
		_year = year;
		_staffMember = staffMembers;
		_meetings = meetings;
		_title = title;
		_unit = unit;
		_syllabus = syllabus;
		_mainLectures = mainLecture;
		_assignments = assignments;
		_exams = exams;
	}
}
