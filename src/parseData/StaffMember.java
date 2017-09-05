package parseData;
import java.util.ArrayList;
public class StaffMember {
	protected String _type;
	private String _id;
	private String _name;
	private String _email;
	private String _image;
	private String _office;
	private ArrayList<String> _officeHour;

	public StaffMember(String type, String id, String fname, String lname, 
		String email, String image, String office, ArrayList<String> officeHour){
		_type = type;
		_id = id;
		_email = email;
		_name = fname + " " + lname; 
		_image = image;
		_office = office;
		_officeHour = officeHour;

	}
	public String getPhoneNumber(){
		return "";
	}

	public String getType(){
		return _type;
	}
	public String getFullType(){
		if (_type.equals( "ta")){
				return "Teaching Assistant";
		}
		else if(_type.equals("cp")){
			return "Course Producer";
		}
		else if(_type.equals("instructor")){
			return "instructor";
		}
		return "Grader";
	}

	public String getId(){
		return _id;
	}

	public String getName(){
		return _name;
	}

	public String getEmail(){
		return _email;
	}
	public String getImage(){
		return _image;
	}

	public String getOffice(){
		return _office;
	}

	public ArrayList<String> getOfficeHour(){
		return _officeHour;
	}

}