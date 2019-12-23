package com.example.employeeapi.API;

import com.example.employeeapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface employeeAPI {

   @GET("employees")
    Call<List<Employee>>getAllEmployee();

}
