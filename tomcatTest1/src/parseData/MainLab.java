package parseData;
import java.util.ArrayList;
public class MainLab {
	private String _number = "";
	private String _title = "";
	private String _url = "";
	private ArrayList<File> _fileList = new ArrayList<File>();
	
	public MainLab(String number, String title, String url, ArrayList<File> fileList){
		_number = number;
		_title = title;
		_url = url;
		_fileList = fileList;
	}
	
	public String getNumber(){
		return _number;
	}
	public String getTitle(){
		return _title;
	}
	public ArrayList<File> getFiles(){
		return _fileList;
	}
	public String getUrl(){
		return _url;
	}
}
