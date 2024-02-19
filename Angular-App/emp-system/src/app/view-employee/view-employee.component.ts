import { Component, OnInit } from '@angular/core';
import { Addemp } from '../addemp';
import { ActivatedRoute } from '@angular/router';
import { AddempService } from '../addemp.service';

@Component({
  selector: 'app-view-employee',
  templateUrl: './view-employee.component.html',
  styleUrl: './view-employee.component.css'
})
export class ViewEmployeeComponent implements OnInit {

  id: number=0;
  employees: Addemp=new Addemp();
  constructor(private route: ActivatedRoute, private employeService: AddempService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

  
    this.employeService.getEmployeeById(this.id).subscribe( data => {
      this.employees = data;
    });
  }

}
