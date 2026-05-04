import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Manager } from '../models/manager.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ManagerService {

  private apiUrl = `${environment.apiUrl}/managers`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Manager[]> {
    return this.http.get<Manager[]>(this.apiUrl);
  }

  getById(id: number): Observable<Manager> {
    return this.http.get<Manager>(`${this.apiUrl}/${id}`);
  }

  create(manager: Manager): Observable<Manager> {
    return this.http.post<Manager>(this.apiUrl, manager);
  }

  update(id: number, manager: Manager): Observable<Manager> {
    return this.http.put<Manager>(`${this.apiUrl}/${id}`, manager);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
