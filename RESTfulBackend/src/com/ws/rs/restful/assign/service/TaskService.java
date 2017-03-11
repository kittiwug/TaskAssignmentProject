package com.ws.rs.restful.assign.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ws.rs.restful.assign.bean.TaskBeanImpl;
import com.ws.rs.restful.assign.model.DataServiceRequest;
import com.ws.rs.restful.assign.model.DataServiceResponse;


@Path("/TaskService")
public class TaskService {

	TaskBeanImpl taskBeanImpl = new TaskBeanImpl();
	
	@POST
	@Path("/AllTask")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DataServiceResponse getAllTask() throws Throwable {
		return taskBeanImpl.getAllTask();
	}
	
	@POST
	@Path("/CreateTask")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DataServiceResponse createTask(DataServiceRequest dataServiceRequest) throws Throwable {
		return taskBeanImpl.createTask(dataServiceRequest);
	}
	
	@POST
	@Path("/UpdateTask")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DataServiceResponse updateTask(DataServiceRequest dataServiceRequest) throws Throwable {
		return taskBeanImpl.updateTask(dataServiceRequest);
	}
	
	@POST
	@Path("/UpdateTaskStatus")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DataServiceResponse updateTaskStatus(DataServiceRequest dataServiceRequest) throws Throwable {
		return taskBeanImpl.updateTaskStatus(dataServiceRequest);
	}
	
	@POST
	@Path("/DeleteTask")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DataServiceResponse deleteTask(DataServiceRequest dataServiceRequest) throws Throwable {
		return taskBeanImpl.deleteTask(dataServiceRequest);
	}
	
	@GET
	@Path("/DeleteAllTask")
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	public DataServiceResponse deleteAllTask() throws Throwable {
		return taskBeanImpl.deleteAllTask();
	}
	
	@GET
	@Path("/GetTaskByID")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DataServiceResponse getTaskByID(@QueryParam("idTask")String idTask) throws Throwable {
		return taskBeanImpl.getTaskByID(idTask);
	}
	
}
