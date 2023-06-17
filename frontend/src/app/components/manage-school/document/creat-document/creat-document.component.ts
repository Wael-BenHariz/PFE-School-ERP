
import {Component, EventEmitter, HostListener, Output} from '@angular/core';
import {AlertService} from "../../../../services/alert.service";
import {DocumentService} from "../../../../services/document.service";

@Component({
  selector: 'app-creat-document',
  templateUrl: './creat-document.component.html'
})
export class CreatDocumentComponent {
//Data
  file!: File;
  file_name: any;
  file_url:any;

  title: string = ""
  description: string = ""

  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()
  constructor(
    private alertService: AlertService,
    private documentService: DocumentService,
  ) { }
  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeModal()
    }
  }
  closeModal() {
    this.close.emit()
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


  submit() {
    if (this.title === '' || this.description === '') {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
    } else {

      this.documentService.geturl(this.file).subscribe(
        (result: any) => {
          this.file_url = result.url
          this.documentService.createDocument(this.title, this.description, this.file_url)
            .subscribe((result) => {
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
