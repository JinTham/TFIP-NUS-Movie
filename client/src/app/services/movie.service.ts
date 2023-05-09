import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Review } from '../models/review';
import { CommentObj } from '../models/comment';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  SB_URL_SEARCH:string = "/api/search"
  SB_URL_COMMENT:string = "/api/comment"

  constructor(private httpClient:HttpClient) { }

  getReviews(movieName:string):Promise<any> {
    //create query parameter
    const params = new HttpParams().set('query',movieName)
    return lastValueFrom(this.httpClient.get<Review[]>(this.SB_URL_SEARCH, {params:params}))
  }

  saveComment(comment:CommentObj):Promise<any> {
    const headers = new HttpHeaders().set("Content-Type","application/json")
    return lastValueFrom(this.httpClient.post<CommentObj>(this.SB_URL_COMMENT, JSON.stringify(comment), {headers:headers}))
  }
}
