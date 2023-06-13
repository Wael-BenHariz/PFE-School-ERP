import { Component, EventEmitter, HostListener, OnInit, Output } from '@angular/core';
import {Router} from "@angular/router";
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';
import { ThemeService } from 'src/app/services/theme.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html'
})
export class AddUserComponent implements OnInit {



  //Data
  email = ""
  password = ""
  repeatPassword = ""
  firstName = ""
  lastName = ""
  personalId = ""
  token = ""
  //end data

  //Loading
  loading = false
  //end loading

  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()



  constructor(private authService: AuthService,
              private router: Router,
              public themeService: ThemeService,
              private alertService: AlertService,
              private userService: UserService) {
  }

  ngOnInit(): void {
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeModal()
    }
  }

  register() {
    if (this.email == "" || this.password == "" || this.firstName == "" || this.lastName == "" || this.personalId == "" || this.token == "") {
      this.alertService.showAlert("danger", "Please fill all fields.")
      return
    }

    if (this.password.length < 8 || this.password.length > 20) {
      this.alertService.showAlert("danger", "Password must be between 8 and 20 characters.")
      return
    }

    if (this.repeatPassword != this.password) {
      this.alertService.showAlert("danger", "Repeated password doesn't match password")
      return
    }

    this.loading = true
    this.authService.register(this.email, this.password, this.firstName, this.lastName, this.personalId, this.token).subscribe((result) => {
      this.alertService.showAlert("success", "You have successfully created a user.")
      this.success.emit()

    }, error => {
      this.loading = false
      if (error.status === 406) {
        this.alertService.showAlert("danger", "Invalid registration token.")
      } else if (error.status === 409) {
        this.alertService.showAlert("danger", "There is another account registered on this email.")
      } else {
        this.alertService.showAlert("danger", "Provided data is invalid. Make sure form is properly filled.")
      }
    })
  }
  closeModal() {
    this.close.emit()
  }
}
