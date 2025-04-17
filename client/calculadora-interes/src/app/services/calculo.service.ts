
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalculoService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  calcularInteres(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/calcular-interes`, data);
  }

  obtenerHistorial(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/historial-calculos`);
  }
}
