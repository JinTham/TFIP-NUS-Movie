import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Review } from '../models/review';
import { CommentObj } from '../models/comment';
import { Subscription } from 'rxjs/internal/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit, OnDestroy{

  form!:FormGroup
  title:string = ""
  movieName!:string
  comment!:CommentObj
  sub$!:Subscription
  queryParams!:any

  constructor(private fb:FormBuilder, private actRoute:ActivatedRoute, private router:Router, private movieSvc:MovieService) { }

  ngOnInit(): void {
    this.form = this.createForm()
    this.sub$ = this.actRoute.queryParams.subscribe(
      (params) => {
        const queryParams = params['queryParam'].split('|')
        this.movieName = queryParams[0]
        this.title = queryParams[1]
      }
    )
  }

  ngOnDestroy(): void {
      this.sub$.unsubscribe()
  }

  createForm():FormGroup {
    return this.fb.group({
      name:this.fb.control('',[Validators.required,Validators.minLength(3)]),
      rating:this.fb.control('',[Validators.required]),
      comment:this.fb.control('',[Validators.required])
    })
  }

  addComment() {
    this.comment = this.form.value
    this.comment.title = this.title
    this.movieSvc.saveComment(this.comment)
    this.router.navigate(['/results',this.movieName])
  }

}
