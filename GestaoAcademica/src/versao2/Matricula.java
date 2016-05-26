package versao2;

import versao1.*;
import versao1.*;


/**
 *
 * @author 41583469
 */
public class Matricula {
    private Estudante estudante;
    private Disciplina disciplina;
    
    public Matricula(Estudante estudante, Disciplina disciplina){
        this.estudante = estudante;
        this.disciplina = disciplina;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }
    
}
