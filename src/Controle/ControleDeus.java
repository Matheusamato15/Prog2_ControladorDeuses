package Controle;

import java.util.List;

import javax.swing.JOptionPane;

import Modelo.ModeloDeus;
import Persistencia.PersistenciaDAODeus;
import Util.UtilRetorno;
import Util.UtilRetornoDado;

public class ControleDeus {
	private PersistenciaDAODeus daoDeus = PersistenciaDAODeus.getInstance();

	
	public void executarIncluirDAO(ModeloDeus deus) {
		UtilRetorno retDeus = daoDeus.incluir(deus);
		
		if (retDeus.isSucesso()) {
			JOptionPane.showMessageDialog(null, " O deus foi inserido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, " O deus NÃO foi inserido com sucesso!", "Erro!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public List<ModeloDeus> executarVisualizarDAO() {
		UtilRetornoDado<List<ModeloDeus>> listarDeus = daoDeus.listarDeus();
			
		return listarDeus.getDado();
	}
	
	public void executarEditarDAO(ModeloDeus deus) {
		UtilRetorno retDeus = daoDeus.editar(deus);
		
		if (retDeus.isSucesso()) {
			JOptionPane.showMessageDialog(null, " O deus foi editado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, " O deus NÃO foi editado com sucesso!", "Erro!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void executarRemoverDAO(ModeloDeus deus) {
		daoDeus.remover(deus);
	}
}
