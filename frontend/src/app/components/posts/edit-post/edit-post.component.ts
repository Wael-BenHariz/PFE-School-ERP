import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PostService} from "../../../services/post.service";
import {AlertService} from "../../../services/alert.service";
import {Post} from "../../../model/post";
import { GradeService } from 'src/app/services/grade.service';

@Component({
  selector: 'app-edit-post',
  templateUrl: './edit-post.component.html'
})
export class EditPostComponent implements OnInit {

  //Data
  title: string = ""
  content: string = ""
  file!: File
  file_name:any
  file_url:any
  //end data


  //Loading
  loading = false
  //end loading

  @Input() post: Post | undefined
  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private postService: PostService,
              private alertService: AlertService,private gradeservice: GradeService) {
  }

  ngOnInit(): void {
    this.title = this.post?.title || ""
    this.content = this.post?.content || ""
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
    if (this.title === '' || this.content === '') {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }
    this.loading = true
    if(this.file){
      this.gradeservice.geturl(this.file).subscribe(
        (result: any) => {
          this.postService.updatePost(this.title, this.content, this.post?.postId,result.url).subscribe((result) => {
            this.alertService.showAlert('success', 'Post has been successfully updated!')
            this.success.emit()
          }, error => {
            this.alertService.showAlert('danger', 'Something went wrong during updating a post. Try again later.')
            this.loading = false
          })
        },
        (error) => {
          console.error(error); // Handle any errors
        }
      );
    }else{
      this.postService.updatePost(this.title, this.content, this.post?.postId,undefined).subscribe((result) => {
        this.alertService.showAlert('success', 'Post has been successfully updated!')
        this.success.emit()
      }, error => {
        this.alertService.showAlert('danger', 'Something went wrong during updating a post. Try again later.')
        this.loading = false
      })
    }

    
    
  }

  closeModal() {
    this.close.emit()
  }
}
