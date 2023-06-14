import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {LevelPage} from "../../../model/level";
import {User} from "../../../model/user";
import {AlertService} from "../../../services/alert.service";
import {LevelService} from "../../../services/level.service";
import {UserService} from "../../../services/user.service";
import {Class} from "../../../model/class";

@Component({
  selector: 'app-scheduel',
  templateUrl: './scheduel.component.html'
})
export class ScheduelComponent implements OnInit {
  //Data
  shortName: string = ""
  fullName: string = ""
  levelId: number | undefined
  teacherId: number | undefined
  levels$: Observable<LevelPage> = this.levelService.getAllActiveLevels()
  teachers$: Observable<User[]> = this.userService.getAllNotSupervisingActiveTeachers()
  //end data

  @Input() class: Class | undefined

  constructor(private levelService: LevelService,
              private userService: UserService,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
    this.shortName = this.class?.name || ""
    this.fullName = this.class?.fullName || ""
    this.teacherId = this.class?.supervisingTeacher.id
    this.levelId = this.class?.level.id
  }

  submit() {
    if (this.shortName === '' || this.fullName === '' || this.levelId === undefined || this.teacherId === undefined) {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }
  }
}
