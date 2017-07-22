package parseData;

import java.util.ArrayList;

public class ExamPaper {
	private String _title;
	private ArrayList<File> _fileList = new ArrayList<File>();
	
	public String getTitle(){
		return _title;
	}
	public ArrayList<File> getFileList(){
		return _fileList;
	}
	
	public ExamPaper(String title, ArrayList<File> fileList){
		_title=title;
		_fileList = fileList;
	}
}
