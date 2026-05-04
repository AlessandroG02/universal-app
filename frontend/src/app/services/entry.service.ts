import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Entry } from '../models/entry.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class EntryService {

  private apiUrl = `${environment.apiUrl}/entries`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Entry[]> {
    return this.http.get<Entry[]>(this.apiUrl);
  }

  getByMember(memberId: number): Observable<Entry[]> {
    return this.http.get<Entry[]>(
      `${this.apiUrl}/by-member/${memberId}`
    );
  }

  create(entry: Entry): Observable<Entry> {
    return this.http.post<Entry>(this.apiUrl, entry);
  }

  update(id: number, entry: Entry): Observable<Entry> {
    return this.http.put<Entry>(`${this.apiUrl}/${id}`, entry);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
