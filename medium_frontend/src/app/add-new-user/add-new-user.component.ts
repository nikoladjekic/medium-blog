import { UsersService } from './../../services/users.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-new-user',
  templateUrl: './add-new-user.component.html',
  styleUrls: ['./add-new-user.component.css']
})
export class AddNewUserComponent implements OnInit {

  newUserForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(3)]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)]),
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.pattern('[a-z][A-Za-z0-9._+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}')])
  });
  newUserAddedString:any;

  newUserErrorMsgs = {
    email: [
      { type: 'required', message: 'Email is required' },
      { type: 'pattern', message: 'Enter a valid email' }
    ],
    username: [
      { type: 'required', message: 'Username is required' },
      { type: 'minlength', message: 'Username must be at least 3 characters long' }
    ],
    password: [
      { type: 'required', message: 'Password is required' },
      { type: 'minlength', message: 'Password must be at least 8 characters long' }
    ],
    firstName: [
      { type: 'required', message: 'First name is required' }
    ],
    lastName: [
      { type: 'required', message: 'Last name is required' }
    ]
  };

  constructor(private usersService: UsersService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    let userAndUserDetailsObj: any = {
      username: this.newUserForm.get('username').value,
      password: this.newUserForm.get('password').value,
      usersUsername: this.newUserForm.get('username').value,
      firstName: this.newUserForm.get('firstName').value,
      lastName: this.newUserForm.get('lastName').value,
      email: this.newUserForm.get('email').value
    };

    this.usersService.addANewUser(userAndUserDetailsObj).subscribe(() => {
      this.router.navigate(['adminOptions/listUsers']);
    },
      err => {
        if (err) { this.newUserAddedString = err; }
      }
    );
  }
}
