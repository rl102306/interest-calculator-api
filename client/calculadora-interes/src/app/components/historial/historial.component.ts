
import { Component, OnInit } from '@angular/core';
import { CalculoService } from '../../services/calculo.service';

@Component({
  selector: 'app-historial',
  templateUrl: './historial.component.html'
})
export class HistorialComponent implements OnInit {
  historial: any[] = [];

  constructor(private calculoService: CalculoService) {}

  ngOnInit() {
    this.calculoService.obtenerHistorial().subscribe(data => {
      this.historial = data;
    });
  }
}
