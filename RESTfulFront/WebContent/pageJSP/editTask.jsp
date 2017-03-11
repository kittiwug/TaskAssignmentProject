<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

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

</head>

<body>
	<div ng-app="myApp" ng-controller="ctrl"
		ng-init="initOnloadTaskByID('<s:property value="idTask"/>')">

		<div style="position: none; display: block; background-color:"
			align="center">
			<h2>Edit Task Assignment</h2>

			<div style="width: 90%;">
				<table style="width: 70%">
					<tr>
						<td width="20%" align="right"><span>Task Subject :</span></td>
						<td width="80%" align="left">
							<input type="text" ng-model="taskSubject" style="width: 70%; background-color: #cccccc;" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td align="right"><span>Task Detail :</span></td>
						<td align="left">
							<textarea rows="" cols="" style="resize: none; width: 70%; height: 50px;" ng-model="taskDetail"></textarea></td>
					</tr>
					<tr>
						<td></td>
						<td align="left"><span>Start Date :</span> 
							<md-datepicker ng-model="startDate" md-placeholder="Enter date"></md-datepicker>
						</td>
					</tr>
					<tr>
						<td></td>
						<td align="left"><span>End Date :</span>&nbsp;<md-datepicker
								ng-model="endDate" md-placeholder="Enter date"></md-datepicker>
						</td>
					</tr>
					<tr>
						<td></td>
						<td align="left"></br> &nbsp;
							<button class="buttonAddTask" ng-click="updateTask('<s:property value="idTask"/>')">&nbsp;&nbsp;&nbsp;Update
								Task&nbsp;&nbsp;&nbsp;&nbsp;</button></td>
					</tr>
				</table>


				</br> <a href="initialTask.action">Back</a>

			</div>



		</div>
	</div>
</body>
</html>