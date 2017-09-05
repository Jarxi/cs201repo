package parseData;

import java.util.ArrayList;

public class Instructor extends StaffMember {
	private String _phoneNumber;
	public Instructor(String type, String id, String fname, String lname, String email,
			String image, String office,ArrayList<String>officeHour, String phoneNumber) {
		super(type, id, fname, lname, email, image, office, officeHour);
		_phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber(){
		return _phoneNumber;
	}
	public String getCapitalType(){
		return "Instructors";
	}
	public String toString(){
		String msg = "Instructor\n";
		msg = msg + "Name: " + getName() + "\n";
		msg = msg + "Email: " + getEmail()+ "\n";
		msg = msg + "Image: " + getImage()+ "\n";
		msg = msg + "Phone: " + getPhoneNumber()+ "\n";
		msg = msg + "Office: " + getOffice()+"\n";
		msg = msg + "Office Hours: " + getOfficeHour() + "\n";
		return msg;
	}
	
}
