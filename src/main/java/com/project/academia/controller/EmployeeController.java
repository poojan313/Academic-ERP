package com.project.academia.controller;

import com.project.academia.bean.Employee;
import com.project.academia.dao.EmpDep;
import com.project.academia.services.EmployeeService;

import javax.json.Json;
import javax.print.DocFlavor;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("employee")
public class  EmployeeController {
    EmployeeService employeeService = new EmployeeService();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginEmployee(Employee employee) throws URISyntaxException{
        Employee e = employeeService.verifyEmail(employee);
        if(e == null)
        {
            return Response.noContent().build();
        }
        return Response.ok().entity(e).build();
    }

    @GET
    @Path("/fetch")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchData(){
        List<EmpDep> employeeList = employeeService.getEmployees();
        if(employeeList == null)
        {
            return Response.noContent().build();
        }
        return Response.ok().entity(employeeList).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerEmployee(EmpDep empDep)
    {
        Employee employee = employeeService.registerEmployee(empDep);
        if(employee == null)
        {
            return Response.noContent().build();
        }
        return Response.ok().entity(employee).build();
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(EmpDep empDep){
        boolean updated = employeeService.employeeUpdate(empDep);
        if(updated){
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
    @POST
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response allEmployee(Employee employee) throws URISyntaxException{
        Employee e = employeeService.verifyAll(employee);
        System.out.println(e.getEmployee_id());
        if(e == null)
        {
            return Response.noContent().build();
        }
        return Response.ok().entity(e).build();
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response employeeDelete(Employee employee){
        boolean check = employeeService.employeeDelete(employee);
        if(check){
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
}
