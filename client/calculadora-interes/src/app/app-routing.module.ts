import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalculadoraComponent } from './components/calculadora/calculadora.component';
import { HistorialComponent } from './components/historial/historial.component';

const routes: Routes = [
  { path: 'calculadora', component: CalculadoraComponent },
  { path: 'historial', component: HistorialComponent },
  { path: '', redirectTo: 'calculadora', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
