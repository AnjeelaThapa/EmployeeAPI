package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeeapi.API.employeeAPI;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.url.BaseURL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
        .build();

        employeeAPI employeeapi = retrofit.create(employeeAPI.class);
        Call<List<Employee>> listCall = employeeapi.getAllEmployee();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Error" +response.code(), Toast.LENGTH_SHORT).show();
                            return;
                }
                List<Employee> employeeList = response.body();
                for (Employee employee : employeeList) {
                    String data="";
                    data += "Employee name :" + employee.getEmployee_name() + "\n";
                    data += "Employee salary :" + employee.getEmployee_salary() + "\n";
                    data += "Employee age :" + employee.getEmployee_age() + "\n";
                    data +="-------------------" + "\n";
                    tvOutput.append(data);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                Log.d("Mero msg","OnFailure" + t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
