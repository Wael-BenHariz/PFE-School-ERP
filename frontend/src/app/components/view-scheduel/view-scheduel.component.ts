import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { ClassPage } from 'src/app/model/class';
import { Scheduel, ScheduelPage } from 'src/app/model/scheduel';
import { AuthService } from 'src/app/services/auth.service';
import { ClassService } from 'src/app/services/class.service';

import { ScheduelService } from 'src/app/services/scheduel.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-view-scheduel',
  templateUrl: './view-scheduel.component.html'
})
export class ViewScheduelComponent implements OnInit {


  scheduel$: Observable<Scheduel> | undefined
  id: number | undefined;
  user$ : any
  schoolClassName:any

  class: ClassPage | undefined
  constructor(private userService: UserService,
              public authService: AuthService,
              public schedualService: ScheduelService,
              public classService: ClassService) { }


  ngOnInit(): void {
    if (this.authService.getRole() == "TEACHER") {
      this.user$ = this.userService.getLoggedInUserObservable().subscribe((result) => {
        this.userService.user = result
        this.id = this.userService.user.id
        this.scheduel$ = this.schedualService.getTeacherScheduel(this.id);
      })
    }
    if(this.authService.getRole() == "STUDENT" ){
      this.user$ = this.userService.getLoggedInUserObservable().subscribe((result) => {
        this.userService.user = result
        this.id = this.userService.user.id
        if(this.class?.content)
        this.scheduel$ = this.schedualService.getClassScheduel(this.id);
    })
  }
}

  refreshUserData() {
    this.userService.getLoggedInUserObservable().subscribe((result) => {
      this.userService.user = result
      this.id = this.userService.user.id
    })
  }

  openlink(link:string){
    window.open(link,'_blank')
  }
  get user() {
    return this.userService.user
  }

}
