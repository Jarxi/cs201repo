<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	#centerTd{
		position:absolute;
		margin-left:180px;
	}
	#rightTd{
		position:fixed;
		margin-left:1030px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lectures</title>
<link rel="stylesheet" href="style2.css">
<script>  
var xmlHttpReq;  

function createXmlHttpRequest()  {  
    if(window.XMLHttpRequest)  {  
       xmlHttpReq = new XMLHttpRequest();//not ie
    }
    else{  
       xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");//ie 
    }  
}  

function trim(str){ 
	str=str.replace(/(^\s*)|(\s*$)/g, "");
	return str;
}

function capitalize(str){
	str = trim(str).toLowerCase();
	str = str.substring(0,1).toUpperCase()+str.substring(1,str.length);
	return str;
}

function  getElementsByClassName (name, tagname){  
    var arr = [];      
    var arr2 = document.getElementsByTagName(tagname);
    /* document.write(arr2.length); */
    for  ( var i = 0; i < arr2.length; i++ ){ 
        if(arr2[i].className==name){ 
            arr[arr.length ] = arr2[i];
        }
    } 
    return  arr;
}

function show(str){
	var value = document.getElementById(str).value;  
	if(trim(value)==""){  
		alert("Please enter something");  
		return false;  
	}
	else{
		var arr = getElementsByClassName(capitalize(value),'td');
		createXmlHttpRequest();  
		xmlHttpReq.onreadystatechange=func;  
		var url = "lectures.jsp";  
		xmlHttpReq.open("get",url,true);  
		xmlHttpReq.send(null);  
		
		function func(){
		    if(xmlHttpReq.readyState==4)  {   
		       if(xmlHttpReq.status==200){  
		    	   var res='';
		    	   var total='';
		    	   		for(var i=0; i<arr.length; i++){
		    	   			var result = arr[i];  
		 		        var tr = result.parentNode;
		 		        res =res +'<tr>'+ tr.innerHTML+'</tr>';  
		 		        total= tr.parentNode;
		    	   		}
		    	   	total.innerHTML = res;  
		       }  
		    }  
		}
	}  
}

function showTopic(str){
	var value = document.getElementById(str).value;  
	if(trim(value)==""){  
		alert("Please enter something");  
		return false;  
	}
	else{
		createXmlHttpRequest();  
		xmlHttpReq.onreadystatechange=func;  
		var url = "lectures.jsp";  
		xmlHttpReq.open("get",url,true);  
		xmlHttpReq.send(null);  
		
		function func(){
		    if(xmlHttpReq.readyState==4)  {   
		       if(xmlHttpReq.status==200){  
		    	   value = capitalize(value);
		    	   var node = document.getElementById(value);
		   	   var parent = node.parentNode.parentNode;
		   	   var grandParent = parent.parentNode;
		   	   grandParent.innerHTML = '<tr>'+parent.innerHTML + '</tr>';
		       }  
		    }  
		}
	}  
}
function showDueDate(){
	createXmlHttpRequest();  
	xmlHttpReq.onreadystatechange=func;  
	var url = "assignments.jsp";  
	xmlHttpReq.open("get",url,true);  
	xmlHttpReq.send(null);  
	var arr = this.getElementsByClassName('due', 'td');
	document.write(xmlHttpReq.document.getElementById("due"));
	function func(){
	    if(xmlHttpReq.readyState==4){   
	       if(xmlHttpReq.status==200){ 
	    	   var res='';
	    	   var total='';
	    	   		for(var i=0; i<arr.length; i++){
	    	   			var result = arr[i];  
	 		        var tr = result.parentNode;
	 		        res =res +'<tr>'+ tr.innerHTML+'</tr>';  
	 		        total= tr.parentNode;
	    	   		}
	    	   	this.getElementById("centerCol").innerHTML = res;  
	       }  
	    }  
	}
}
function showExamDate(){
	
}
function showFinalDate(){
	
}
</script>  
</head>
<body text = "#333333" bgcolor = "#EEEEEE">
 
 
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="parseData.*" %>
<%@ page import="parseData.School" %>
<%! 
  parseData pd = new parseData();
%>
<div>
   <%
   		//school
		School school=(School)request.getSession().getAttribute("school");
		//course
		Course course = school.getDepartment().get(0).getCourses().get(0);
		//department
		Department department =school.getDepartment().get(0);
   		
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
   		//lecture
   		ArrayList<MainLecture> lectureList= course.getLectures();
   		//TextBook
   		ArrayList<Textbook> textBookList = course.getLectures().get(0).getTextbook();
  		
   	%>
</div>
<table>
	<tbody>
		<tr>
			<!-- column for left side of page -->
			<td id="leftTd" width="180" valign="top" align="right">
				<a href= "http://www.usc.edu">
 					<img id="uscImage" src=<%=school.getImage() %> border="0">
 					</a>
 					<br>
 					<br>
 					<font size="+1">
 						<a href="http://cs.usc.edu"><%=department.getPrefix()+" Department"%></a>
 					</font>
 					<br>
 					<br>
 					<font size="+1"><a href="test.jsp"><%=department.getPrefix()+" "+course.getNumber()+ " Home" %></a></font>
 					<br>
 					<br>
 					<font size="+1"><a href=<%=course.getSyllabus() %>>Syllabus</a></font>
 					<br>
 					<br>
 					<font size="+1">Lectures</font>
 					<br>
 					<br>
 					<font size="+1"><a href="assignments.jsp">Assignments</font>
 					<br>
 					<br>
 					<font size="+1"><a href="exams.jsp">Previous Exams</font>
 					<br>
 					<br>
 				
			</td>
			<!-- center column to sep-arate other two columns -->			
			<td width="20"><br></td>
			<!-- large column in center of page with dominant centent -->
			<td id="centerTd" align = "baseline" width = "850">
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
 					
 					<%for (int i =0; i<textBookList.size();i++){ %>
 					
 					<b>
 						Chapter references are from <%=textBookList.get(i).getAuthor() %>
 						<u>
 						<%=textBookList.get(i).getTitle() %>,
 						</u>
 						<%=textBookList.get(i).getPublisher() %>,<%=textBookList.get(i).getYear() %>,
 						<%=textBookList.get(i).getIsbn() %>
 					</b>
 					<%} %>
 					
 					<p></p>
 					<hr size = "4">
 					<p></p>
 					<b>Lectures</b>
 					
				<table id="centerCol" border="2" cellpadding="5" width="830">
					<tbody>
						<tr>
							<th align="center">Week</th>
							<th align="center">Lab</th>
							<th align="center">Lecture</th>
							<th align="center">Day</th>
							<th align="center" width="40">Date</th>
							<th align="center">Lecture Topic</th>
							<th align="center">Chapter</th>
							<th>Program</th>
						</tr>
					</tbody>
					<tbody>
						<%
						int lectureLength = course.getLectures().size();
						for (int i=0; i<course.getLectures().size();i++){ 
							ArrayList<MainLab> labList = lectureList.get(i).getLabs();
							int labLength=0;
							if(labList!=null){
								labLength = labList.size();
							}
							ArrayList<Topic> topicList = lectureList.get(i).getTopic();
							int topicLength=0;
							if(topicList!=null){
								topicLength = topicList.size();
							}
						%>
							<tr >
								<td align="center"><%=lectureList.get(i).getWeek() %></td>
								<!-- lab -->
								<td align="center">
								<%	if(labLength!=0){
								for (int j=0; j<labLength-1;j++){ %>
									<a href=<%=labList.get(j).getUrl() %>><%=labList.get(j).getTitle() %></a>
									<hr>
								<%} %>
									<a href=<%=labList.get(labLength-1).getUrl() %>><%=labList.get(labLength-1).getTitle() %></a>
								<%} %>
								</td align="center">
								<!-- lecture number -->
								<td align="center"><%=lectureList.get(i).getNumber() %></td>
								<td align="center" class= <%=lectureList.get(i).getDay() %>><%=lectureList.get(i).getDay() %></td>
								<td align="center"class= <%=lectureList.get(i).getDate() %>><%=lectureList.get(i).getDate() %></td>
								
								
								 <!-- topic -->
								 <%if(!topicList.get(topicLength-1).getUrl().equals("")){ %>
								<td align="center">
								<%	if(topicLength!=0){
								for (int j=0; j<topicLength-1;j++){ %>
									<a id=<%=topicList.get(j).getTitle() %> class=<%=i%> href=<%=topicList.get(j).getUrl() %>><%=topicList.get(j).getTitle() %></a>
									<hr>
								<%} if(!topicList.get(topicLength-1).getUrl().equals("")){%>
									<a id=<%=topicList.get(topicLength-1).getTitle() %> class=<%=i%> href=<%=topicList.get(topicLength-1).getUrl() %>><%=topicList.get(topicLength-1).getTitle() %></a>
								<%}else{%>
									<a id=<%=topicList.get(topicLength-1).getTitle() %> class=<%=i%>><%=topicList.get(topicLength-1).getTitle() %></a>
								<%} %>
								<%} %>
								<%}else{ %>
									<td align="center" colspan="3">
								<%	if(topicLength!=0){
								for (int j=0; j<topicLength-1;j++){ %>
									<a id=<%=topicList.get(j).getTitle() %> class=<%=i%> href=<%=topicList.get(j).getUrl() %>><%=topicList.get(j).getTitle() %></a>
									<hr>
								<%} if(!topicList.get(topicLength-1).getUrl().equals("")){%>
									<a id=<%=topicList.get(topicLength-1).getTitle() %> class=<%=i%> href=<%=topicList.get(topicLength-1).getUrl() %>><%=topicList.get(topicLength-1).getTitle() %></a>
								<%}else{%>
									<a id=<%=topicList.get(topicLength-1).getTitle() %> class=<%=i%>><%=topicList.get(topicLength-1).getTitle() %></a>
								<%} %>
								<%} %>
								<%} %>
								</td> 
								
								<!-- chapter -->
								<% if(!topicList.get(topicLength-1).getUrl().equals("")){%>
								<td align="center">
								<%	if(topicLength!=0){
								for (int j=0; j<topicLength-2;j++){ %>
									
									<%if(j==0&& !topicList.get(0).getChapter().equals("")){%>
										<%=topicList.get(0).getChapter() %>,
									<% }else if(!topicList.get(j+1).getChapter().equals(topicList.get(j).getChapter())){ %>
									<%=topicList.get(j+1).getChapter() %>,
									<%} %>
								<%} %>
									<%=topicList.get(topicLength-1).getChapter() %>
								<%} %>
								</td>
								<%} %>
								<!-- program -->
								<% if(!topicList.get(topicLength-1).getUrl().equals("")){%>
								<td align="center">
								<%	if(topicLength!=0){
								for (int j=0; j<topicLength;j++){ 
									int fileLength = 0;
									if(topicList.get(j).getFiles()!=null){
										fileLength = topicList.get(j).getFiles().size();
									}
									for (int k=0; k<fileLength;k++){
								%>
									<a href=<%=topicList.get(j).getFiles().get(k).getURL() %>><%=topicList.get(j).getFiles().get(k).getTitle() %></a>
									<br>
								<%} if(fileLength!=0&&j!=topicLength-1){%>
									<hr>
								<%}} %>
								
								<%} %>
								</td> 
								<%} %>
							</tr>	
						<%} %>
					</tbody>
				</table>
			</td><!-- right td -->
			<td width="40"></td>
			<td id="rightTd" width="300"  align="left" valign="top">
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			
			<!-- Day:   -->
			<input type="text" id="day" Placeholder="Day.." >  <br>
			<button onclick="show('day')"><span>Day Filter</span> </button> <br>
			
			<!-- Date: -->	
			<input type="text" id="date" Placeholder="Date..">  <br>
			<button onclick="show('date')"><span>Date Filter</span> </button><br>
			
			<!-- Topic:	 -->
			<input type="text" id="topic" Placeholder="Topic..">  <br>
			<button  onclick="showTopic('topic')"><span>Topic Filter</span> </button><br>
			<br>
			
			<!-- Assignments Due Dates: -->
			<button class="style2" onclick="showDueDate()"><span>Due Dates Filter</span> </button><br>
			
			<!-- Exams Dates: -->
			<button class="style2" onclick="showExamDate()"><span>Exam Dates Filter</span> </button><br>
			
			<!-- Final Project Dates: -->
			<button class="style2" onclick="showFinalDate()"><span>Final Dates Filter</span> </button><br>
			</td>
		</tr>
	</tbody>
</table>



</body>
</html>