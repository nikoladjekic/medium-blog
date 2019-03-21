import { AuthService } from './../../services/auth.service';
import { Component, OnInit, HostListener } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-options',
  templateUrl: './admin-options.component.html',
  styleUrls: ['./admin-options.component.css']
})
export class AdminOptionsComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  logout() {
    this.authService.logout().subscribe( () => {
      localStorage.removeItem('currentUser');
      localStorage.removeItem('cred');
    });
    this.router.navigate(['login']);
  }

}
