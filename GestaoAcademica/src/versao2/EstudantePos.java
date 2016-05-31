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
public class EstudantePos extends Estudante {

    private String tema;
    private String orientador;

    public EstudantePos(long id, String nome, String email, String tema, String orientador) {
        super(id, nome, email);
        this.tema = tema;
        this.orientador = orientador;
    }

    //Fazer
    @Override
    public String toString() {
        return ("Número: " + id + " Nome: " + nome + " Email: " + email + " Crétido: " + getTotalCreditos() + " Tema: " + tema + " Orientador: " + orientador);

    }

    @Override
    public int getTotalCreditos() {
        int totalDiciplina = 0;
        for (Disciplina d : getDisciplinasMatriculadas()) {
            totalDiciplina += d.getCreditos();
        }
        return totalDiciplina;
    }
}
