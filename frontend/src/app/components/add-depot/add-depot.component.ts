import { Component, EventEmitter, HostListener, Input, OnInit, Output } from '@angular/core';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';
import { GradeService } from 'src/app/services/grade.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-depot',
  templateUrl: './add-depot.component.html'
})
export class AddDepotComponent implements OnInit {
  
  @Input() activityid: number | undefined
  @Input() activityname: string | undefined
  @Input() activitydate: Date | undefined

  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()


  loading = false
  imageSrc: string | ArrayBuffer | null = null;
  file!: File


  constructor(private alertService: AlertService,private gradeservice: GradeService,public authService: AuthService,private userService: UserService) { }

  ngOnInit(): void {
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeModal()
    }
  }
  
 

  triggerFileInput() {
    document.getElementById('file-upload-input')?.click();
  }

  readURL(event: any) {
    const input = event.target;
    if (input.files && input.files[0]) {
      const reader = new FileReader();
      reader.onload = () => {
        this.imageSrc = reader.result;
          this.file=event.target.files[0];
      };
      reader.readAsDataURL(input.files[0]);
    } else {
      this.removeUpload();
    }
  }

  removeUpload() {
    this.file= null as unknown as File;
  }

  submit(){
    if (this.file === null) {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }
    console.log(this.file)
     this.loading = true
    this.gradeservice.geturl(this.file).subscribe(
      (result: any) => {
         this.gradeservice.updateGrade(this.activityid, this.userService.user?.id, -1, "",result.url).subscribe((result) => {
           this.alertService.showAlert("success", "Work has been deposit!")
           this.loading = true
           this.close.emit()
         }, error => {
           this.alertService.showAlert("danger", "Error during grading. Make sure form is valid!")
           this.loading = false
         })
      },
      (error) => {
        console.error(error); 
      }
    );



   
    
    


  }



  

  closeModal() {
    this.close.emit()
  }
}
