package parseData;

import java.util.ArrayList;

public class MainLecture {
	private String _week="";
	private String _number="";	
	private ArrayList<Textbook> _textbook = new ArrayList<Textbook>();
	private String _date = "";
	private String _day = "";
	private ArrayList<MainLab> _labList = new ArrayList<MainLab>();
	private ArrayList<Topic> _topicList = new ArrayList<Topic>();
	
	public String getWeek(){
		return _week;
	}
	
	public String getNumber(){
		return _number;
	}
	
	public ArrayList<Textbook> getTextbook(){
		return _textbook;
	}
	
	public String getDate(){
		return _date;
	}
	
	public ArrayList<MainLab> getLabs(){
		return _labList;
	}
	
	public ArrayList<Topic> getTopic(){
		return _topicList;
	}
	
	public String getDay(){
		return _day;
	}
	
	public ArrayList<Textbook> getTextbooks(){
		return _textbook;
	}
	public MainLecture(String week, String number, ArrayList<Textbook> textbook, String date, String day,
			ArrayList<MainLab> labList, ArrayList<Topic> topicList){
		_week = week;
		_number =number;
		_textbook = textbook;
		_date = date;
		_day = day;
		_labList = labList;
		_topicList = topicList;
	}

}
