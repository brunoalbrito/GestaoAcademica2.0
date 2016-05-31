/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package versao1;

import javax.swing.JOptionPane;

/**
 *
 * @author Bruno
 */
public class GestaoAcademicaApp1 {

    public static Universidade mackenzie;
    public static String constAluno = "";

    public static void main(String[] args) {
        mackenzie = new Universidade("Mackenzie");
        mackenzie.carregarDadosArquivo("disciplinas-abddcdccebbdcebdbadd.txt", "estudantes-abddcdccebbdcebdbadd.txt", "matriculas-abddcdccebbdcebdbadd.txt");
        //Usuario irá informar sua escolha para o menu
        selection();
    }

    //Menu com as opções 
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
                case 5:
                    System.exit(0);
                    break;
                default:
                    break;
            }

        } while (choose != 5);

    }

//Dados seleciodos pelo menu;
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

    public static void showAlunos() {
        try {
            for (Estudante estudante : mackenzie.getEstudantes()) {
                constAluno += ("Número: " + estudante.getId() + " Nome: " + estudante.getNome() + "\n");
            }
            JOptionPane.showMessageDialog(null, constAluno);

        } catch (Exception e) {
            System.exit(-1);
        }
    }

    public static void allDiciplinasEstudante() {

        try {
            String constInfMatridoAluno = "";
            int credito = 0;
            //Exibe os alunos matriculados
            for (Estudante estudante : mackenzie.getEstudantes()) {
                constAluno += ("Número: " + estudante.getId() + " Nome: " + estudante.getNome() + "\n");
            }

            long matricula = infMatricula();

            //Percorre todos os estudantes
            for (Estudante estudantes : mackenzie.getEstudantes()) {
                //Seleciona apenas o que tiver a matricula informada
                if (estudantes.getId() == matricula) {
                    //Como sabemos a posição em que ele se encontra iremos pegar o tamanho 
                    //do array de diciplinas deste aluno e percorre-la
                    for (Disciplina disciplinas : estudantes.getDisciplinasMatriculadas()) {
                        //A partir do array de diciplinas iremos pegar seu códio
                        constInfMatridoAluno += (disciplinas.getCodigo() + "\n");
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
        return Long.parseLong(JOptionPane.showInputDialog(null, constAluno + " Informe sua matrícula"));
    }

    public static void exibeInfDisplina(String constInfMatridoAluno, int credito) {
        JOptionPane.showMessageDialog(null, constInfMatridoAluno + "\n Total de Crédito: " + credito);
    }

    //------------------------------------------------------------------------------------------------
    public static void getDisciplinas() {
        String listaDisciplina = "";
        try {
            for (Disciplina objDisciplina : mackenzie.getDisciplinas()) {
                listaDisciplina += objDisciplina.getCodigo() + "\n";
            }
            JOptionPane.showMessageDialog(null, listaDisciplina);
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    public static void getInformacoesDisciplina() {
        String codigoDisciplina = "";
        String listaDisciplina = "";
        String listaAlunos = "";
        int numAlunos = 0;

        try {
            for (Disciplina objDisciplina : mackenzie.getDisciplinas()) {
                listaDisciplina += objDisciplina.getCodigo() + "\n";
            }

            codigoDisciplina = JOptionPane.showInputDialog("Digite o Código da Disciplina \n" + listaDisciplina);
            for (int i = 0; i < mackenzie.getEstudantes().size(); i++) {
                for (int j = 0; j < mackenzie.getEstudantes().get(i).getMatriculas().size(); j++) {
                    if (mackenzie.getEstudantes().get(i).getDisciplinasMatriculadas().get(j).getCodigo().equals(codigoDisciplina)) {
                        listaAlunos += mackenzie.getEstudantes().get(i).getId() + " | " + mackenzie.getEstudantes().get(i).getNome() + " | " + mackenzie.getEstudantes().get(i).getEmail() + "\n";
                        numAlunos++;
                    }
                }
            }
            infDisciplina(listaAlunos, numAlunos);
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    public static void infDisciplina(String listaAlunos, int numAlunos) {
        JOptionPane.showMessageDialog(null, listaAlunos + "\n Quantidade de alunos encontrados : " + numAlunos);
    }

    public void infAlunosDisciplinas(String listaAlunos, String numAlunos) {
        JOptionPane.showMessageDialog(null, listaAlunos + "\n Quantidade de alunos encontrados : " + numAlunos);
    }

}
