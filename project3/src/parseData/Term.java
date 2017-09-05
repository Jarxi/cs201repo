package parseData;

import java.util.ArrayList;

public class Term {
	private String _semester = "";
	private String _year = "";
	private ArrayList<ExamPaper> _examList = new ArrayList<ExamPaper>();
	
	public String getSemester(){
		return _semester;
	}
	
	public String getYear(){
		return _year;
	}
	
	public ArrayList<ExamPaper> getExamList(){
		return _examList;
	}
	
	public Term(String semester, String year, ArrayList<ExamPaper> examList){
		_semester = semester;
		_year = year;
		_examList = examList;
	}
}
