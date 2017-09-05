package parseData;

import java.util.ArrayList;

public class Assignment {
	private String _number="";
	private String _assignedDate = "";
	private String _dueDate = "";
	private String _title = "";
	private String _url = "";
	private ArrayList<File> _fileList = new ArrayList<File>();
	private String _gradePercentage="";
	private String _gradingCriteria = "";
	private String _solution = "";
	private ArrayList<Deliverable> _deliverableList = new ArrayList<Deliverable>();
	public String getNumber(){
		return _number;
	}
	public String getAssignedDate(){
		return _assignedDate;
	}
	public String getDueDate(){
		return _dueDate;
	}
	public String getTitle(){
		return _title;
	}
	public String getUrl(){
		return _url;
	}
	public ArrayList<File> getFileList(){
		return _fileList;
	}
	public String getGradingCriteria(){
		return _gradingCriteria;
	}
	public String getGradingPercentage(){
		return _gradePercentage;
	}
	public String getSolution(){
		return _solution;
	}
	public ArrayList<Deliverable> getDeliverableList(){
		return _deliverableList;
	}
	public Assignment(String number, String assignedDate, String dueDate, String title,
			String url, ArrayList<File> fileList, String gradePercentage,
			String gradingCriteria, String solution,  ArrayList<Deliverable> deliverableList){
		_number = number;
		_assignedDate = assignedDate;
		_dueDate = dueDate;
		_title = title;
		_url = url;
		_fileList=fileList;
		_gradePercentage = gradePercentage;
		_gradingCriteria = gradingCriteria;
		_solution = solution;
		_deliverableList = deliverableList;
	}
}
