package Util;


import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Modelo.ModeloDeus;


public class UtilValidacoes {
	
	public int validarCamposForamPreenchidos(JTextField nome, JTextField regiao, JTextField representacao, JTextField personalidade, JTextField hierarquia) {
		if(nome.getText().equals("") ||regiao.getText().equals("") ||representacao.getText().equals("") ||
				personalidade.getText().equals("") ||hierarquia.getText().equals("")) {
			
			JOptionPane.showMessageDialog(null, "Existem campos não preenchidos!", "Erro!", JOptionPane.ERROR_MESSAGE);
			return 1;
		}else {
			return 0;
		}
		
	}
	
	public boolean validarContinuidade() {
		if(JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja prosseguir com o deus selecionado?", "Aviso!", 
				JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
			return true;
		}else {
			return false;
		}
			
	}
	
	public boolean validarLinhaSelecionada(JTable table) {
		if(table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Selecione um deus para poder prosseguir!", "Erro!", JOptionPane.ERROR_MESSAGE);
			return true;
		}else {
			return false;
		}

	}
	
	public int validarSeStringTemNumeros (ModeloDeus deus) {

		if (!deus.getNomeDeus().substring(0).matches("[a-z A-Z à-ú À-Ú]*")|| 
			!deus.getPersonalidadeDeus().substring(0).matches("[a-z A-Z à-ú À-Ú]*")||
			!deus.getRegiaoDeus().substring(0).matches("[a-z A-Z à-ú À-Ú]*")||
			!deus.getRepresentacaoDeus().substring(0).matches("[a-z A-Z à-ú À-Ú]*")||
			!deus.getHierarquiaDeus().substring(0).matches("[a-z A-Z à-ú À-Ú]*")){
			
			JOptionPane.showMessageDialog(null, "Permitidos apenas caracteres alfabéticos!", "Erro!", JOptionPane.ERROR_MESSAGE);
	        return 0;
	    } else {
	  		return 1;
	    }

	}
	
}
