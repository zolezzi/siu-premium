import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserVO } from '../models/userVO';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private url = 'http://localhost:8080';

  constructor(private http: HttpClient) { }
 // private loggedIn = new BehaviorSubject<boolean>(sessionStorage.getItem('lastName') != null);
  
  logout() {console.log(localStorage.getItem('auth_token'),'token para sacar')
    localStorage.removeItem('auth_token');
  }
  login(user:UserVO): Observable<any> {
    return this.http.post(this.url + '/login', user);
  }
 createUser(user: UserVO):Observable<any> {
  return this.http.post(this.url + '/create', user);
}

}