package lp1v1;

import java.util.ArrayList;

/**
 *
 * @author 41583469
 */
public class Disciplina {

    private String codigo;
    private int creditos;
    private ArrayList<Matricula> matriculas;

    public Disciplina(String codigo, int creditos) {
        this.codigo = codigo;
        this.creditos = creditos;
        this.matriculas = new ArrayList();
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public ArrayList<Matricula> getMatriculas() {
        return matriculas;
    }

    public void addMatricula(Matricula matricula) {
        //System.out.println("Adicinou nova matricula " + matricula.getEstudante().getNome());
        matriculas.add(matricula);
    }

    public ArrayList<Estudante> getEstudantesMatriculados() {
        ArrayList<Estudante> estudantes = new ArrayList<>();
        for(Matricula m:matriculas){
                estudantes.add(m.getEstudante());
            }
        return estudantes;
    }
}
