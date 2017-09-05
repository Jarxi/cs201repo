package parseData;

import java.util.List;

public abstract class Meeting {
	private String _type;
	private String _section;
	private String _room;
	private String _meetingDay;
	private String _meetingTime;
	private List<StaffMember> _assistants;
	public Meeting(String type, String section, String room, String meetingDay,
			String meetingTime, List<StaffMember> assistants){
		_type = type;
		_section = section;
		_room = room;
		_meetingDay = meetingDay;
		_meetingTime = meetingTime;
		_assistants = assistants;
	}


	public String getType(){
		return _type;
	}
	
	public String getSection(){
		return _section;
	}
	
	public String getRoom(){
		if(_room.equals("OFFICE")){
			return "TBD";
		}
		return _room;
	}
	
	public String getMeetingDay(){
		return _meetingDay;
	}
	
	public String getMeetingTime(){
		return _meetingTime;
	}
	
	public List<StaffMember> getAssistants(){
		return _assistants;
	}
	
}

