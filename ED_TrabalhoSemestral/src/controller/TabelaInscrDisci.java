package controller;

import br.com.fatec.Lista;
import dao.DisciplinaDAO;
import model.Disciplina;
import model.Inscricao;

public class TabelaInscrDisci {
	
	Lista<Inscricao>[] tabelaHashInscricoes;
	
	//Contrutor cria um vetor com o tamanho necessário para todas as disciplinas cadastradas e inicializa a tabela
	@SuppressWarnings("unchecked")
	public TabelaInscrDisci() {
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		Lista<Disciplina> listaDisciplinas = disciplinaDAO.consultarDisciplinas();
		int qtdDisciplinas = listaDisciplinas.size();
		int maiorCodDisciplina = Integer.MIN_VALUE;
		//Procura o maior código de disciplina para saber o tamanho certo para o array
		for (int i = 0; i < qtdDisciplinas; i++) {
			int codDisciplina;
			try {
				codDisciplina = listaDisciplinas.get(i).getCodigoDisciplina();
				if (codDisciplina > maiorCodDisciplina) {
					maiorCodDisciplina = codDisciplina;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		int tamanho = maiorCodDisciplina + 1;
		tabelaHashInscricoes = new Lista[tamanho];
		inicializarTabelaHash();
	}

	//Inicializa a tabela criando uma lista vazia de inscrições em cada posição do vetor
	private void inicializarTabelaHash() {
		int tamanho = tabelaHashInscricoes.length;
		for (int i = 0; i < tamanho; i++) {
			tabelaHashInscricoes[i] = new Lista<Inscricao>();
		}
	}
	
	//Adiciona a inscrição na posição do vetor equivalente ao código da disciplina da inscrição
	public void adicionarElemento(Inscricao inscricao) {
		int posicao = inscricao.hashCode();
		try {
			tabelaHashInscricoes[posicao].addLast(inscricao);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	//Função hash para retornar o código do curso da discplina 
	@Override
	public int hashCode() {
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		
		//Procura uma posição na tabela que não esteja com uma lista vazia
		int pos = 0;
		while (tabelaHashInscricoes[pos].isEmpty()) {
			pos++;
		}
		
		//Retorna -1 caso todas as posições estejam com listas vazias
		if (pos >= tabelaHashInscricoes.length) {
			return - 1;
		}
		
		//Tenta pegar o código da disciplina na posição encontrada e usa isso para retornar o código do curso
		int codDisciplina = 0;
		try {
			codDisciplina = tabelaHashInscricoes[pos].get(0).getCodigoDisciplina();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		Disciplina disciplina = disciplinaDAO.buscarDisciplina(codDisciplina);
		return disciplina.getCodigoCurso();
	}
}
