/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package versao2;

import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno Albuquerque
 * @author Bruno Tavares 41555287
 * @author Leonardo Ferreira
 *
 */
public class GestaoAcademicaApp2 {

    private static Universidade universidade;
    private static String nomeUniversidadeEsperado;

    public static void main(String[] args) {
        String nomeArquivoDisciplinas = "disciplinas-" + "abbcbbadbbccdbebdedc" + ".txt";
        String nomeArquivoEstudantes = "estudantes-" + "abbcbbadbbccdbebdedc" + ".txt";
        String nomeArquivoMatriculas = "matriculas-" + "abbcbbadbbccdbebdedc" + ".txt";

        nomeUniversidadeEsperado = "Universidade " + "Teste";
        universidade = new Universidade(nomeUniversidadeEsperado);
        universidade.carregarDadosArquivo(nomeArquivoDisciplinas, nomeArquivoEstudantes, nomeArquivoMatriculas);
        selection();
    }

    //Menu;
    public static int choose() {
        int resp = 0;
        try {
            resp = Integer.parseInt(JOptionPane.showInputDialog("Informe sua escolha:\n"
                    + "1 - Listar os números, nomes e créditos de todos os estudantes \n"
                    + "2 - Listar o código de todas as diciplicas \n"
                    + "3 - Listar todas as informações dos estudande matriculados em uma determina disciplica \n"
                    + "4 - Listar todas as informações de uma diciplica em que um determinado estudante está matriculado\n"
                    + "5 - Sair"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Valores não informados corretamente");
        }

        return resp;
    }

    //---------
    //Opção selecionada do Menu
    public static void selection() {
        int choose = 0;
        do {
            choose = choose();
            switch (choose) {
                case 1:
                    showAlunos();
                    break;
                case 2:
                    getDisciplinas();
                    break;
                case 3:
                    getInformacoesDisciplina();
                    break;
                case 4:
                    allDiciplinasEstudante();
                    break;
            }

        } while (choose != 5);
        JOptionPane.showMessageDialog(null, "Fechando Aplicação");
    }

    //---------
    //1. ---------Aluno
    public static void showAlunos() {
        List<Estudante> estudantes = universidade.getEstudantes();
        String acumText = "";
        for (Estudante estudanteArray : estudantes) {
            int id = (int) estudanteArray.getId();
            Estudante estudanteEncontrado = findEstudanteById(id, estudantes);
            if ((estudanteEncontrado instanceof EstudanteGrad)) {
                EstudanteGrad AlunoGrad = (EstudanteGrad) estudanteEncontrado;
                acumText += (AlunoGrad.toString() + "\n");
            }
            if (!(estudanteEncontrado instanceof EstudanteGrad)) {
                EstudantePos AlunoPos = (EstudantePos) estudanteEncontrado;
                acumText += (AlunoPos.toString() + "\n");
            }
        }
        JOptionPane.showMessageDialog(null, acumText);
    }

    public static Estudante findEstudanteById(int id, List<Estudante> estudantes) {
        Estudante estudanteEncontrado = null;
        for (Estudante estudante : estudantes) {
            if (id == estudante.getId()) {
                estudanteEncontrado = estudante;
                break;
            }
        }
        return estudanteEncontrado;
    }

    //---------
    //2. --------Disciplina
    public static void getDisciplinas() {
        String listaDisciplina = "";
        try {
            for (Disciplina objDisciplina : universidade.getDisciplinas()) {
                listaDisciplina += objDisciplina.getCodigo() + "\n";
            }
            JOptionPane.showMessageDialog(null, listaDisciplina);
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    public static Disciplina findDisciplinaByCodigo(String codigo, List<Disciplina> disciplinas) {
        Disciplina disciplinaEncontrada = null;
        for (Disciplina disciplina : disciplinas) {
            if (codigo.equals(disciplina.getCodigo())) {
                disciplinaEncontrada = disciplina;
                break;
            }
        }
        return disciplinaEncontrada;
    }

    //---------
    //3. Todos os Alunos por disciplinas 
    public static void getInformacoesDisciplina() {
        String codigoDisciplina = "";
        String listaDisciplina = "";
        String listaAlunos = "";
        int numAlunos = 0;

        try {
            for (Disciplina objDisciplina : universidade.getDisciplinas()) {
                listaDisciplina += objDisciplina.getCodigo() + "\n";
            }
            String acumText = "";
            codigoDisciplina = JOptionPane.showInputDialog("Digite o Código da Disciplina \n" + listaDisciplina);
            for (int i = 0; i < universidade.getEstudantes().size(); i++) {

                for (int j = 0; j < universidade.getEstudantes().get(i).getMatriculas().size(); j++) {
                    if (universidade.getEstudantes().get(i).getDisciplinasMatriculadas().get(j).getCodigo().equals(codigoDisciplina)) {
                        if ((universidade.getEstudantes().get(i) instanceof EstudanteGrad)) {
                            EstudanteGrad AlunoGrad = (EstudanteGrad) universidade.getEstudantes().get(i);
                            acumText += (AlunoGrad.toString() + "\n");
                        }
                        if (!(universidade.getEstudantes().get(i) instanceof EstudanteGrad)) {
                            EstudantePos AlunoPos = (EstudantePos) universidade.getEstudantes().get(i);
                            acumText += (AlunoPos.toString() + "\n");
                        }
                        numAlunos++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, acumText);
            infDisciplina(numAlunos);
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    public static void infDisciplina(int numAlunos) {
        JOptionPane.showMessageDialog(null, "\n Quantidade de alunos encontrados : " + numAlunos);
    }

    //---------
    //4. Listar todas as informações de uma diciplica em que um determinado estudante está matriculado
    public static void allDiciplinasEstudante() {
        try {
            String constInfMatridoAluno = "";
            String constAluno = "";
            int credito = 0;

            long matricula = infMatricula();

            //Percorre todos os estudantes
            for (Estudante estudantes : universidade.getEstudantes()) {
                //Seleciona apenas o que tiver a matricula informada
                if (estudantes.getId() == matricula) {
                    //Como sabemos a posição em que ele se encontra iremos pegar o tamanho 
                    //do array de diciplinas deste aluno e percorre-la
                    for (Disciplina disciplinas : estudantes.getDisciplinasMatriculadas()) {
                        //A partir do array de diciplinas iremos pegar seu códio
                        constInfMatridoAluno += ("Nome da Disciplina: " + disciplinas.getCodigo() + " Crédito: " + disciplinas.getCreditos() + "\n");
                        //Somar os créditos de cada disciplina
                        credito += disciplinas.getCreditos();
                    }
                }
            }
            exibeInfDisplina(constInfMatridoAluno, credito);
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    public static long infMatricula() {
        String constAluno = "";
        //Exibe os alunos matriculados
        for (Estudante estudante : universidade.getEstudantes()) {
            constAluno += estudante.toString() + "\n";
        }
        return Long.parseLong(JOptionPane.showInputDialog(null, constAluno + " Informe sua matrícula"));
    }

    public static void exibeInfDisplina(String constInfMatridoAluno, int credito) {
        JOptionPane.showMessageDialog(null, constInfMatridoAluno + "\n Total de Crédito: " + credito);
    }

    //--------
}
