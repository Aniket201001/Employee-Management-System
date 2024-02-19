import { Component, OnInit } from '@angular/core';
import { Addemp } from '../addemp';
import { AddempService } from '../addemp.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrl: './update-employee.component.css'
})
export class UpdateEmployeeComponent implements OnInit{

 id:number=0;
 employees: Addemp=new Addemp();

 constructor(private empservice:AddempService,private router:Router,private route: ActivatedRoute){}



  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.empservice.getEmployeeById(this.id).subscribe({
      next: data => {
        this.employees=data;
      },
      error: error => console.log(error)
    });
  }

  onSubmit(){
    this.empservice.updateEmployee(this.id, this.employees).subscribe( {
      next: data => {
        this.goToEmployeeList();
      },
      error: error => console.log(error)
    });
  }

  goToEmployeeList(){
    this.router.navigate(['/employees']);
  }
}
