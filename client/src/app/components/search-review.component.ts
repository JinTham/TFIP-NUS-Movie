import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MovieService } from '../services/movie.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit {

  form!:FormGroup

  constructor(private fb:FormBuilder, private movieSvc:MovieService, private router:Router) { }

  ngOnInit(): void {
      this.form = this.createForm()
  }

  private createForm():FormGroup {
    return this.fb.group({
      movieName:this.fb.control('',[Validators.required,Validators.minLength(2)])
    })
  }

  processForm() {
    this.router.navigate(['/results', this.form.value["movieName"]])
  }

}
