import { Domain } from 'src/models/domen.model';
import { DomenService } from 'src/services/domen.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-domains',
  templateUrl: './all-domains.component.html',
  styleUrls: ['./all-domains.component.css']
})
export class AllDomainsComponent implements OnInit {

  allDomains: Domain[];

  constructor(private domensService: DomenService) { }

  ngOnInit() {
    this.getAllDomains();
  }

  getAllDomains() {
    this.domensService.getAllDomens().subscribe(resp => this.allDomains = resp);
  }

}
