import {Component, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {AlertService} from "../../../services/alert.service";
import {ActivityService} from "../../../services/activity.service";
import { GradeService } from 'src/app/services/grade.service';

@Component({
  selector: 'app-create-activity',
  templateUrl: './create-activity.component.html'
})
export class CreateActivityComponent implements OnInit {

  //Data
  name: string = ""
  description: string = ""
  weight: number = 1
  date = new Date().toISOString().slice(0, 10)
  file!: File;
  file_name:any;
  file_url:any;
  //end data


  //Loading
  loading = false
  //end loading

  @Input() realisationId: number | undefined
  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private alertService: AlertService,
              private activityService: ActivityService,private gradeservice: GradeService) {
  }

  ngOnInit(): void {
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
    
    if (this.name === '' || this.weight === 0 || this.file === null) {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }

    this.loading = true
    this.gradeservice.geturl(this.file).subscribe(
      (result: any) => {
        this.file_url=result.url
        this.activityService.createActivity(this.name, this.description, this.weight, new Date(this.date), this.realisationId,this.file_url).subscribe((result) => {
          this.alertService.showAlert('success', 'Activity has been successfully created! Now you can grade students of it.')
          this.success.emit()
        }, error => {
          this.alertService.showAlert('danger', 'Something went wrong during creating a activity. Make sure form is valid')
          this.loading = false
        })
      },
      (error) => {
        console.error(error); // Handle any errors
      }
    );
    
  }

  closeModal() {
    this.close.emit()
  }
}
