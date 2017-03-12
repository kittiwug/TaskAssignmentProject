package com.ws.rs.restful.assign.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
			List<TaskM> taskList = null;
			HashMap<Long, TaskM> hashMap = FileDATUtil.readFileTask();
			if (hashMap != null && hashMap.size() > 0) {
				
				List<Entry<Long, TaskM>> list = new LinkedList<>(hashMap.entrySet());
			    Collections.sort(list, new Comparator<Object>() {
			        @SuppressWarnings("unchecked")
			        public int compare(Object o1, Object o2) {
			            return ((Comparable<Long>) ((Map.Entry<Long, TaskM>) (o1)).getKey()).compareTo(((Map.Entry<Long, TaskM>) (o2)).getKey());
			        }
			    });
			    
			    HashMap<Long, TaskM> result = new LinkedHashMap<>();
			    for (Iterator<Entry<Long, TaskM>> it = list.iterator(); it.hasNext();) {
			        Map.Entry<Long, TaskM> entry = (Map.Entry<Long, TaskM>) it.next();
			        result.put(entry.getKey(), entry.getValue());
			    }

				taskList = new ArrayList<>();
				for (Map.Entry<Long, TaskM> entry : result.entrySet()) {
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
		HashMap<Long, TaskM> hashMap = null;
		try {
			System.out.println("TaskBeanImpl > createTask");
			
			if (dataServiceRequest != null) { 
				List<TaskM> taskMs = dataServiceRequest.getTaskList();
				if (taskMs != null) {
					hashMap = FileDATUtil.readFileTask();
					if (hashMap == null) {
						hashMap = new HashMap<>();
					}
					
					System.out.println("TaskBeanImpl > createTask : sizeMap = " + hashMap.size());
					for (TaskM taskM2 : taskMs) {
						Calendar dateCurrent = Calendar.getInstance();
						long idTask = dateCurrent.getTimeInMillis();
						
						taskM2.setIdTask(Long.toString(idTask));
						taskM2.setDateCreate(sdf.format(new Date()));
						taskM2.setStatus(StatusTaskEnum.P.toString());
						hashMap.put(idTask, taskM2);
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
				HashMap<Long, TaskM> hashMap = FileDATUtil.readFileTask();
				
				List<TaskM> taskMs = dataServiceRequest.getTaskList();
				if (taskMs != null && taskMs.size() > 0) {
					for (TaskM taskM : taskMs) {
						String idTask = taskM.getIdTask();
						String subjectDetail = taskM.getSubjectDetail();
						
						if (idTask != null && !"".equals(idTask)) {
							if (hashMap != null) {
								TaskM taskM2 = hashMap.get(Long.parseLong(idTask));
								taskM2.setSubjectDetail(subjectDetail);
								taskM2.setStartDate(taskM.getStartDate());
								taskM2.setEndDate(taskM.getEndDate());
								taskM2.setDateUpdate(sdf.format(new Date()));
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
				HashMap<Long, TaskM> hashMap = FileDATUtil.readFileTask();
				
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
								TaskM taskM2 = hashMap.get(Long.parseLong(idTask));
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
				HashMap<Long, TaskM> hashMap = FileDATUtil.readFileTask();
				
				List<TaskM> taskMs = dataServiceRequest.getTaskList();
				if (taskMs != null && taskMs.size() > 0) {
					for (TaskM taskM : taskMs) {
						String idTask = taskM.getIdTask();
						if (idTask != null && !"".equals(idTask)) {
							if (hashMap != null) {
								hashMap.remove(Long.parseLong(idTask));
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
			HashMap<Long, TaskM> hashMap = FileDATUtil.readFileTask();
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
			HashMap<Long, TaskM> hashMap = FileDATUtil.readFileTask();
			if (hashMap != null && hashMap.size() > 0) {
				TaskM taskM = hashMap.get(Long.parseLong(idTask));
				if (taskM != null) {
					taskList = new ArrayList<>();
					taskList.add(taskM);
					
					dataServiceResponse.setTaskList(taskList);
					dataServiceResponse.setStatusCode(StatusService.SUCCESS.getCode());
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
}
