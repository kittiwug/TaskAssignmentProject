package com.ws.rs.restful.assign.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ws.rs.restful.assign.constant.StatusService;
import com.ws.rs.restful.assign.constant.StatusTaskEnum;
import com.ws.rs.restful.assign.model.DataServiceRequest;
import com.ws.rs.restful.assign.model.DataServiceResponse;
import com.ws.rs.restful.assign.model.TaskM;
import com.ws.rs.restful.assign.util.FileDATUtil;

public class TaskBeanImpl {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public DataServiceResponse getAllTask() throws Throwable {
		
		DataServiceResponse dataServiceResponse = new DataServiceResponse();
		try {
			System.out.println(">> getAllTask");
			
			List<TaskM> taskList = null;
			HashMap<String, TaskM> hashMap = FileDATUtil.readFileTask();
			if (hashMap != null && hashMap.size() > 0) {
				taskList = new ArrayList<>();
				for (Map.Entry<String, TaskM> entry : hashMap.entrySet()) {
					System.out.println("TaskM key = " + entry.getKey());
					
				    TaskM taskM = entry.getValue();
				    taskList.add(taskM);
				}
			}
			
			dataServiceResponse.setTaskList(taskList);
			dataServiceResponse.setStatusCode(StatusService.SUCCESS.getCode());
		} 
		catch (Throwable t) {
			dataServiceResponse.setStatusMessage(t.getMessage());
			dataServiceResponse.setStatusCode(StatusService.ERROR.getCode());
			t.printStackTrace();
		}
		
		return dataServiceResponse;
	}

	public DataServiceResponse createTask(DataServiceRequest dataServiceRequest) throws Throwable {

		DataServiceResponse dataServiceResponse = new DataServiceResponse();
		HashMap<String, TaskM> hashMap = null;
		try {
			if (dataServiceRequest != null) { 
				System.out.println("OperationName = " + dataServiceRequest.getOperationName());
				List<TaskM> taskMs = dataServiceRequest.getTaskList();
				if (taskMs != null) {
					System.out.println("Task List size = " + taskMs.size());
					
					hashMap = FileDATUtil.readFileTask();
					if (hashMap == null) {
						hashMap = new HashMap<>();
					}
					
					for (TaskM taskM2 : taskMs) {
						UUID id = UUID.randomUUID();
						taskM2.setIdTask(id.toString());
						taskM2.setDateCreate(sdf.format(new Date()));
						taskM2.setStatus(StatusTaskEnum.P.toString());
						hashMap.put(id.toString(), taskM2);
					}
					
					FileDATUtil.saveFileTask(hashMap);
					dataServiceResponse.setStatusCode(StatusService.SUCCESS.getCode());
				}
				else {
					dataServiceResponse.setStatusMessage("Method [createTask] taskMs is null.");
					dataServiceResponse.setStatusCode(StatusService.ERROR.getCode());
				}
			}
		} 
		catch (Throwable t) {
			dataServiceResponse.setStatusMessage(t.getMessage());
			dataServiceResponse.setStatusCode(StatusService.ERROR.getCode());
			t.printStackTrace();
		}
		
		return dataServiceResponse;
	}

	public DataServiceResponse updateTask(DataServiceRequest dataServiceRequest) {
		
		DataServiceResponse dataServiceResponse = new DataServiceResponse();
		try {
			if (dataServiceRequest != null) {
				System.out.println("Method : updateTask, " + dataServiceRequest.getOperationName());
				
				HashMap<String, TaskM> hashMap = FileDATUtil.readFileTask();
				
				List<TaskM> taskMs = dataServiceRequest.getTaskList();
				if (taskMs != null && taskMs.size() > 0) {
					for (TaskM taskM : taskMs) {
						String idTask = taskM.getIdTask();
						String subjectDetail = taskM.getSubjectDetail();
						
						if (idTask != null && !"".equals(idTask)) {
							if (hashMap != null) {
								TaskM taskM2 = hashMap.get(idTask);
								taskM2.setSubjectDetail(subjectDetail);
								taskM2.setStartDate(taskM.getStartDate());
								taskM2.setEndDate(taskM.getEndDate());
							}
							
							FileDATUtil.saveFileTask(hashMap);
							dataServiceResponse.setStatusCode(StatusService.SUCCESS.getCode());
						}
						else {
							dataServiceResponse.setStatusMessage("Method [updateTask] idTask is null.");
							dataServiceResponse.setStatusCode(StatusService.ERROR.getCode());
						}
					}
					
				}
			}
		} 
		catch (Throwable t) {
			dataServiceResponse.setStatusMessage(t.getMessage());
			dataServiceResponse.setStatusCode(StatusService.ERROR.getCode());
			t.printStackTrace();
		}
		
		return dataServiceResponse;
	}

	public DataServiceResponse updateTaskStatus(DataServiceRequest dataServiceRequest) {

		DataServiceResponse dataServiceResponse = new DataServiceResponse();
		try {
			if (dataServiceRequest != null) {
				System.out.println(dataServiceRequest.getOperationName());
				
				HashMap<String, TaskM> hashMap = FileDATUtil.readFileTask();
				
				List<TaskM> taskMs = dataServiceRequest.getTaskList();
				if (taskMs != null && taskMs.size() > 0) {
					for (TaskM taskM : taskMs) {
						String idTask = taskM.getIdTask();
						String status = taskM.getStatus();
						String statusNew = null;
						
						if (idTask != null && !"".equals(idTask)) {
							if (StatusTaskEnum.D.toString().equalsIgnoreCase(status)) {
								statusNew = StatusTaskEnum.P.toString();
							}
							else {
								statusNew = StatusTaskEnum.D.toString();
							}
							
							if (hashMap != null) {
								TaskM taskM2 = hashMap.get(idTask);
								taskM2.setStatus(statusNew);
							}
							
							dataServiceResponse.setStatusCode(StatusService.SUCCESS.getCode());
						}
						else {
							dataServiceResponse.setStatusMessage("Method [updateTaskStatus] idTask is null.");
							dataServiceResponse.setStatusCode(StatusService.ERROR.getCode());
						}
					}
					
					FileDATUtil.saveFileTask(hashMap);
				}
			}
		} 
		catch (Throwable t) {
			dataServiceResponse.setStatusMessage(t.getMessage());
			dataServiceResponse.setStatusCode(StatusService.ERROR.getCode());
			t.printStackTrace();
		}
		
		return dataServiceResponse;
	
	}

	public DataServiceResponse deleteTask(DataServiceRequest dataServiceRequest) {

		DataServiceResponse dataServiceResponse = new DataServiceResponse();
		try {
			if (dataServiceRequest != null) {
				System.out.println(" > OperationName = " + dataServiceRequest.getOperationName());
				
				HashMap<String, TaskM> hashMap = FileDATUtil.readFileTask();
				
				List<TaskM> taskMs = dataServiceRequest.getTaskList();
				if (taskMs != null && taskMs.size() > 0) {
					for (TaskM taskM : taskMs) {
						String idTask = taskM.getIdTask();
						if (idTask != null && !"".equals(idTask)) {
							if (hashMap != null) {
								hashMap.remove(idTask);
							}
							dataServiceResponse.setStatusCode(StatusService.SUCCESS.getCode());
						}
						else {
							dataServiceResponse.setStatusCode(StatusService.ERROR.getCode());
							dataServiceResponse.setStatusMessage("Method [deleteTask] idTask is null");
						}
					}
					
					FileDATUtil.saveFileTask(hashMap);
				}
			}
		} 
		catch (Throwable t) {
			dataServiceResponse.setStatusMessage(t.getMessage());
			dataServiceResponse.setStatusCode(StatusService.ERROR.getCode());
			t.printStackTrace();
		}
		
		return dataServiceResponse;
	}

	public DataServiceResponse deleteAllTask() {

		DataServiceResponse dataServiceResponse = new DataServiceResponse();
		try {
			HashMap<String, TaskM> hashMap = FileDATUtil.readFileTask();
			if (hashMap != null) {
				hashMap.clear();
			}
				
			FileDATUtil.saveFileTask(hashMap);
		
			dataServiceResponse.setStatusCode(StatusService.SUCCESS.getCode());
		} 
		catch (Throwable t) {
			dataServiceResponse.setStatusMessage(t.getMessage());
			dataServiceResponse.setStatusCode(StatusService.ERROR.getCode());
			t.printStackTrace();
		}
		
		return dataServiceResponse;
	}

	public DataServiceResponse getTaskByID(String idTask) {
		DataServiceResponse dataServiceResponse = new DataServiceResponse();
		try {
			List<TaskM> taskList = null;
			HashMap<String, TaskM> hashMap = FileDATUtil.readFileTask();
			if (hashMap != null && hashMap.size() > 0) {
				if (hashMap.containsKey(idTask)) {
					TaskM taskM = hashMap.get(idTask);
					if (taskM != null) {
						taskList = new ArrayList<>();
						taskList.add(taskM);
					}
					dataServiceResponse.setTaskList(taskList);
					dataServiceResponse.setStatusCode(StatusService.SUCCESS.getCode());
				}
				else {
					dataServiceResponse.setStatusCode(StatusService.ERROR.getCode());
					dataServiceResponse.setStatusMessage("Method [getTaskByID] idTask is null.");
				}
			}
			
			
		} 
		catch (Throwable t) {
			dataServiceResponse.setStatusMessage(t.getMessage());
			dataServiceResponse.setStatusCode(StatusService.ERROR.getCode());
			t.printStackTrace();
		}
		
		return dataServiceResponse;
	}
	
	//Id = a379df1e-4060-47ef-9cc4-892bf9cdd4c5
	
	public static void main(String[] args) {
		TaskBeanImpl taskBeanImpl = new TaskBeanImpl();
		taskBeanImpl.getTaskByID("a379df1e-4060-47ef-9cc4-892bf9cdd4c5");
	}

}
