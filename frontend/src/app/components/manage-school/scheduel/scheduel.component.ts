import {Component} from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Component({
  selector: 'app-scheduel',
  templateUrl: './scheduel.component.html'
})
export class ScheduelComponent {
  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination


//Modals
  deleteModalOpen: boolean = false
  createScheduelModalOpen: boolean = false

  constructor( ) {
  }

}
