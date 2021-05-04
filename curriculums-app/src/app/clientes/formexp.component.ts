import { Component, OnInit } from '@angular/core';
import {Experiencia} from './experiencia'
import {ExperienciaService} from './experiencia.service';
import {Router, ActivatedRoute} from '@angular/router'
import swal from 'sweetalert2'


@Component({
  selector: 'app-formexp',
  templateUrl: './formexp.component.html',
  styleUrls: ['./formexp.component.css']
})
export class FormexpComponent implements OnInit {
  private experiencia: Experiencia = new Experiencia()
  private titulo:string = "Crear Expediente"
  constructor(private experienciaService: ExperienciaService,
    private router: Router,
  private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.cargarExperiencia()
  }

  
  cargarExperiencia(): void{
    this.activatedRoute.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.experienciaService.getExperiencia(id).subscribe( (experiencia) => this.experiencia = experiencia)
      }
    })
  }

  create(): void {
    this.experienciaService.create(this.experiencia)
      .subscribe(experiencia => {
        this.router.navigate(['/experiencias'])
        swal('Nuevo Experiencia', `Experiencia ${experiencia.empresa} creado con éxito!`, 'success')
      }
      );
  }

  update():void{
    this.experienciaService.update(this.experiencia)
    .subscribe( experiencia => {
      this.router.navigate(['/experiencias'])
      swal('Experiencias Actualizado', `Experiencias ${experiencia.empresa} actualizado con éxito!`, 'success')
    }

    )
  }


}
