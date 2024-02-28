import { Component, OnInit } from '@angular/core';
import { Addemp } from '../addemp';
import { AddempService } from '../addemp.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrl: './create-employee.component.css'
})
export class CreateEmployeeComponent implements OnInit{

addemployees: Addemp=new Addemp();
constructor(private addEmpService:AddempService,private router:Router) {}

ngOnInit(): void {    
}

saveEmployee() {
  this.addEmpService.addEmployee(this.addemployees).subscribe({
    next: data => {
      console.log(data);
      this.goToEmployeeList();
    },
    error: error => {console.log(error)
    alert('Name or Email already exists');}
  });
}

goToEmployeeList(){
  this.router.navigate(['/employees']);
}
  
  validator() {
  console.log("in validator");
  if (
    !this.addemployees.name ||
    !this.addemployees.email ||
    !this.addemployees.salary ||
    !this.addemployees.bonus ||
    !this.addemployees.address ||
    !this.addemployees.phone // Removed unnecessary condition here
  ) {
    alert('Please fill all fields');
  } else {
    console.log("else block");
    this.saveEmployee();
  }
}

onSubmit(){
  console.log(this.addemployees);
  this.saveEmployee();
}
}
