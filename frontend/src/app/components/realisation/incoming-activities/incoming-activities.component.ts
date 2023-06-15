import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActivityPage} from "../../../model/activity";

@Component({
  selector: 'app-incoming-activities',
  templateUrl: './incoming-activities.component.html'
})
export class IncomingActivitiesComponent implements OnInit {

  @Input() realisationId: number | undefined;
  @Input() activityPage: ActivityPage | undefined

  @Output() nextPage: EventEmitter<any> = new EventEmitter();
  @Output() previousPage: EventEmitter<any> = new EventEmitter();

  file:any;
  file_name:string | undefined;

  constructor() {
  }

  ngOnInit(): void {
  }

  previous() {
    this.previousPage.emit()
  }

  next() {
    this.nextPage.emit()
  }

  chooseFile() {
        document.getElementById('fileInput')?.click();
      }
    
      onFileSelected(event: any | null) {
        if (event.target.files[0]) {
          this.file=event.target.files[0]
          this.file_name=this.file.name
        console.log(this.file_name)
          // Process the selected file as needed
        }
      }

      openPDF():void{
        if(this.file){
          window.open(URL.createObjectURL(this.file))
        }
      }
      downloadPdf(): void {
        if (this.file) {
          const fileUrl = URL.createObjectURL(this.file)
          const link = document.createElement('a')
          link.href = fileUrl
          link.download = this.file.name
          link.click()
        }
      }
}
