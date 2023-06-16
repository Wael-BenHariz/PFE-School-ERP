import {Component } from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../../../model/user";
import {AlertService} from "../../../services/alert.service";
import {UserService} from "../../../services/user.service";
import {Class, ClassPage} from "../../../model/class";
import {ClassService} from "../../../services/class.service";
import {ScheduelService} from "../../../services/scheduel.service";

@Component({
  selector: 'app-scheduel',
  templateUrl: './scheduel.component.html'
})
export class ScheduelComponent{
  //Data
  chooseReciever ='teacher'
  title: string = ""
  desc: string = ""
  file: string = ""
  teacher : User[] = []
  class: Class[] = []
  classes$: Observable<ClassPage>  = this.classService.getAllActiveClasses()
  teachers$: Observable<User[]> = this.userService.getAllTeachers()

  constructor(private classService: ClassService,
              private userService: UserService,
              private alertService: AlertService,
              private scheduelService: ScheduelService,
              ) {
  }



  receiver(input:string){
    this.chooseReciever = input;
  }

  submit() {
    if (this.chooseReciever == 'teacher') {
      if (this.title === '' || this.desc === '' || this.file === '' ) {
        this.alertService.showAlert('warning', 'Fill all the required fields.')
      } else{
        this.scheduelService.createScheduleTeacher(this.title, this.desc, this.file, this.teacher )
      }
    }
    else if (this.chooseReciever == 'class') {
      if (this.title === '' || this.desc === '' || this.file === '' ) {
        this.alertService.showAlert('warning', 'Fill all the required fields.')
      } else {
        this.scheduelService.createScheduleClasse(this.title, this.desc, this.file,this.class )
      }
    }
  }
}
