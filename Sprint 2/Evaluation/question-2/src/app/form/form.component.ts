import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup , Validators} from '@angular/forms';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit{
  userForm:FormGroup;

  constructor(private formBuilder: FormBuilder){
    this.userForm=this.formBuilder.group({
      name:["",[Validators.required,Validators.pattern(/^[A-Za-z]+$/)]],
      email:["",[Validators.required,Validators.email]],
      password:["",[Validators.required,Validators.minLength(5)]],
      age:["",[Validators.required,Validators.min(18),Validators.max(60)]],
      country:["",Validators.required],
      acceptance:[false,Validators.requiredTrue]
    })
  }
  
  submit(){
    if(this.userForm.valid){
      const formData=this.userForm.value;
      console.log(formData);
    }
  }
  
  ngOnInit(): void {
  }
}
