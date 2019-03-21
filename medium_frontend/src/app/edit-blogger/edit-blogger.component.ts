import { UsersService } from './../../services/users.service';
import { ActivatedRoute } from '@angular/router';
import { UserDetails } from './../../models/userDetails.model';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-blogger',
  templateUrl: './edit-blogger.component.html',
  styleUrls: ['./edit-blogger.component.css']
})
export class EditBloggerComponent implements OnInit {
  thisBlogger: UserDetails;
  thisBloggerId: string;

  editUserForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.pattern('[a-z][A-Za-z0-9._+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}')])
  });
  editUserMsg: any;

  newUserErrorMsgs = {
    email: [
      { type: 'required', message: 'Email is required' },
      { type: 'pattern', message: 'Enter a valid email' }
    ],
    firstName: [
      { type: 'required', message: 'First name is required' }
    ],
    lastName: [
      { type: 'required', message: 'Last name is required' }
    ]
  };

  constructor(private route: ActivatedRoute, private userService: UsersService) {}

  ngOnInit() {
    this.route.params.subscribe(resp => {
      this.thisBloggerId = resp.id;
    });
    this.userService.getUserDetailsByUsername(this.thisBloggerId).subscribe( resp => {
      this.thisBlogger = resp;
      this.editUserForm.get('firstName').setValue(this.thisBlogger.firstName);
      this.editUserForm.get('lastName').setValue(this.thisBlogger.lastName);
      this.editUserForm.get('email').setValue(this.thisBlogger.email);
    });
  }

  onSubmit() {
    let editUserObj = {
      usersUsername: this.thisBloggerId,
      firstName: this.editUserForm.get('firstName').value,
      lastName: this.editUserForm.get('lastName').value,
      email: this.editUserForm.get('email').value,
    };

    this.userService.edditUser(editUserObj).subscribe( resp => {
      this.editUserMsg = resp;
    });
    this.editUserForm.reset();
  }
}
