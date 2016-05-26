package versao2;

import versao1.*;
import versao1.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 41583469
 */
public class Universidade {

    private String nome;
    private ArrayList<Estudante> estudantesArray;
    private ArrayList<Disciplina> disciplinasArray;

    public Universidade(String nome) {
        this.nome = nome;
        this.estudantesArray = new ArrayList();
        this.disciplinasArray = new ArrayList();
    }

    /*Modificar para carregar dados do estudandte*/
    public void carregarDadosArquivo(String nomeArquivoDisciplinas, String nomeArquivoEstudantes, String nomeArquivoMatriculas) {
        //Criando Disciplinas
        try {
            BufferedReader lerDisciplinas = new BufferedReader(new FileReader(nomeArquivoDisciplinas));

            for (String linha; (linha = lerDisciplinas.readLine()) != null;) {
                String dadosDisciplina[] = linha.split(":");
                String codigo = (dadosDisciplina[0]);
                int credito = Integer.parseInt(dadosDisciplina[1]);

                Disciplina disciplina = new Disciplina(codigo, credito);
                disciplinasArray.add(disciplina);

            }
        } catch (Exception e) {
            System.out.println("Error1 " + e.getMessage());
        }
        //Criando Estudante
        try {
            BufferedReader lerEstudante = new BufferedReader(new FileReader(nomeArquivoEstudantes));

            for (String linha; (linha = lerEstudante.readLine()) != null;) {
                String dadosEstudante[] = linha.split(":");
                String tipo = dadosEstudante[3];
                switch (tipo) {
                    case "GRAD":
                        estudantesArray.add(new EstudanteGrad(Long.parseLong(dadosEstudante[0]), dadosEstudante[1], dadosEstudante[2], Integer.parseInt(dadosEstudante[4])));
                        break;
                    case "POS":
                        estudantesArray.add(new EstudantePos(Long.parseLong(dadosEstudante[0]), dadosEstudante[1], dadosEstudante[2], dadosEstudante[4], dadosEstudante[5]));
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error2 " + e.getMessage());
        }
        //Criando Matricula
        try {
            BufferedReader lerMatricula = new BufferedReader(new FileReader(nomeArquivoMatriculas));

            for (String linha; (linha = lerMatricula.readLine()) != null;) {

                String dadosMatricula[] = linha.split(":");
                long idDoc = Long.parseLong(dadosMatricula[0]);
                String codigoDiscDoc = (dadosMatricula[1]);

//                System.out.println("ID: "+idDoc+" CodigoDisciplina: "+codigoDiscDoc);
                Estudante e = null;
                Disciplina d = null;

                for (Estudante percorreE : estudantesArray) {
                    if (percorreE.getId() == idDoc) {
                        e = percorreE;
                        break;
                    }
                }

                for (Disciplina percorreD : disciplinasArray) {
                    if (percorreD.getCodigo().equals(codigoDiscDoc)) {
                        d = percorreD;
                        break;
                    }
                }

//                System.out.println(e);
//                System.out.println(d);
                Matricula m = new Matricula(e, d);
                e.addMatricula(m);
                d.addMatricula(m);

            }
        } catch (Exception error) {
            System.out.println("Error3 " + error.getMessage() + " " + error.getStackTrace());
        }
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Estudante> getEstudantes() {
        return estudantesArray;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinasArray;
    }
}
