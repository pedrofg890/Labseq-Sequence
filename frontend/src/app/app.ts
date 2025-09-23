import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { LabSeqService } from './services/labSeqService';

@Component({
  selector: 'app-root',               // This must match index.html
  templateUrl: './app.html',
  standalone: true,                    // Important for standalone component
  imports: [FormsModule, CommonModule, HttpClientModule],
  providers: [LabSeqService]
})
export class App {
  n: number = 0;
  result: string | null = null;
  loading: boolean = false;
  error: string | null = null;

  constructor(private labSeqService: LabSeqService) {}

  calculateLabSeq() {
    this.loading = true;
    this.result = null;
    this.error = null;

    if (this.n == null || isNaN(this.n) || this.n < 0) {
      this.loading = false;
      this.error = 'Index must be non-negative';
      return;
    }

    this.labSeqService.getLabSeq(this.n).subscribe({
      next: value => {
        this.result = value;
        this.loading = false;
      },
      error: err => {
        this.error = err.error || 'Error fetching LabSeq';
        this.loading = false;
      }
    });
  }

  get displayResult(): string {
    if (this.result && this.result.length > 18) {
      // Scientific notation: mantissa . rest e+ exponent
      // Example: 12345678901234567890 -> 1.2345678901234567e+19
      const mantissa = this.result[0];
      const decimals = this.result.slice(1, 7); // 6 decimals after the dot
      const exponent = this.result.length - 1;
      return `${mantissa}.${decimals}e+${exponent}`;
    }
    return this.result || '';
  }
}
