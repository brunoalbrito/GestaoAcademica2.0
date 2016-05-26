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
        return ("");
    }

    @Override
    public int getTotalCredito() {
//        IMPLEMENTAR
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
