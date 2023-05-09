import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Review } from '../models/review';
import { Subscription } from 'rxjs/internal/Subscription';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css']
})
export class MovieReviewsListComponent implements OnInit, OnDestroy {

  movieName!:string
  reviews:Review[] = []
  sub$!:Subscription

  constructor(private router:Router, private actRoute:ActivatedRoute, private movieSvc:MovieService) { }

  ngOnInit(): void {
      this.sub$ = this.actRoute.params.subscribe(
        async (params) => {
          this.movieName = params["movieName"]
          this.reviews = await this.movieSvc.getReviews(this.movieName)
        }
      )
  }

  ngOnDestroy(): void {
      this.sub$.unsubscribe()
  }

  addComment(title:string) {
    const queryParams: Params = {queryParam: this.movieName + "|" + title}
    this.router.navigate(['/comment'],{queryParams:queryParams})
  }

}
