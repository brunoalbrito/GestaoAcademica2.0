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
public class EstudanteGrad extends Estudante{
    private int horasAtividades;

    public EstudanteGrad(long id, String nome, String email, int horasAtividades) {
        super(id, nome, email);
        this.horasAtividades = horasAtividades;
    }
    
    //Fazer
    @Override
    public String toString(){
        return ("");
    }

    @Override
    public int getTotalCredito() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
