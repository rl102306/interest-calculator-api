import { Routes } from '@angular/router';


import { CalculadoraComponent } from './components/calculadora/calculadora.component';
import { HistorialComponent } from './components/historial/historial.component';

export const routes: Routes = [
  { path: 'calculadora', component: CalculadoraComponent },
  { path: 'historial', component: HistorialComponent },
  { path: '', redirectTo: 'calculadora', pathMatch: 'full' }
];