package parseData;

import java.util.ArrayList;

public class School {
	private String _name;
	private ArrayList<Department> _department;
	
	public String getName(){
		return _name;
	}
	
	public ArrayList<Department> getDepartment(){
		return _department;
	}
	
	public School(String name, ArrayList<Department> department){
		_name = name;
		_department = department;
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
