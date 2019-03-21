import { UsersService } from './../../services/users.service';
import { UserDetails } from './../../models/userDetails.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {

  allUsers: UserDetails[];
  deleteString = '';

  constructor(private userService: UsersService) { }

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.getAllUserDetails().subscribe( resp => {
      this.allUsers = resp;
    });
  }

  deleteUser(user: string) {
    this.userService.deleteUserByUsername(user).subscribe( resp => {
      if (resp.status === 404) {
        this.deleteString = "That user doesn't exist.";
      } else {
        this.deleteString = 'You have successfully deleted the users';
      }
      this.getAllUsers();
    });
  }

}
