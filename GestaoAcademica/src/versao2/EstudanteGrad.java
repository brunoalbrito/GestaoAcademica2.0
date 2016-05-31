/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package versao2;

/**
 *
 * @author Bruno
 */
public class EstudanteGrad extends Estudante {

    private int horasAtividades;

    public EstudanteGrad(long id, String nome, String email, int horasAtividades) {
        super(id, nome, email);
        this.horasAtividades = horasAtividades;
    }

    //Fazer
    @Override
    public String toString() {
        return ("Número: " + id + " Nome: " + nome + " Email: " + email + " Crétido: " + getTotalCreditos() + " Atividades Complementares: " + horasAtividades);
    }

    @Override
    public int getTotalCreditos() {
        int totalDiciplina = 0;
        for (Disciplina d : getDisciplinasMatriculadas()) {
            totalDiciplina += d.getCreditos();
        }
        return totalDiciplina + horasAtividades;
    }
}
