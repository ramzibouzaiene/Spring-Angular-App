import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from '../models/Login';
import { Register } from '../models/Register';
import { Auth } from '../models/Auth';
import { environment } from '../../environments/environment';

const TOKEN_KEY = 'accessToken';
const AUTH_API = environment.backendUrl;
const AUTH_API_TEST = environment.testUrl;

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(body: Login): Observable<Auth> {
    return this.http.post<Auth>(AUTH_API + 'login', body);
  }

  register(body: Register): Observable<String> {
    return this.http.post<String>(AUTH_API + 'register', body);
  }

  test(): Observable<any> {
    return this.http.get(AUTH_API_TEST, { responseType: 'text' });
  }

  isLoggedIn(): boolean {
    const accessToken = localStorage.getItem('accessToken');
    return !!accessToken;
  }

  getToken(): string | null {
    return localStorage.getItem('accessToken');
  }

  logout(): void {
    window.localStorage.clear();
  }

  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }
}
