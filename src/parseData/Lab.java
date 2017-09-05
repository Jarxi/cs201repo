package parseData;

import java.util.List;

public class Lab extends Meeting {

	public Lab(String type, String section, String room, String meetingDay, String meetingTime,
			List<StaffMember> assistants) {
		super(type, section, room, meetingDay, meetingTime, assistants);
		// TODO Auto-generated constructor stub
	}
	public String getCapitalType(){
		return "Lab";
	}
	public String toString(){
		String msg = "Lab Meeting Information\n";
		if (getSection() != ""){
			msg =msg+ "Section: " + getSection() + "\n";
		}
		if (getRoom()!=""){
			msg =msg+ "Room: " + getRoom()+"\n";
		}
		
		if (getMeetingDay()!=""){
			msg = msg+"Meeting Day: " + getMeetingDay() + "\n";
		}
		
		if (getMeetingTime()!=""){
			msg =msg+ "Meeting Time: " + getMeetingTime() + "\n";
		}
		
		if ((int)getAssistants().size()!=0){
			msg =msg+ "Assistants: ";
			int length = getAssistants().size();
			for (int i = 0; i<getAssistants().size()-1; i++){
				msg = msg + getAssistants().get(i).getName() +", ";
			}
			msg = msg + getAssistants().get(length-1).getName();
		}
		return msg;
	}

}
