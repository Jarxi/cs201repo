package parseData;

public class Textbook {
	private String _number = "";
	private String _author = "";
	private String _title = "";
	private String _publisher = "";
	private String _year = "";
	private String _isbn = "";
	public String getNumber(){
		return _number;
	}
	public String getAuthor(){
		return _author;
	}
	public String getTitle(){
		return _title;
	}
	public String getPublisher(){
		return _publisher;
	}
	
	public String getYear(){
		return _year;
	}
	public String getIsbn(){
		return _isbn;
	}
	public Textbook(String number, String author, String title, String publisher, String year,
			String isbn){
		_number = number;
		_author = author;
		_title = title;
		_publisher = publisher;
		_year = year;
		_isbn = isbn;
	}
}
