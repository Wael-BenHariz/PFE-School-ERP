import {Component} from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../../../model/user";
import {AlertService} from "../../../services/alert.service";
import {UserService} from "../../../services/user.service";
import {ClassPage} from "../../../model/class";
import {ClassService} from "../../../services/class.service";
import {ScheduelService} from "../../../services/scheduel.service";

@Component({
  selector: 'app-scheduel',
  templateUrl: './scheduel.component.html'
})
export class ScheduelComponent {
  //Data
  file!: File;
  file_name: any;
  file_url:any;

  chooseReciever = 'teacher'
  title: string = ""
  description: string = ""
  teacher_id: number | undefined
  class_id: number | undefined
  classes$: Observable<ClassPage> = this.classService.getAllActiveClasses()
  teachers$: Observable<User[]> = this.userService.getAllTeachers()

  constructor(private classService: ClassService,
              private userService: UserService,
              private alertService: AlertService,
              private scheduelService: ScheduelService,
  ) {
  }


  chooseFile() {
    document.getElementById('fileInput')?.click();
  }

  onFileSelected(event: any | null) {
    if (event.target.files[0]) {
      this.file = event.target.files[0]
      this.file_name = this.file.name
    }
  }

  receiver(input: string) {
    this.chooseReciever = input;
  }

  submit() {
    if (this.chooseReciever == 'teacher') {

      if (this.title === '' || this.description === '' || this.teacher_id === undefined) {
        this.alertService.showAlert('warning', 'Fill all the required fields.')
      } else {

        this.scheduelService.geturl(this.file).subscribe(
          (result: any) => {
            this.file_url = result.url

            this.scheduelService.createScheduleTeacher(this.title, this.description, this.file_url, this.teacher_id).subscribe((result) => {
              this.alertService.showAlert('success', 'Activity has been successfully created! Now you can grade students of it.')
            }, error => {
              this.alertService.showAlert('danger', 'Something went wrong during creating a activity. Make sure form is valid')
            })
          },
          (error) => {
            console.error(error); // Handle any errors
          }
        );

      }
    } else if (this.chooseReciever == 'class') {
      if (this.title === '' || this.description === '' || this.class_id === undefined) {
        this.alertService.showAlert('warning', 'Fill all the required fields.')
      } else {
        this.scheduelService.geturl(this.file).subscribe(
          (result: any) => {
            this.file_url = result.url
            this.scheduelService.createScheduleClasse(this.title, this.description, this.file_url, this.class_id).subscribe((result) => {
              this.alertService.showAlert('success', 'Activity has been successfully created! Now you can grade students of it.')
            }, error => {
              this.alertService.showAlert('danger', 'Something went wrong during creating a activity. Make sure form is valid')
            })
          },
          (error) => {
            console.error(error); // Handle any errors
          }
        );
      }
    }
  }
}
