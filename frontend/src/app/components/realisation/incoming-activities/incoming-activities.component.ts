import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActivityPage} from "../../../model/activity";
import { AuthService } from 'src/app/services/auth.service';
import { GradeService } from 'src/app/services/grade.service';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-incoming-activities',
  templateUrl: './incoming-activities.component.html'
})
export class IncomingActivitiesComponent implements OnInit {

  @Input() realisationId: number | undefined
  @Input() activityPage: ActivityPage | undefined

  @Output() nextPage: EventEmitter<any> = new EventEmitter()
  @Output() previousPage: EventEmitter<any> = new EventEmitter()

  file:any;
  file_name:string | undefined
  file_url:string  | undefined
  activityid: number | undefined
  activityname: string | undefined
  activitydate: Date | undefined
  addworks:boolean=false;

  constructor(private alertService: AlertService,public authService: AuthService,private gradeservice: GradeService) {
  }

  ngOnInit(): void {
  }

  previous() {
    this.previousPage.emit()
  }

  next() {
    this.nextPage.emit()
  }
  openlink(link:string){
    window.open(link,'_blank')
  }
  

      addwork(activityId:number,activityname:string,activitydate:Date):void{
        this.activityid=activityId
        this.activitydate=activitydate
        this.activityname=activityname
        this.addworks=true
      }

      depsit_done():void{
        
      }

      submit(){
        if(this.file === null){
          this.alertService.showAlert('warning', 'No file added.')
      return
        }
        this.gradeservice.geturl(this.file).subscribe(
          (result: any) => {
            this.file_url=result.url
          },
          (error) => {
            console.error(error) 
          }
        );
      }
}
