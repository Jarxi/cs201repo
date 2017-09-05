package parseData;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.xml.sax.SAXException; 
import java.lang.ClassCastException;
import java.util.ArrayList;

public class parseData {
	public   Document doc;
	private   Scanner sc;
	public   void openFile(String filename) throws ParserConfigurationException{
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder(); 
	    Boolean rightFile = false;
	    doc = null;
	    
	    while (rightFile == false){
		    try{
//		    		System.out.print("What is the name of the input file? ");
//				String filename = sc.nextLine();
		    		doc = db.parse(filename);
		    }
		    catch(FileNotFoundException fnfe){
	    			System.out.println(fnfe.getMessage());
		    }
		    catch(SAXException sax1){
		    		System.out.println(sax1.getMessage());
		    }
		    catch(IOException err){
		    		System.out.println(err.getMessage());
		    }
		    catch(ClassCastException cce){
		    		System.out.println(cce.getMessage());
		    }
		    finally{
		    		if (doc!=null){
		    			rightFile = true;
		    		}
		    }
	    }
	}
	
	public   void mainMenu(ArrayList<School> schoolList){
		Boolean school = false;
		int option = -1;
		while (school == false){
			sc = new Scanner(System.in);
			System.out.println();
			System.out.println("	1) Display schools");
			System.out.println("	2) Exit");
			System.out.print("What would you like to do? ");
			option = sc.nextInt();
			if(option == 1){
				chooseSchool(schoolList);
				school = true;
			}
			else if(option == 2){
				school = true;
				break;
			}
			else{
				System.out.println("That is not a valid option.");
				System.out.println();
			}
		}
	}
	
	
	public   void chooseSchool(ArrayList<School> schoolList){
		Boolean school = false;
		int option = -1;
		while (school == false){
			System.out.println();
			//display options
			System.out.println("Schools");
			for(int i = 0; i< schoolList.size() ; i ++){
	            System.out.println("	" +(i+1) + ") "+ schoolList.get(i).getName() );
			}
			System.out.println("	"+ (schoolList.size() +1) + ") Go to main menu");
			System.out.println("	"+(schoolList.size()+2)+") Exit");
			System.out.print("What would you like to do? ");
			
			//decisions
			option = sc.nextInt();
			if (option == schoolList.size()+1){
				mainMenu(schoolList);
				return;
			}
			else if(option == schoolList.size()+2){
				return;
			}
			else{
				for (int i=1; i<schoolList.size()+1;i++){
					if(option == i){
						ArrayList<Department> departmentList = schoolList.get(i-1).getDepartment();
						chooseDepartment(schoolList,departmentList);
						school = true;
						return;
					}
				}
				if (option>schoolList.size()+1 && option <1){
					System.out.println("That is not a valid opiton");
				}
			}
		}
		
	}
	
	public   void chooseDepartment(ArrayList<School> schoolList,
			ArrayList<Department> departmentList){
		Boolean department = false;
		int option = -1;
		while (department == false){
			System.out.println();
			//display options
			System.out.println("Departments");
			for(int i = 0; i< departmentList.size() ; i ++){
	            System.out.println("	" +(i+1) + ") "+ departmentList.get(i).getName() );
			}
			System.out.println("	"+ (departmentList.size() +1) + ") Go to Schools menu");
			System.out.println("	"+(departmentList.size()+2)+") Exit");
			System.out.print("What would you like to do? ");
			
			//decisions
			option = sc.nextInt();
			if (option == departmentList.size()+1){
				chooseSchool(schoolList);
				return;
			}
			else if(option == departmentList.size()+2){
				return;
			}
			else{
				for (int i=1; i<departmentList.size()+1;i++){
					if(option == i){
						ArrayList<Course> courseList = departmentList.get(i-1).getCourses();
						chooseCourse(schoolList, departmentList, courseList, departmentList.get(i-1));
						department = true;
						return;
					}
				}
				if (option>departmentList.size()+1 && option <1){
					System.out.println("That is not a valid opiton");
				}
			}
		}
		
	}
	
	public   void chooseCourse(ArrayList<School> schoolList,
			ArrayList<Department> departmentList,ArrayList<Course> courseList, Department depart){
		Boolean control = false;
		int option = -1;
		while (control == false){
			System.out.println();
			//display options
			System.out.println(depart.getPrefix()+ " Courses" );
			for(int i = 0; i< courseList.size() ; i ++){
	            System.out.println("	" +(i+1) + ") "+depart.getPrefix()+" "+ courseList.get(i).getName() );
			}
			System.out.println("	"+ (courseList.size() +1) + ") Go to Departments menu");
			System.out.println("	"+(courseList.size()+2)+") Exit");
			System.out.print("What would you like to do? ");
			
			//decisions
			option = sc.nextInt();
			if (option == courseList.size()+1){
				chooseDepartment(schoolList,departmentList);
				return;
			}
			else if(option == courseList.size()+2){
				return;
			}
			else{
				for (int i=1; i<courseList.size()+1;i++){
					if(option == i){
						Course theCourse = courseList.get(i-1);
						staffMeetingMenu(schoolList,departmentList,courseList,depart,theCourse);
						control = true;
						return;
					}
				}
				if (option>departmentList.size()+1 && option <1){
					System.out.println("That is not a valid opiton");
				}
			}
		}
		
	}
	
	public   void staffMeetingMenu(ArrayList<School> schoolList,
			ArrayList<Department> departmentList,ArrayList<Course> courseList, 
			Department depart,Course course){
		
		Boolean control = false;
		int option = -1;
		while (control == false){
			//display options
			System.out.println();
			System.out.println(depart.getPrefix()+" "+course.getName() );
			System.out.println("	1) View course staff");
			System.out.println("	2) View meeting information");
			System.out.println("	3) Go to "+ depart.getPrefix()+ " courses "+ "menu");
			System.out.println("	4) Exit");
			System.out.print("What would you like to do? ");
			
			//decisions
			option = sc.nextInt();
			if (option == 1){
				viewStaff(schoolList,departmentList,courseList, depart,course);
				return;
			}
			else if(option == 2){
				viewMeeting(schoolList,departmentList,courseList, depart,course);
				return;
			}
			else if(option == 3){
				chooseCourse(schoolList,departmentList, courseList,depart);
				return;
			}
			else if(option == 4){
				return;
			}
			else{
				System.out.println("That is not a valid opiton");
			}
		}
	}
	
	public   void viewStaff(ArrayList<School> schoolList,
			ArrayList<Department> departmentList,ArrayList<Course> courseList, 
			Department depart,Course course){
		Boolean control = false;
		int option = -1;
		while (control == false){
			//display options
			System.out.println();
			System.out.println("Course Staff");
			System.out.println("	1) View Instructors");
			System.out.println("	2) View TAs");
			System.out.println("	3) View CPs");
			System.out.println("	4) View Graderss");
			System.out.println("	5) Go to "+ depart.getPrefix()+ course.getName() + " courses "+ "menu");
			System.out.println("	6) Exit");
			System.out.print("What would you like to do? ");
			
			//decisions
			option = sc.nextInt();
			if (option == 1){
				displayStaffInfo(course, "instructor");
				
			}
			else if(option == 2){
				displayStaffInfo(course, "ta");
				
			}
			else if(option == 3){
				displayStaffInfo(course, "cp");
				
			}
			else if(option == 4){
				displayStaffInfo(course, "grader");
				
			}
			else if(option == 5){
				staffMeetingMenu(schoolList,departmentList,courseList,depart,course);
				return;
			}
			else if(option == 6){
				return;
			}
			else{
				System.out.println("That is not a valid opiton");
			}
		}
		
	}
	public   void displayStaffInfo(Course course, String type){
		System.out.println();
		ArrayList<StaffMember> staffList = course.getStaffMember();
		for (int i=0; i<staffList.size();i++){
			if (staffList.get(i).getType().equals(type)){
				System.out.println(staffList.get(i).toString());
			}
			System.out.println();
		}
	}
	public   void viewMeeting(ArrayList<School> schoolList,
			ArrayList<Department> departmentList,ArrayList<Course> courseList, 
			Department depart,Course course){
		Boolean control = false;
		int option = -1;
		while (control == false){
			//display options
			System.out.println();
			System.out.println("Meeting Information");
			System.out.println("	1) Lecture");
			System.out.println("	2) Lab");
			System.out.println("	3) Quiz");
			System.out.println("	4) Go to "+ depart.getPrefix()+ course.getName() + " courses "+ "menu");
			System.out.println("	5) Exit");
			System.out.print("What would you like to do? ");
			
			//decisions
			option = sc.nextInt();
			if (option == 1){
				displayMeetingInfo(course, "lecture");
				
			}
			else if(option == 2){
				displayMeetingInfo(course, "lab");
				
			}
			else if(option == 3){
				displayMeetingInfo(course, "quiz");
				
			}
			else if(option == 4){
				staffMeetingMenu(schoolList,departmentList,courseList,depart,course);
				return;
			}
			else if(option == 5){
				return;
			}
			else{
				System.out.println("That is not a valid opiton");
			}
		}
		
	}
	
	public   void displayMeetingInfo(Course course, String type){
		System.out.println();
		ArrayList<Meeting> meetingList = course.getMeetings();
		for (int i=0; i<meetingList.size();i++){
			if (meetingList.get(i).getType().equals(type)){
				System.out.println(meetingList.get(i).toString());
			}
			System.out.println();
		}
	}
	
	public ArrayList<School> parse(String filename) throws Exception {
		openFile(filename);
		SchoolParser sp = new SchoolParser(doc);
		ArrayList<School> schoolList = sp.parse();
//		mainMenu(schoolList);
		System.out.println("Thank you for using my program");
		return schoolList;
	}
}
