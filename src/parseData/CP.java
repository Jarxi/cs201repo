package parseData;
import java.util.ArrayList;
public class CP extends StaffMember {

	public CP(String type, String id, String fname, String lname, String email, String image, String office,
			ArrayList<String>officeHour) {
		super(type, id, fname, lname, email, image, office, officeHour);
		// TODO Auto-generated constructor stub
	}
	public String getCapitalType(){
		return "CPs";
	}
	public String toString(){
		String msg = "CP\n";
		msg = msg + "Name: " + getName() + "\n";
		msg = msg + "Email: " + getEmail()+ "\n";
		msg = msg + "Image: " + getImage()+ "\n";
		msg = msg + "Office: " + getOffice()+"\n";
		msg = msg + "Office Hours: " + getOfficeHour() + "\n";
		return msg;
	}
}
