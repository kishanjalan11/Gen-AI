import { Component } from '@angular/core';

@Component({
  selector: 'app-counter',
  templateUrl: './counter.component.html',
  styleUrls: ['./counter.component.css']
})
export class CounterComponent {
  counter=0;
  increase(){
    this.counter+=1;
  }
  decrease(){
    if(this.counter>0){
      this.counter-=1;
    }
  }
  reset(){
    this.counter=0;
  }
}
