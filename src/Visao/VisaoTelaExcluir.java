package Visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controle.ControleAcoes;
import Controle.ControleDeus;
import Util.UtilValidacoes;

public class VisaoTelaExcluir extends VisaoTelaVisualizar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ControleAcoes controleAcoes = new ControleAcoes();
	ControleDeus controleDeus = new ControleDeus();
	UtilValidacoes validacoes = new UtilValidacoes();
	
	public VisaoTelaExcluir() {
		
		liberarBotoesTravados();
		
		excluirDeus();
		
		
	}

	private void excluirDeus() {
		botaoExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacoes.validarLinhaSelecionada(table)) {
					atualizarFrame();
				} else {
					if(validacoes.validarContinuidade()) {
						controleDeus.executarRemoverDAO(getDeusSelecionado());
						atualizarFrame();
					}
				}

			}
		});
	}

	private void liberarBotoesTravados() {
		if(!controleAcoes.saberTela(0)) {
			botaoExcluir.setVisible(true);
			botaoEditar.setVisible(false);
			
		}
	}
	
	private void atualizarFrame() {
		this.setVisible(true);
		visualizarTable();
	}

}
