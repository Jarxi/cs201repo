package parseData;

import java.util.ArrayList;

public class School {
	private String _name;
	private ArrayList<Department> _department;
	private String _image;
	public String getName(){
		return _name;
	}
	
	public String getImage(){
		return _image;
	}
	
	public ArrayList<Department> getDepartment(){
		return _department;
	}
	
	public School(String name, ArrayList<Department> department, String image){
		_name = name;
		_department = department;
		_image = image;
	}
	
	public String toString(){
		String msg = "";
		if(!_name.equals("")){
			msg += _name+"\n"; 
		}
		for (int i=0; i<_department.size(); i++){
			msg += _department.get(i).toString();
		}
		return msg;
	}
} 
