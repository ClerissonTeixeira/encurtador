import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-url-shortener',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './url-shortener.component.html',
  styleUrl: './url-shortener.component.css'
})
export class UrlShortenerComponent {
  urlInput: string = '';
  shortUrl: string | null = null;
  errorMessage: string | null = null;

  constructor(private http: HttpClient) {}

  shortenUrl(){
    this.shortUrl = null;
    this.errorMessage = null;

    const url = this.urlInput.trim();
    if(!url) {
      this.errorMessage = 'Por favor, insira uma URL válida';
      return;
    }

    this.http.post('http://localhost:8080/shorten', url, { responseType: 'text'})
    .subscribe({
      next: (response) => {
        this.shortUrl = response;
      },
      error: () => {
        this.errorMessage = 'Erro ao encurtar a URL';
      }
    })
  }

}
