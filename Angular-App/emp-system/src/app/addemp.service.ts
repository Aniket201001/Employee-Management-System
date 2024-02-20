import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Addemp } from './addemp';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddempService {

  private baseURL="http://localhost:9000/employees";
  
  constructor(private httpClient:HttpClient) { }

  getEmployeeList(): Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.baseURL}`);
  }

  addEmployee(addEmp: Addemp):Observable<any>{
    return this.httpClient.post(`${this.baseURL}`,addEmp);
  }

  getEmployeeById(id: number):Observable<any>{
    return this.httpClient.get<any>(`${this.baseURL}/${id}`);

  }
  
  updateEmployee(id: number, employee: Addemp): Observable<any>{
    return this.httpClient.put(`${this.baseURL}/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
