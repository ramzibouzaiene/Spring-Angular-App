import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  backendMessage = '';
  private unsubscribe$: Subject<void> = new Subject<void>();

  constructor(
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router
  ) {}
  ngOnInit() {
    this.authService
      .test()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: any) => {
          console.log('response backend =>', JSON.stringify(response));
          this.backendMessage = JSON.stringify(response);
          this.toastr.success(response);
        },
        (error: any) => {
          this.toastr.error(JSON.stringify(error));
        }
      );
  }

  onLogout() {
    this.authService.logout();
    this.toastr.success('You are logged out');
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
