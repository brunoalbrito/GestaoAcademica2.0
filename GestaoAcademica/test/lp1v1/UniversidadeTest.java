package lp1v1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import junit.framework.Assert;
import org.junit.Test;

public class UniversidadeTest {

    private final Universidade universidade;
    private final String nomeUniversidadeEsperado;
    private String[][] disciplinasArray;
    private String[][] estudantesArray;
    private int[] matriculasPorEstudante;
    private int[] matriculasPorDisciplina;
    private String[][] matriculasArray;

    public UniversidadeTest() throws FileNotFoundException {
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

    public void criarArquivoEstudantes(String nomeArquivo) throws FileNotFoundException {
        estudantesArray = new String[3][];
        String matricula;
        matricula = randomString("0123456789", 8);
        estudantesArray[0] = new String[]{matricula, "Jonatas", matricula + "@mackenzista.com.br"};
        matricula = randomString("0123456789", 8);
        estudantesArray[1] = new String[]{matricula, "Lucas", matricula + "@mackenzista.com.br"};
        matricula = randomString("0123456789", 8);
        estudantesArray[2] = new String[]{matricula, "Rubens", matricula + "@mackenzista.com.br"};

        PrintWriter pw = new PrintWriter(nomeArquivo);
        for (String[] estudante : estudantesArray) {
            pw.println(estudante[0] + ":" + estudante[1] + ":" + estudante[2]);
        }
        pw.close();
    }

    public void criarArquivoDisciplinas(String nomeArquivo) throws FileNotFoundException {
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

    public void criarArquivoMatriculas(String nomeArquivo) throws FileNotFoundException {
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

    public String randomString(String chars, int length) {
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
}
