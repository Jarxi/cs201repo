<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>CSCI 201.jsp</title>
<style>
#centerTd{
	position:absolute;
	margin-left:200px;
}

@import url('https://fonts.googleapis.com/css?family=Montserrat');
</style>
<link rel="stylesheet" href="style2.css">

</head>
<body>

<%@ page import="java.io.*,java.util.*" %>
<%@ page import="parseData.*" %>
<%@ page import="parseData.School" %>
<%! 
  parseData pd = new parseData();
%>

   <% 	String filename = request.getParameter("name");
   		School school;
   		if(filename!=null){
   			filename="http://localhost:8080/tomcatTest1/"+filename;
   			ArrayList<School> schoolList = pd.parse(filename);
   			school=schoolList.get(0);
   		}
   		else{
   			school = (School)request.getSession().getAttribute("school");
   		}
		//school
		request.getSession().setAttribute("school",school);
		//course
   		Course course = school.getDepartment().get(0).getCourses().get(0);
   		//department
   		Department department = school.getDepartment().get(0);
   		
   		//header
   		String courseName = department.getPrefix()+" "+course.getNumber();
   		String title = course.getTitle();
   		String unit = course.getUnit()+" units";
   		String term = course.getTerm()+ " "+course.getYear();
   		
   		//meetings
   		ArrayList<Meeting> meetings = course.getMeetings();
   		int numMeeting = meetings.size();
   		
   		//staffMembers
   		ArrayList<StaffMember> staffMembers = course.getStaffMember();
   		int numStaffMember = staffMembers.size();
   		//instructor image
   		String instructorImage = "";
   		String instructorName = "";
   		String instructorOffice = "";
   		String instructorOfficeHour ="";
   		String instructorPhone = "";
   		String instructorEmail = "";
   		ArrayList<StaffMember> instructorList = new ArrayList<StaffMember>();
   %>
 

 	<table cellpadding = "10">
 		<tbody>
 			<tr>
 				<!-- leftTd -->
 			
 				<td id="leftTd" width = "240" align = "middle">
 					<a href= "http://www.usc.edu">
 						<img id="uscImage" src=<%=school.getImage() %> border="0">
 					</a>
 					
 					<br>
 					<br>
 					<div class="topnav" >
 					<font size="+1">
 						<a href="http://cs.usc.edu"><%=department.getPrefix()+" Department"%></a>
 					</font>
 					<br>
 					<br>
 					<font size="+1"><a><%=department.getPrefix()+" "+course.getNumber()+ " Home" %></a></font>
 					<br>
 					<br>
 					<font size="+1"><a href=<%=course.getSyllabus() %>>Syllabus</a></font>
 					<br>
 					<br>
 					<font size="+1"><a href="lectures.jsp">Lectures</a></font>
 					<br>
 					<br>
 					<font size="+1"><a href="assignments.jsp">Assignments</a></font>
 					<br>
 					<br>
 					<font size="+1"><a href="exams.jsp">Previous Exams</a></font>
 					<br>
 					<br>
 				
 				</div>
 				</td>
 				<td width = "30">
 					<br>
 				</td>
 				<!-- rightTd -->
 				
 				<td id="centerTd"align = "baseline" width = "615" >
 					<br>
 					<p>
 						<b>
 							<font size="+3"><% out.println(courseName); %></font>
 						</b>
 						<br>
 						<b>
 							<i>
 								<font size= "+1"><% out.println(title+" - "+unit); %></font>
 							</i>
 						</b>
 						<br>
 						<b>
 							<i>
 							
 								<font size="+1"><% out.println(term); %></font>
 							</i>
 						</b>
 					</p>
 					<p></p>
 					<hr size = "4"> 
 					<p></p>
 					<p></p>
 					<p>
 						<font size="-1">
 							<table border = "1">
 								<tbody>
 									<tr id = "instructorHeader">
 										<th align = "center">Picture</th>
 										<th align = "center">Professor</th>
 										<th align = "center">Office</th>
 										<th align = "center">Phone</th>
 										<th align = "center">Email</th>
 										<th align = "center">Office Hours</th>
 									</tr>
 									<% for (int i=0;i<staffMembers.size(); i++){ 
   										if(staffMembers.get(i).getType().equals("instructor")){
   										 	instructorImage = course.getStaffMember().get(i).getImage();
   						   				 	instructorName = course.getStaffMember().get(i).getName();
   						   				 	instructorOffice = course.getStaffMember().get(i).getOffice();
   						   				 	instructorOfficeHour = course.getStaffMember().get(i).getOfficeHour().get(0);
   						   				 	instructorPhone = course.getStaffMember().get(i).getPhoneNumber();
   						   				 	instructorEmail = course.getStaffMember().get(i).getEmail();
	   								%>
	   								<td>
		 							<img width = "100" height="100" src =<%=instructorImage %> > 
		 							</td>
		 							<td>
		 								<font size = '-1'><%=instructorName %></font>
		 							</td>
	   								<td>
		 								<font size = '-1'><%=instructorOffice %></font>
		 							</td>
	   								<td>
		 								<font size = '-1'><%=instructorPhone %></font>
		 							</td>
	   								<td>
		 								<font size = '-1'><%=instructorEmail %></font>
		 							</td>
		 							<td>
		 								<%for (int j=0; j<course.getStaffMember().get(i).getOfficeHour().size();j++){ %>
		 								<font size = '-1'><%=course.getStaffMember().get(i).getOfficeHour().get(j)%></font>
		 								<br>
		 								<%} %>
		 							</td>
	   								<%		}
	   									}%>
		 							
 								</tbody>
 							</table>
 							<br>
 						</font>
 					</p>
 					<hr size = "4">
 					<p></p>
 					<font size="-1">
 					<b>
 						<font size="+1">Lecture Schedule</font>
 					</b>
	 					<table border="2" cellpadding = "5" width="590">
	 						<tbody>
	 							<tr id="lectureHeader">
									<th  align = "center">Sect. No.</th>
		 							<th align = "center">Day & Time</th>
		 							<th align = "center">Room</th>
		 							<th>Lecture Assistant</th>
	 							</tr>				

 						<%for(int i=0;i<numMeeting;i++){
	 							if (!meetings.get(i).getType().equals("lab")){
	 					%>
							<tr>
							<td align="center">
								<font size="-1">
									<%=meetings.get(i).getSection() %>
								</font>
							</td>
							<td align="center">
								<font size="-1">
								<%if(!meetings.get(i).getMeetingDay().equals("TBA")){ %>
									<%=meetings.get(i).getMeetingDay()%>
									<br>
									<%=meetings.get(i).getMeetingTime()%>
								<%} 
								else{ %>
									TBD
								<%} %>
								</font>
							</td>
							<td align="center">
								<font size="-1">
									<%=meetings.get(i).getRoom()%>
								</font>
							</td>
							<td align = center>
								<table border="0">
									<tbody>
										<tr>
										<% for (int j=0; j< meetings.get(i).getAssistants().size();j++){ %>
											<td align = "center">
												<img src = <%=meetings.get(i).getAssistants().get(j).getImage() %>>
												<br>
												<a href = <%="http://www-scf.usc.edu/~csci201/"+meetings.get(i).getAssistants().get(j).getEmail() %>>
													<%=meetings.get(i).getAssistants().get(j).getName()%>
												</a>
											</td>
										<%} %>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					<%}}%>	

 					
			 				</td>
			 			</tr>
			 		</tbody>
			 	</table>
		</font>
				<p></p>
				<hr size="4">
				<p></p>
				<font size="-1">
					<b>
 						<font size="+1">Lab Schedule</font>
 					</b>
	 					<table border="2" cellpadding = "5" width="590">
	 						<tbody>
	 							<tr id="lectureHeader">
									<th  align = "center">Sect. No.</th>
		 							<th align = "center">Day & Time</th>
		 							<th align = "center">Room</th>
		 							<th>Lecture Assistant</th>
	 							</tr>				

 						<%for(int i=0;i<numMeeting;i++){
	 							if (meetings.get(i).getType().equals("lab")){
	 					%>
							<tr>
							<td align="center">
								<font size="-1">
									<%=meetings.get(i).getSection() %>
								</font>
							</td>
							<td align="center">
								<font>
								<%if(!meetings.get(i).getMeetingDay().equals("TBA")){ %>
									<font size="-1"><%=meetings.get(i).getMeetingDay()%></font>
									<br>
									<font size="-1"><%=meetings.get(i).getMeetingTime()%></font>
								<%} 
								else{ %>
									<font size="-1">TBD</font>
								<%} %>
								</font>
							</td>
							<td align="center">
								<font size="-1">
									<%=meetings.get(i).getRoom()%>
								</font>
							</td>
							<td align = center>
								<table border="0">
									<tbody>
										<tr>
										<% for (int j=0; j< meetings.get(i).getAssistants().size();j++){ %>
											<td align = "center">
												<img src = <%=meetings.get(i).getAssistants().get(j).getImage() %>>
												<br>
												<a href = <%="http://www-scf.usc.edu/~csci201/"+meetings.get(i).getAssistants().get(j).getEmail() %>>
													<%=meetings.get(i).getAssistants().get(j).getName()%>
												</a>
											</td>
										<%} %>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					<%}}%>			
	 				</td>
	 			</tr>
	 		</tbody>
	 	</table>
</font>
<br>
<hr size = "4">
<br>
				<!-- Staff Office Hours -->
				<b>
					<font size="+1">Staff Office Hours</font>
				</b>
				<br>
				<table border="1">
					<tbody>
						<%for (int i=0; i<staffMembers.size();i++){ %>
						<tr>
							<% if(!staffMembers.get(i).getType().equals("instructor")){ %>
							<td><font size="-1"><%= staffMembers.get(i).getFullType() %></font></td>
							<td><img src=<%=staffMembers.get(i).getImage() %>></td>
							<td><font size="-1"><a href=<%=staffMembers.get(i).getEmail() %> > 
							<%=staffMembers.get(i).getName() %></a></font></td>				
							<td><font size="-1">			
							<%for (int j=0; j<course.getStaffMember().get(i).getOfficeHour().size();j++){ %>
		 					<%=course.getStaffMember().get(i).getOfficeHour().get(j)%><br>
		 					<%} %>
		 					</font>
		 					</td>
							<td><font size="-1"><%=staffMembers.get(i).getOffice() %></font></td>
							<%} %>
						</tr>
						<%} %>
					</tbody>
				</table>
				
				
<br>
<hr size = "4">
<br>
				<!-- Teaching Staff -->
				<b>
					<font size="+1">Teaching Staff</font>
				</b>
				<br>
				<table border="1">
					<tbody>
						<tr>
							<th align="center">Picture</th>
							<th align="center">Name</th>
							<th align="center">Email</th>
						</tr>
						<%for (int i=0; i<staffMembers.size();i++){ %>
						<tr>
							
							<% if(!staffMembers.get(i).getType().equals("instructor")){ %>
							<td><img src=<%=staffMembers.get(i).getImage() %>></td>
							<% if(!staffMembers.get(i).getType().equals("ta")){ %>
							<td><font size="-1"><%=staffMembers.get(i).getName() %></font></td>
							<%}else{%>
								<td><font size="-1"><%=staffMembers.get(i).getName()+"(TA)"%></font></td>
							<%} %>
							<td><font size="-1"><a href=<%=staffMembers.get(i).getEmail() %> > 
							<%=staffMembers.get(i).getEmail() %></a></font></td>				
							<%} %>
							
						</tr>
						<%} %>
					</tbody>
				</table>
			</td><!-- right td -->
			</tr>
		</tbody>
	</table>
	<br>
	<br>
	<br>

</body>
</html>