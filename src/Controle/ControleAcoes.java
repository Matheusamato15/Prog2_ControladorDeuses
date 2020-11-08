package Controle;


import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Visao.VisaoTelaPrincipal;

public class ControleAcoes {
	
	public void abrirTelas(JMenuItem menu, JFrame frameNovo, JFrame frameAtual) {
		menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frameNovo.setVisible(true);
				frameAtual.setVisible(false);
			}
		});
	}
	
	public void retornarInicio(Button botao, JFrame tela) {
		botao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VisaoTelaPrincipal inicio = new VisaoTelaPrincipal();
				tela.setVisible(false);
				inicio.setVisible(true);
				
			}
		});
	}
	
	public void sairPrograma(Button botao, JFrame tela) {
		botao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair do programa?", "Aviso!", 
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0 ) {
					tela.setVisible(false);
				} else {
					tela.setVisible(true);
				}
				
			}
		});
	}
	

	
	public void abrirJanelaBotao (JFrame tela){
		tela.setVisible(true);
	}
	
	public boolean saberTela(int numero) {
		if(numero == 1) {
			return true;
		} else {
			return false;
		}
	}
	

}
