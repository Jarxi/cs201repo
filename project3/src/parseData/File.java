package parseData;

public class File {
	private String _number="";
	private String _title="";
	private String _url = "";
	public String getNumber(){
		return _number;
	}
	public String getTitle(){
		return _title;
	}
	public String getURL(){
		return _url;
	}
	
	public File(String number, String title, String url){
		_number = number;
		_title = title;
		_url = url;
	}
}
