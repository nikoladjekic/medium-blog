import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { DomenService } from 'src/services/domen.service';
import { Domain } from './../../models/domen.model';

@Component({
  selector: 'app-create-domen',
  templateUrl: './create-domen.component.html',
  styleUrls: ['./create-domen.component.css']
})
export class CreateDomenComponent implements OnInit {
  respMsg = '';
  domainName: string;
  domainPicture: string;
  form = new FormGroup({
    domainName: new FormControl(''),
    domainPicture: new FormControl('')
  });

  constructor(private domainService: DomenService) { }

  get getDomainName() {
    return this.form.get('domainName');
  }

  get getDomainPicture() {
    return this.form.get('domainPicture');
  }

  takeDomainNameValue(val: string) {
    this.domainName = val;
  }

  takeDomainPictureValue(val: string) {
    this.domainPicture = val;
  }

  ngOnInit() {
  }

  createDomen() {
    const domenToBeSent: Domain = {
      domainId: null,
      name: this.domainName.toUpperCase(),
      picture: this.domainPicture,
      blogs: null
    }
    this.domainService.addANewDomen(domenToBeSent).subscribe( resp => {
      if (resp.status === 201) {
        this.respMsg = 'You have successfully added a new domain';
      } else if( resp.status === 409) {
        this.respMsg = 'Domain already exists';
      } 
      else if( resp.status === 403) {
        this.respMsg = 'You dont have permission to add new topic!';
      }else {
        this.respMsg = 'Your request failed, try again';
      }
    },
    err =>{
      if(err.status===403){
        this.respMsg='You dont have permission to add new topic!'
      }
    });
  }

}
