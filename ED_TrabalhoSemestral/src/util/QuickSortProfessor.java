package util;

import model.Professor;

public class QuickSortProfessor {
	
	//Faz o quick sort de um vetor de professores da pontuação maior para a menor 
	public static Professor[] quickSort(Professor[] vet, int inicio, int fim) {
		if (fim > inicio) {
			int posicaoPivoFixo;
			posicaoPivoFixo = dividir(vet, inicio, fim);
			quickSort(vet, inicio, posicaoPivoFixo - 1);
			quickSort(vet, posicaoPivoFixo + 1, fim);
		}
		return vet;
	}
	
	private static int dividir(Professor[] vet, int inicio, int fim) {
		Professor pivo = vet[inicio];
		int ponteiroEsquerda = inicio + 1;
		int ponteiroDireita = vet.length - 1;
		
		while (ponteiroEsquerda <= ponteiroDireita) {
			while (ponteiroEsquerda <= ponteiroDireita && vet[ponteiroEsquerda].getQtdPontos() > pivo.getQtdPontos()) {
				ponteiroEsquerda++;
			}
			while (ponteiroDireita >= ponteiroEsquerda && vet[ponteiroDireita].getQtdPontos() <= pivo.getQtdPontos()) {
				ponteiroDireita--;
			}
			if (ponteiroEsquerda < ponteiroDireita) {
				trocar(vet, ponteiroEsquerda, ponteiroDireita);
			}
		}
		trocar(vet, inicio, ponteiroDireita);
		return ponteiroDireita;
	}
	
	private static void trocar(Professor[] vet, int i, int j) {
		Professor aux = vet[i];
		vet[i] = vet[j];
		vet[j] = aux;
	}

}
