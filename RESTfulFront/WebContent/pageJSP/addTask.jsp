<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">

<title>Task Assignment</title>

	  <link rel="stylesheet" type="text/css" href="css/index.css">
	  <link rel="stylesheet" type="text/css" href="css/angular-material-min.css">
      <script src="js/angular/angular.min.js"></script>
      <script src="js/angular/angular-animate.min.js"></script>
      <script src="js/angular/angular-aria.min.js"></script>
      <script src="js/angular/angular-messages.min.js"></script>
      <script src="js/angular/angular-material.min.js"></script>
      <script src="js/config-resource.js"></script>
	  <script src="js/index.js"></script>
	  <script src="js/moment-with-locales.min.js"></script>
	  
<style type="text/css"></style>


</head>



<body>
<div ng-app="myApp" ng-controller="ctrl" ng-init="initOnload()"> 

	<div style="" align="center">
		<h1>Task Assignment Project</h1>

		<div style="width: 90%; background-color: #F0FCDD; height: 250px; padding-top: 20px;">
			<table style="width: 70%">
				<tr>
					<td width="20%" align="right"><span>Task Subject :</span></td>
					<td width="80%" align="left">
						<input type="text" ng-model="taskSubject" style="width: 70%; "/><span ng-show="hiddenValidTaskSubject" class="spanDataNotFound">&nbsp;Task subject is required!!</span>
					</td>
				</tr>
				<tr>
					<td align="right"><span>Task Detail :</span></td>
					<td align="left">
						<textarea rows="" cols="" style="resize: none; width: 70%; height: 50px" ng-model="taskDetail"></textarea>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="left">
					<span>Start Date :</span><md-datepicker  ng-model="startDate" md-placeholder="Enter date"></md-datepicker>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="left">
					<span>End Date :</span>&nbsp;<md-datepicker  ng-model="endDate" md-placeholder="Enter date"></md-datepicker>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="left" valign="top">
						&nbsp;<button class="buttonAddTask" ng-click="addTaskDatail()">&nbsp;&nbsp;&nbsp;Add Task&nbsp;&nbsp;&nbsp;&nbsp;</button>
						<img ng-show="loadingAddTask" src="images/loading.gif" alt="Delete Task" height="30" width="30" style="vertical-align: middle;">
					</td>
				</tr>
			</table>
		</div>

		<div style="width: 90%; background-color: ; height: 200px; padding-top: 20px;">
			<div style="text-align: right;">
				<button class="buttonDeleteAll" ng-click="deleteAllTask()">Delete All Task</button>
			</div>
			<table border="0" width="100%" cellpadding="0" cellspacing="1" class="tableTaskDetail">
					<tr>
						<th style="width: 3%" align="center">No.</th>
						<th style="width: 20%" align="center">Task</th>
						<th style="width: 40%" align="center">Task Detail</th>
						<th style="width: 10%" align="center">Start Date</th>
						<th style="width: 10%" align="center">End Date</th>
						<th style="width: 10%" align="center">Status</th>
						<th style="width:" align="center" colspan="2"></th>
					</tr>
					<tr ng-repeat="x in tasksDetailTable">
						<td align="center"><span>{{ $index+1 }}.</span></td>
						<td align="center"><span>{{ x.subjectTask }}</span></td>
						<td align="center">
							<textarea style="width: 99%; vertical-align: top; text-align: center; resize: none;" rows="2" cols="" disabled="disabled">{{ x.subjectDetail }}</textarea>
						</td>
						<td align="center"><span>{{ x.startDate }}</span></td>
						<td align="center"><span>{{ x.endDate }}</span></td>
						<td align="center">   
							<select ng-model="selectStatus" ng-change="updateStatusTask(x, 'loadingUpdateStatusTask{{$index}}')">
								<option value="P" ng-selected="x.status == 'P'">Pending</option>
								<option value="D" ng-selected="x.status == 'D'">Done</option>
							</select>
							<img  id="loadingUpdateStatusTask{{$index}}" src="images/loading.gif" alt="Delete Task" height="30" width="30" style="vertical-align: middle; visibility: hidden;">
						</td>
						<td align="center"><img src="images/delete.png" alt="Delete Task" height="20" width="20" ng-click="deleteTask(x)"></td>
						<td align="center"><img src="images/edit.png" alt="Edit Task" height="20" width="20" ng-click="editTask(x.idTask)"></td>
					</tr>
					<tr ng-if="tasksDetailTable == '' || tasksDetailTable == null">
					    <td colspan="8" align="center">
					    	<span class="spanDataNotFound">Data not found.</span>
					    </td>
					</tr>
				</table>
		</div>

	</div>
	
</div>	
</body>
</html>