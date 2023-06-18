import {Component} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {ScheduelService} from "../../../services/scheduel.service";
import {ScheduelPage} from "../../../model/scheduel";

@Component({
  selector: 'app-scheduel',
  templateUrl: './scheduel.component.html'
})
export class ScheduelComponent {
  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination
  scheduel$: Observable<ScheduelPage> = this.scheduelService.getAllScheduel()

//Modals
  deleteModalOpen: boolean = false
  createScheduelModalOpen: boolean = false

  constructor(private scheduelService: ScheduelService) {
  }

  openlink(link: string) {
    window.open(link, '_blank')
  }
  deleteScheduel(id:number){
      this.scheduelService.deleteScheduel(id)
  }
}
