import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CalculoService } from '../../services/calculo.service';

import { ChartConfiguration } from 'chart.js';

@Component({
  standalone: true,
  imports: [ReactiveFormsModule], // Añade BaseChartDirective
  selector: 'app-calculadora',
  templateUrl: './calculadora.component.html'
})
export class CalculadoraComponent {
  form: FormGroup;
  resultado: any;
  errorMessage = '';

  // Configuración del gráfico
  public lineChartData: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: []
  };

  public lineChartOptions: ChartConfiguration<'line'>['options'] = {
    responsive: true
  };

  constructor(private fb: FormBuilder, private calculoService: CalculoService) {
    this.form = this.fb.group({
      montoInicial: [null, [Validators.required, Validators.min(1)]],
      tasaInteres: [null, [Validators.required, Validators.min(1)]],
      anios: [null, [Validators.required, Validators.min(1), Validators.max(50)]]
    });
  }

  enviar() {
    if (this.form.invalid) {
      this.errorMessage = 'Corrige los errores del formulario.';
      return;
    }

    this.calculoService.calcularInteres(this.form.value).subscribe({
      next: (res) => { 
        this.resultado = res;
        this.errorMessage = '';
        
        // Actualizar datos del gráfico
        this.lineChartData = {
          labels: res.interes_compuesto.map(x => 'Año ' + x.anio),
          datasets: [
            { 
              data: res.interes_compuesto.map(x => x.monto), 
              label: 'Interés Compuesto', 
              borderColor: 'blue',
              tension: 0.1
            },
            { 
              data: res.interes_simple.map(x => x.monto), 
              label: 'Interés Simple', 
              borderColor: 'red',
              tension: 0.1
            }
          ]
        };
      },
      error: () => { 
        this.errorMessage = 'Error al realizar el cálculo.'; 
      }
    });
  }
}