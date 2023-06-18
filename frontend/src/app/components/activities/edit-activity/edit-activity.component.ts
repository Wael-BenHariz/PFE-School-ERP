import {Component, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {AlertService} from "../../../services/alert.service";
import {Activity} from "../../../model/activity";
import {ActivityService} from "../../../services/activity.service";
import { GradeService } from 'src/app/services/grade.service';

@Component({
  selector: 'app-edit-activity',
  templateUrl: './edit-activity.component.html'
})
export class EditActivityComponent implements OnInit {

  //Data
  name: string = ""
  description: string = ""
  weight: number = 1
  date: any | undefined
  file!: File
  file_name:any
  file_url:any
  //end data


  //Loading
  loading = false
  //end loading

  @Input() activity: Activity | undefined
  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private activityService: ActivityService,
              private alertService: AlertService,private gradeservice: GradeService) {
  }

  ngOnInit(): void {
    this.name = this.activity?.name || ""
    this.weight = this.activity?.weight || 1
    this.description = this.activity?.description || ""
    this.date = this.activity?.date.toString().slice(0, 10)
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeModal()
    }
  }

  chooseFile() {
    document.getElementById('fileInput')?.click();
  }

  onFileSelected(event: any | null) {
    if (event.target.files[0]) {
      this.file=event.target.files[0]
      this.file_name=this.file.name
    }
  }

  submit() {
    if (this.name === '' || this.weight < 1) {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }
    this.loading = true
    if(this.file){
      this.gradeservice.geturl(this.file).subscribe(
        (result: any) => {
          this.activityService.updateActivity(this.name, this.description, this.weight, new Date(this.date), this.activity?.activityId,result.url).subscribe((result) => {
            this.alertService.showAlert('success', 'Activity has been successfully updated!')
            this.success.emit()
          }, error => {
            this.alertService.showAlert('danger', 'Something went wrong during updating a activity. Try again later.')
            this.loading = false
          })
        },
        (error) => {
          console.error(error); // Handle any errors
        }
      );
    }else{
      this.activityService.updateActivity(this.name, this.description, this.weight, new Date(this.date), this.activity?.activityId,undefined).subscribe((result) => {
        this.alertService.showAlert('success', 'Activity has been successfully updated!')
        this.success.emit()
      }, error => {
        this.alertService.showAlert('danger', 'Something went wrong during updating a activity. Try again later.')
        this.loading = false
      })
    }

    
  }

  closeModal() {
    this.close.emit()
  }
}
