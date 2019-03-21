import { LogCounter } from './../../models/logcounter.model';
import { LogcounterService } from './../../services/logcounter.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-log-counter',
  templateUrl: './admin-log-counter.component.html',
  styleUrls: ['./admin-log-counter.component.css']
})
export class AdminLogCounterComponent implements OnInit {

  allLogs: LogCounter[];

  constructor(private logcounterService: LogcounterService) { }

  ngOnInit() {
    this.getAllLogs();
  }

  getAllLogs() {
    this.logcounterService.getAllLogs().subscribe( res => this.allLogs = res);
  }

}
