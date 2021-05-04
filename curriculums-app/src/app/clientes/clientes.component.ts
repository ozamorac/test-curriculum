import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { Experiencia } from './experiencia';
import { ClienteService } from './cliente.service';
import { ExperienciaService } from './experiencia.service';
import swal from 'sweetalert2'

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html'
})
export class ClientesComponent implements OnInit {

  clientes: Cliente[];
  experiencias: Experiencia[];

  constructor(private clienteService: ClienteService,private experienciaService: ExperienciaService) { }

  ngOnInit() {
    this.clienteService.getClientes().subscribe(
      clientes => this.clientes = clientes
    );

    this.experienciaService.getExperiencias().subscribe(
      experiencias => this.experiencias = experiencias
    );
  }

  delete(cliente: Cliente): void {
    swal({
      title: 'Está seguro?',
      text: `¿Seguro que desea eliminar al cliente ${cliente.nombre} ${cliente.apellido}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      buttonsStyling: false,
      reverseButtons: true
    }).then((result) => {
      if (result.value) {

        this.clienteService.delete(cliente.id).subscribe(
          response => {
            this.clientes = this.clientes.filter(cli => cli !== cliente)
            swal(
              'Cliente Eliminado!',
              `Cliente ${cliente.nombre} eliminado con éxito.`,
              'success'
            )
          }
        )

      }
    })
  }


  getValidaNombre(id: number){
    var totalCreEmp: string;
    for (var i = 0; i < this.clientes.length; i++) {
      if (this.clientes[i].id ==  id )
      { totalCreEmp =this.clientes[i].nombre}    
     }  
    return  totalCreEmp;
  }


}


