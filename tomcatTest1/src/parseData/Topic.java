package parseData;
import java.util.ArrayList;
public class Topic {
	private String _number = "";
	private String _title = "";
	private String _url = "";
	private String _chapter = "";
	private ArrayList<File> _fileList= new ArrayList<File>();
	public String getNumber(){
		return _number;
	}
	
	public String getTitle(){
		return _title;
	}
	
	public String getChapter(){
		return _chapter;
	}
	
	public ArrayList<File> getFiles(){
		return _fileList;
	}
	
	public String getUrl(){
		return _url;
	}
	
	public Topic(String number, String title, String url, String chapter, ArrayList<File> fileList){
		_number = number;
		_title = title;
		_url = url;
		_chapter = chapter;
		_fileList = fileList;
	}
}
