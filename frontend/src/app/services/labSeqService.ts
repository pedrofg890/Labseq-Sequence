import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LabSeqService {
  private apiUrl = 'http://localhost:8080/labseq';

  constructor(private http: HttpClient) {}

  getLabSeq(n: number): Observable<string> {
    return this.http.get(`${this.apiUrl}/${n}`, { responseType: 'text' });
  }
}
