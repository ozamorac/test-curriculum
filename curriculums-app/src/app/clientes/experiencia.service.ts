import { Injectable } from '@angular/core';
import { CLIENTES } from './clientes.json';
import { Experiencia } from './experiencia';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable()
export class ExperienciaService {
  private urlEndPoint: string = 'http://localhost:8080/api/experiencias';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  getExperiencias(): Observable<Experiencia[]> {
    //return of(CLIENTES);
    return this.http.get(this.urlEndPoint).pipe(
      map(response => response as Experiencia[])
    );
  }

  create(cliente: Experiencia) : Observable<Experiencia> {
    return this.http.post<Experiencia>(this.urlEndPoint, cliente, {headers: this.httpHeaders})
  }

  getExperiencia(id): Observable<Experiencia>{
    return this.http.get<Experiencia>(`${this.urlEndPoint}/${id}`)
  }

  update(cliente: Experiencia): Observable<Experiencia>{
    return this.http.put<Experiencia>(`${this.urlEndPoint}/${cliente.id}`, cliente, {headers: this.httpHeaders})
  }

  delete(id: number): Observable<Experiencia>{
    return this.http.delete<Experiencia>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders})
  }

}
