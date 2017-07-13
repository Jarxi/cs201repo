package parseData;

public class StaffMember {
	protected String _type;
	private String _id;
	private String _name;
	private String _email;
	private String _image;
	private String _office;
	private String _officeHour;

	public StaffMember(String type, String id, String fname, String lname, 
		String email, String image, String office, String officeHour){
		_type = type;
		_id = id;
		_email = email;
		_name = fname + " " + lname; 
		_image = image;
		_office = office;
		_officeHour = officeHour;

	}

	public String getType(){
		return _type;
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

	public String getOfficeHour(){
		return _officeHour;
	}
	


	

}