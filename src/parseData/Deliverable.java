package parseData;

import java.util.ArrayList;

public class Deliverable {
	private String _number;
	private String _dueDate;
	private String _title;
	private String _gradePercentage;
	ArrayList<File> _fileList = new ArrayList<File>(); 
	
	public Deliverable(String number, String dueDate, String title, String gradePercentage,
			ArrayList<File> fileList){
		_number = number;
		_dueDate = dueDate;
		_title = title;
		_gradePercentage = gradePercentage;
		_fileList = fileList;
	}
	
	public String getNumber(){
		return _number;
	}
	
	public String getDueDate(){
		return _dueDate;
	}
	public String getTitle(){
		return _title;
	}
	public String getGradePercentage(){
		return _gradePercentage;
	}
	public ArrayList<File> getFileList(){
		return _fileList;
	}
}
