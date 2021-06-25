package com.project.academia.controller;


import com.project.academia.bean.Department;
import com.project.academia.bean.Employee;
import com.project.academia.services.DepartmentService;
import com.project.academia.services.EmployeeService;
import org.json.simple.JSONObject;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.List;


@Path("department")
public class DepartmentController {

    DepartmentService departmentService = new DepartmentService();

    @GET
    @Path("/fetch")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchData()
    {
        List<String> d = departmentService.getDept();
        if(d == null)
        {
            return Response.noContent().build();
        }
        return Response.ok().entity(d).build();
    }

    @POST
    @Path("/count")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCount(Department department){
        boolean c = departmentService.departmentCount(department);
        if(c)
        {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @POST
    @Path("/name")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchDeptName(Employee employee) throws URISyntaxException {
        String name = departmentService.getName(employee);
        JSONObject object = new JSONObject();
        object.put("name",name);
        if(name == null)
        {
            return Response.noContent().build();
        }
        return Response.ok().entity(object).build();
    }
}
