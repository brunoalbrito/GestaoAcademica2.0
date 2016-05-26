package lp1v2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import versao2.Disciplina;
import versao2.Estudante;
import versao2.EstudanteGrad;
import versao2.EstudantePos;
import versao2.Matricula;
import versao2.Universidade;

public class UniversidadeTestV2 {

    private static Universidade universidade;
    private static String nomeUniversidadeEsperado;
    private static String[][] disciplinasArray;
    private static String[][] estudantesArray;
    private static String[][] matriculasArray;
    private static int[] matriculasPorEstudante;
    private static int[] matriculasPorDisciplina;

    @BeforeClass
    public static void initialize() throws FileNotFoundException {
        String testeId = randomString("abcde", 20);
        String nomeArquivoDisciplinas = "disciplinas-" + testeId + ".txt";
        String nomeArquivoEstudantes = "estudantes-" + testeId + ".txt";
        String nomeArquivoMatriculas = "matriculas-" + testeId + ".txt";

        criarArquivoEstudantes(nomeArquivoEstudantes);
        criarArquivoDisciplinas(nomeArquivoDisciplinas);
        criarArquivoMatriculas(nomeArquivoMatriculas);

        nomeUniversidadeEsperado = "Universidade " + testeId;
        universidade = new Universidade(nomeUniversidadeEsperado);
        universidade.carregarDadosArquivo(nomeArquivoDisciplinas, nomeArquivoEstudantes, nomeArquivoMatriculas);
    }

    public static void criarArquivoEstudantes(String nomeArquivo) throws FileNotFoundException {
        estudantesArray = new String[3][];
        String matricula;
        matricula = randomString("0123456789", 8);
        estudantesArray[0] = new String[]{matricula, "Jonatas", matricula + "@mackenzista.com.br", "GRAD",  "120"};
        matricula = randomString("0123456789", 8);
        estudantesArray[1] = new String[]{matricula, "Lucas", matricula + "@mackenzista.com.br", "POS", "Controle de robôs", "Furukawa"};
        matricula = randomString("0123456789", 8);
        estudantesArray[2] = new String[]{matricula, "Rubens", matricula + "@mackenzista.com.br", "GRAD", "220"};

        PrintWriter pw = new PrintWriter(nomeArquivo);
        for (String[] estudante : estudantesArray) {
            pw.print(estudante[0] + ":" + estudante[1] + ":" + estudante[2]);
            if (estudante[3].equals("GRAD")) {
                pw.print(":" + estudante[3] + ":" + estudante[4]);
            } else if (estudante[3].equals("POS")) {
                pw.print(":" + estudante[3] + ":" + estudante[4] + ":" + estudante[5]);
            }
            pw.println();
        }
        pw.close();
    }

    public static void criarArquivoDisciplinas(String nomeArquivo) throws FileNotFoundException {
        disciplinasArray = new String[3][];
        disciplinasArray[0] = new String[]{"ENEC1544", "40"};
        disciplinasArray[1] = new String[]{"ENEC1426", "40"};
        disciplinasArray[2] = new String[]{"ENEC1000", "20"};

        PrintWriter pw = new PrintWriter(nomeArquivo);
        for (String[] disciplina : disciplinasArray) {
            pw.println(disciplina[0] + ":" + disciplina[1]);
        }
        pw.close();
    }

    public static void criarArquivoMatriculas(String nomeArquivo) throws FileNotFoundException {
        matriculasArray = new String[7][];
        matriculasPorEstudante = new int[]{3, 2, 2};
        matriculasPorDisciplina = new int[]{2, 3, 2};
        matriculasArray[0] = new String[]{estudantesArray[0][0], disciplinasArray[0][0]};
        matriculasArray[1] = new String[]{estudantesArray[0][0], disciplinasArray[1][0]};
        matriculasArray[2] = new String[]{estudantesArray[0][0], disciplinasArray[2][0]};
        matriculasArray[3] = new String[]{estudantesArray[1][0], disciplinasArray[1][0]};
        matriculasArray[4] = new String[]{estudantesArray[1][0], disciplinasArray[0][0]};
        matriculasArray[5] = new String[]{estudantesArray[2][0], disciplinasArray[1][0]};
        matriculasArray[6] = new String[]{estudantesArray[2][0], disciplinasArray[2][0]};
        PrintWriter pw = new PrintWriter(nomeArquivo);
        for (String[] matricula : matriculasArray) {
            pw.println(matricula[0] + ":" + matricula[1]);
        }
        pw.close();
    }

    public static String randomString(String chars, int length) {
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return buf.toString();
    }

    public Estudante findEstudanteById(int id, List<Estudante> estudantes) {
        Estudante estudanteEncontrado = null;
        for (Estudante estudante : estudantes) {
            if (id == estudante.getId()) {
                estudanteEncontrado = estudante;
                break;
            }
        }
        return estudanteEncontrado;
    }

    public Disciplina findDisciplinaByCodigo(String codigo, List<Disciplina> disciplinas) {
        Disciplina disciplinaEncontrada = null;
        for (Disciplina disciplina : disciplinas) {
            if (codigo.equals(disciplina.getCodigo())) {
                disciplinaEncontrada = disciplina;
                break;
            }
        }
        return disciplinaEncontrada;
    }

    @Test
    public void testNomeUniversidade() {
        String nomeObtido = universidade.getNome();
        Assert.assertEquals(nomeUniversidadeEsperado, nomeObtido);
    }

    @Test
    public void testEstudantes() {
        List<Estudante> estudantes = universidade.getEstudantes();
        int numeroEsperado = estudantesArray.length;
        int numeroObtido = estudantes.size();
        Assert.assertEquals(numeroEsperado, numeroObtido);
        for (String[] estudanteArray : estudantesArray) {
            int id = Integer.parseInt(estudanteArray[0]);
            String nomeEsperado = estudanteArray[1];
            String emailEsperado = estudanteArray[2];
            Estudante estudanteEncontrado = findEstudanteById(id, estudantes);
            Assert.assertNotNull(estudanteEncontrado);
            Assert.assertEquals(nomeEsperado, estudanteEncontrado.getNome());
            Assert.assertEquals(emailEsperado, estudanteEncontrado.getEmail());
            if (estudanteArray[3].equals("GRAD")) {
                Assert.assertTrue(estudanteEncontrado instanceof EstudanteGrad);
            } else if (estudanteArray[3].equals("POS")) {
                Assert.assertTrue(estudanteEncontrado instanceof EstudantePos);
            }
        }
    }

    @Test
    public void testDisciplinas() {
        List<Disciplina> disciplinas = universidade.getDisciplinas();
        int numeroEsperado = disciplinasArray.length;
        int numeroObtido = disciplinas.size();
        Assert.assertEquals(numeroEsperado, numeroObtido);
        for (String[] disciplinaArray : disciplinasArray) {
            String codigo = disciplinaArray[0];
            int creditos = Integer.parseInt(disciplinaArray[1]);
            Disciplina disciplinaEncontrada = findDisciplinaByCodigo(codigo, disciplinas);
            Assert.assertNotNull(disciplinaEncontrada);
            Assert.assertEquals(creditos, disciplinaEncontrada.getCreditos());
        }
    }

    @Test
    public void testMatriculas() {
        List<Estudante> estudantes = universidade.getEstudantes();
        List<Disciplina> disciplinas = universidade.getDisciplinas();
        List<Matricula> matriculas =  null;
        
        for (int i=0; i<estudantesArray.length; i++) {
            String[] estudanteArray = estudantesArray[i];
            int id = Integer.parseInt(estudanteArray[0]);
            Estudante estudante = findEstudanteById(id, estudantes);
            Assert.assertEquals(matriculasPorEstudante[i], estudante.getMatriculas().size());
        }
        
        for (int i=0; i<disciplinasArray.length; i++) {
            String[] disciplinaArray = disciplinasArray[i];
            String codigo = disciplinaArray[0];
            Disciplina disciplina = findDisciplinaByCodigo(codigo, disciplinas);
            Assert.assertEquals(matriculasPorDisciplina[i], disciplina.getMatriculas().size());
        }
        
        for (String[] matriculaArray : matriculasArray) {
            int idEstudante = Integer.parseInt(matriculaArray[0]);
            String codigoDisciplina = matriculaArray[1];
            Estudante estudante = findEstudanteById(idEstudante, estudantes);
            Disciplina disciplina = findDisciplinaByCodigo(codigoDisciplina, disciplinas);
            
            Matricula matriculaEncontradaInEstudante = null;
            matriculas = estudante.getMatriculas();
            for (Matricula matricula : matriculas) {
                if (codigoDisciplina.equals(matricula.getDisciplina().getCodigo()) && idEstudante == matricula.getEstudante().getId()) {
                    matriculaEncontradaInEstudante = matricula;
                    break;
                }
            }
            Assert.assertNotNull(matriculaEncontradaInEstudante);
            
            Matricula matriculaEncontradaInDisciplina = null;
            matriculas = disciplina.getMatriculas();
            for (Matricula matricula : matriculas) {
                if (codigoDisciplina.equals(matricula.getDisciplina().getCodigo()) && idEstudante == matricula.getEstudante().getId()) {
                    matriculaEncontradaInDisciplina = matricula;
                    break;
                }
            }
            Assert.assertNotNull(matriculaEncontradaInDisciplina);
        }
    }
    
    @Test
    public void testCreditos() {
        List<Estudante> estudantes = universidade.getEstudantes();
        List<Disciplina> disciplinas = universidade.getDisciplinas();
        List<Matricula> matriculas =  null;
        
        for (int i=0; i<estudantesArray.length; i++) {
            String[] estudanteArray = estudantesArray[i];
            int id = Integer.parseInt(estudanteArray[0]);
            String tipo = estudanteArray[3];
            int creditosDisciplinas = 0;
            int totalCreditosEsperados = 0;
            
            for (String[] matriculaArray: matriculasArray) {
                int idAlunoMatriculado = Integer.parseInt(matriculaArray[0]);
                String codigoDisciplina = matriculaArray[1];
                if (id == idAlunoMatriculado) {
                    for (String[] disciplinaArray: disciplinasArray) {
                        if (codigoDisciplina.equals(disciplinaArray[0])) {
                            creditosDisciplinas += Integer.parseInt(disciplinaArray[1]);
                        }
                    }
                }
            }
            
            Estudante estudante = findEstudanteById(id, estudantes);
            if (tipo.equals("GRAD")) {
                int horas = Integer.parseInt(estudanteArray[4]);
                totalCreditosEsperados = horas + creditosDisciplinas;
            } else if (tipo.equals("POS")) {
                totalCreditosEsperados = creditosDisciplinas;
            }
            
            Assert.assertEquals(totalCreditosEsperados, estudante.getTotalCreditos());
        }        
    }
}
