//var taskArray = new Array();
var taskArray = [];
var DATE_FORMAT = "DD/MM/YYYY";

var app = angular.module('myApp',['ngMaterial','ngMessages']);
app.controller('ctrl', function($scope, $http) {
	
	
	
	$scope.addTaskDatail = function() {
		  
		if (($scope.taskSubject != null && $scope.taskDetail != null) && ($scope.taskSubject != '' && $scope.taskDetail != '')) {
			$scope.hiddenValidTaskSubject = false;
			
			var startDate = moment($scope.startDate).format(DATE_FORMAT);
			var endDate = moment($scope.endDate).format(DATE_FORMAT)
			
			var jsonTask = {
				'startDate' : startDate,
				'endDate' : endDate,
				'subjectTask' : $scope.taskSubject,
				'subjectDetail' : $scope.taskDetail
			};
			
			taskArray.push(jsonTask);
			
			var jsonRequest = {
					'operationName' : "Add Task",
					'taskList' : taskArray
			};	
			
			$scope.loadingAddTask = true;
			$http.post("http://"+ IP_PORT +"/RESTfulBackend/rest/TaskService/CreateTask/", jsonRequest).success(function(data) {
				
				$scope.loadingAddTask = false;
				var statusCode = data.statusCode;
				var statusMessage = data.statusMessage;
				if (statusCode == 0) {
					alert("Add task success.");
					
					$scope.taskSubject = '';
					$scope.taskDetail = '';
					
					refreshTableTask();
				}
				else {
					alert(statusMessage);
				}

		    }).error(function(error) {
		    	$scope.loadingAddTask = false;
		    	alert(error);
		    });
		}
		else {
			$scope.hiddenValidTaskSubject = 'true';
		}
	};
	
	function refreshTableTask() {
		
		$http.post("http://"+ IP_PORT +"/RESTfulBackend/rest/TaskService/AllTask").success(function(data) {
			var statusCode = data.statusCode;
			var statusMessage = data.statusMessage;
			if (statusCode == 0) {
				$scope.tasksDetailTable = data.taskList;
			}
			else {
				alert(statusMessage);
			}

	    }).error(function(error) {
	    	alert(error);
	    });
	}

	$scope.confirmSave = function() {
		
		var r = confirm("Confirm Save To Database.");
		if (r == true) {
			
		} 
	}
	
	$scope.initOnload = function () {
		$http.post("http://"+ IP_PORT +"/RESTfulBackend/rest/TaskService/AllTask/").success(function(data) {
			var statusCode = data.statusCode;
			var statusMessage = data.statusMessage;
			if (statusCode == 0) {
				$scope.tasksDetailTable = data.taskList;
			}
			else {
				alert(statusMessage);
			}

	    }).error(function(error) {
	    	alert(error);
	    });
	}
	
	$scope.updateStatusTask = function (objJson, objLoading) {
		
		var img = document.getElementById(objLoading);
		img.style.visibility = 'visible';
		
		var jsonRequest = {
				'operationName' : "Update Task Status",
				'taskList' : objJson
		};	
		
		$http.post("http://"+ IP_PORT +"/RESTfulBackend/rest/TaskService/UpdateTaskStatus/", jsonRequest).success(function(data) {
			var statusCode = data.statusCode;
			var statusMessage = data.statusMessage;
			if (statusCode == 0) {
				refreshTableTask();
			}
			else {
				alert(statusMessage);
			}

			//$scope.objLoading = false;
	    }).error(function(error) {
	    	//$scope.objLoading = false;
	    	alert(error);
	    });
	}
	
	$scope.deleteTask = function (objJson) {
		
		var r = confirm("Confirm, Do you want to delete.");
		if (r == true) {
			var jsonRequest = {
					'operationName' : "Delete Task",
					'taskList' : objJson
			};	
			
			$http.post("http://"+ IP_PORT +"/RESTfulBackend/rest/TaskService/DeleteTask/", jsonRequest).success(function(data) {
				var statusCode = data.statusCode;
				var statusMessage = data.statusMessage;
				if (statusCode == 0) {
					refreshTableTask();
				}
				else {
					alert(statusMessage);
				}

		    }).error(function(error) {
		    	alert(error);
		    });
		} 
	}
	
	$scope.deleteAllTask = function () {
		var r = confirm("Confirm, Do you want to delete all task.");
		if (r == true) {
			$http.get("http://"+ IP_PORT +"/RESTfulBackend/rest/TaskService/DeleteAllTask/").success(function(data) {
				var statusCode = data.statusCode;
				var statusMessage = data.statusMessage;
				if (statusCode == 0) {
					refreshTableTask();
				}
				else {
					alert(statusMessage);
				}

		    }).error(function(error) {
		    	alert(error);
		    });
		} 
	}
	
	$scope.editTask = function (idTask) {
		window.location.href = "initialTask.action?redirectPage=editTask&idTask=" + idTask;
	}
	
	$scope.initOnloadTaskByID = function (idTask) {
		
		$http.get("http://"+ IP_PORT +"/RESTfulBackend/rest/TaskService/GetTaskByID?idTask="+idTask).success(function(data) {
			var statusCode = data.statusCode;
			var statusMessage = data.statusMessage;
			if (statusCode == 0) {
				var jsonObj = data.taskList[0];
				$scope.taskSubject = jsonObj.subjectTask;
				$scope.taskDetail = jsonObj.subjectDetail;
				
				if (jsonObj.startDate != null && '' != jsonObj.startDate) {
					$scope.startDate = new Date(jsonObj.startDate);
				}
				else {
					$scope.startDate = new Date();
				}
				
				if (jsonObj.endDate != null && '' != jsonObj.endDate) {
					$scope.endDate = new Date(jsonObj.endDate);
				}
				else {
					$scope.endDate = new Date();
				}
				
			}
			else {
				alert(statusMessage);
			}

	    }).error(function(error) {
	    	alert(error);
	    });
	}
	
	$scope.updateTask = function (idTask) {
		
		var startDate = moment($scope.startDate).format(DATE_FORMAT);
		var endDate = moment($scope.endDate).format(DATE_FORMAT)
		
		var jsonTask = {
				'startDate' : startDate,
				'endDate' : endDate,
				'idTask' : idTask,
				'subjectDetail' : $scope.taskDetail
			};
			
			taskArray.push(jsonTask);
			
			var jsonRequest = {
					'operationName' : "Update Edit Task",
					'taskList' : taskArray
			};	
			
			$http.post("http://"+ IP_PORT +"/RESTfulBackend/rest/TaskService/UpdateTask/", jsonRequest).success(function(data) {
				var statusCode = data.statusCode;
				var statusMessage = data.statusMessage;
				if (statusCode == 0) {
					alert("Edit task success.");
					window.location.href = "initialTask.action";
				}
				else {
					alert(statusMessage);
				}

		    }).error(function(error) {
		    	alert(error);
		    });
	}
	
});



function successCallback(data) {
	akert(data);
	
}

function errorCallback(data) {
	akert(data);
	
}

function addTaskDatail() {

}
