import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent} from './footer/footer.component';
import { DirectivaComponent } from './directiva/directiva.component';
import { ClientesComponent } from './clientes/clientes.component';
import { ClienteService } from './clientes/cliente.service';
import { ExperienciaService } from './clientes/experiencia.service';
import { RouterModule, Routes} from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormComponent } from './clientes/form.component';
import { FormsModule } from '@angular/forms';
import { CurriculumsComponent } from './curriculums/curriculums.component';
import { LoginComponent } from './login/login.component';
import { FormexpComponent } from './clientes/formexp.component';
import { CurriculumComponent } from './clientes/curriculum.component'

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'curriculums', component: CurriculumsComponent},
  {path: 'directivas', component: DirectivaComponent},
  {path: 'clientes', component: ClientesComponent},
  {path: 'clientes/form', component: FormComponent},
  {path: 'clientes/form/:id', component: FormComponent},
  {path: 'clientes/formexp', component: FormexpComponent},
  {path: 'clientes/formexp/:id', component: FormexpComponent},
  {path: ':nombre', component: CurriculumComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectivaComponent,
    ClientesComponent,
    FormComponent,
    CurriculumsComponent,
    LoginComponent,
    FormexpComponent,
    CurriculumComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [ClienteService,
              ExperienciaService
            ],
  bootstrap: [AppComponent]
})
export class AppModule { }
