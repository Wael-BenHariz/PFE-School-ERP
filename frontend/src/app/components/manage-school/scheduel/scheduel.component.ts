import {Component} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {ScheduelService} from "../../../services/scheduel.service";
import {ScheduelPage} from "../../../model/scheduel";

@Component({
  selector: 'app-scheduel',
  templateUrl: './scheduel.component.html'
})
export class ScheduelComponent {
  //Filter
  showArchived: boolean = false
  //end filter

  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination
  scheduel$: Observable<ScheduelPage> = this.scheduelService.getAllScheduel()

//Modals
  deleteModalOpen: boolean = false
  createScheduelModalOpen: boolean = false

  constructor(private scheduelService: ScheduelService) {
  }

  getFilteredScheduels() {
    if (!this.showArchived) {
      this.scheduel$ = this.scheduelService.getAllScheduel()
  }
  }


  openlink(link: string) {
    window.open(link, '_blank')
  }
  deleteScheduel(id:number){
      console.log(id)
      this.scheduelService.deleteScheduel(id).subscribe((result) => {
        this.getFilteredScheduels()
      }, error => {
      })
  }
}
