package controller;

import br.com.fatec.Lista;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import model.Curso;
import model.Disciplina;
import model.Inscricao;

public class TabelaDisciCursos {
	
	TabelaInscrDisci[] tabelaHashInscrDisc;
	
	//Contrutor cria um vetor com o tamanho necessário para todos os cursos cadastrados e inicializa a tabela
	public TabelaDisciCursos() {
		CursoDAO cursoDAO = new CursoDAO();
		Lista<Curso> listaCursos = cursoDAO.consultarCursos();
		int qtdCursos = listaCursos.size();
		int maiorCodCurso = Integer.MIN_VALUE;
		//Procura o maior código de curso para saber o tamanho certo para o array
		for (int i = 0; i < qtdCursos; i++) {
			int codCurso;
			try {
				codCurso = listaCursos.get(i).getCodigoCurso();
				if (codCurso > maiorCodCurso) {
					maiorCodCurso = codCurso;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		int tamanho = maiorCodCurso + 1;
		tabelaHashInscrDisc = new TabelaInscrDisci[tamanho];
		inicializarTabelaHash();
	}

	//Inicializa a tabela criando uma lista vazia de tabelas de inscrições nas diciplinas em cada posição do vetor
	private void inicializarTabelaHash() {
		int tamanho = tabelaHashInscrDisc.length;
		for (int i = 0; i < tamanho; i++) {
			tabelaHashInscrDisc[i] = new TabelaInscrDisci();
		}
	}
	
	//Adiciona a inscrição na subtabela da posição do vetor equivalente ao código da disciplina da inscrição
	public void adicionarElemento(Inscricao inscricao) {
		int codDisciplina = inscricao.getCodigoDisciplina();
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		Disciplina disciplina = disciplinaDAO.buscarDisciplina(codDisciplina);
		int codCurso = disciplina.getCodigoCurso();
		tabelaHashInscrDisc[codCurso].adicionarElemento(inscricao);
	}
}
