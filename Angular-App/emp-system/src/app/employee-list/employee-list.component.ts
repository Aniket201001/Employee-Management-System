import { Component, OnInit } from '@angular/core';
//import { Employee } from '../employee';
//import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';
import { Addemp } from '../addemp';
import { AddempService } from '../addemp.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent implements OnInit{

  employees: Addemp[]=[];

  constructor(private employeeService: AddempService,private router:Router){}

  ngOnInit(): void {
    this.getEmployees();
  }

  private getEmployees(){
    this.employeeService.getEmployeeList().subscribe(data =>{
      this.employees=data
    });
  }

  employeeDetails(id: number){
    this.router.navigate(['view-employee', id]);
  }

  updateEmployee(id: number){
    this.router.navigate(['update-employee', id]);
  }

  deleteEmployee(id: number){
    this.employeeService.deleteEmployee(id).subscribe( data => {
      console.log(data);
      this.getEmployees();
    })
  }
}
