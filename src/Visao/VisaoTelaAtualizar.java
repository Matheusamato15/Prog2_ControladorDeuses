package Visao;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controle.ControleAcoes;
import Controle.ControleDeus;
import Modelo.ModeloDeus;
import Util.UtilValidacoes;

public class VisaoTelaAtualizar extends VisaoTelaInserir {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	
	ControleAcoes controleAcoes = new ControleAcoes();
	ControleDeus controleDeus = new ControleDeus();
	UtilValidacoes validacoes = new UtilValidacoes();
	
	public VisaoTelaAtualizar(ModeloDeus deusParaAtualizar) {
		setTitulo("Bem vindo a edição!!!");
		
		configurarImagem("jupiter.jpg",	"jupiter.jpg");
		
		preencherCamposComDeusParaAtualizar(deusParaAtualizar);
		
		liberarBotoesTravados();	
		
		salvarEdicao(deusParaAtualizar);
	}

	private void salvarEdicao(ModeloDeus deusParaAtualizar) {
		botaoSalvarEdicao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ModeloDeus deus = new ModeloDeus();
				deus.setId(deusParaAtualizar.getId());
				deus.setNomeDeus(textFieldNome.getText());
				deus.setRepresentacaoDeus(textFieldRepresentacao.getText());
				deus.setRegiaoDeus(textFieldRegiao.getText());
				deus.setPersonalidadeDeus(textFieldPersonalidade.getText());
				deus.setHierarquiaDeus(textFieldHierarquia.getText());
				

				if(validacoes.validarCamposForamPreenchidos(textFieldNome, textFieldRegiao, textFieldRepresentacao,
						textFieldPersonalidade, textFieldHierarquia) == 0){
					if(validacoes.validarSeStringTemNumeros(deus)==0) {
						
					}else {
						controleDeus.executarEditarDAO(deus);
					}
					
				}
			}

		});
	}

	private void liberarBotoesTravados() {
		if(!acoes.saberTela(0)){
			botaoSalvar.setVisible(false);
			botaoLimpar.setVisible(false);
			botaoSalvarEdicao.setVisible(true);
		}
	}

	private void preencherCamposComDeusParaAtualizar(ModeloDeus deusParaAtualizar) {
		textFieldNome.setText(deusParaAtualizar.getNomeDeus());
		textFieldRegiao.setText(deusParaAtualizar.getRegiaoDeus());
		textFieldRepresentacao.setText(deusParaAtualizar.getRepresentacaoDeus());
		textFieldPersonalidade.setText(deusParaAtualizar.getPersonalidadeDeus());
		textFieldHierarquia.setText(deusParaAtualizar.getHierarquiaDeus());
	}

}
