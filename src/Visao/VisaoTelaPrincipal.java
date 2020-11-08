package Visao;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import Controle.ControleAcoes;
import Imagem.ImagemCriadorImagem;

public class VisaoTelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	VisaoTelaInserir inserir = new VisaoTelaInserir();
	VisaoTelaVisualizar visualizar = new VisaoTelaVisualizar();
	VisaoTelaExcluir excluir = new VisaoTelaExcluir();
	ControleAcoes acoes = new ControleAcoes();
	
	private JPanel painelPrincipal;
	private JPanel painelMenu;
	private JMenuBar barraMenu;
	private JPanel painelImagem;
	private JLabel labelImagem;
	private JMenuItem menuInserir;
	private JMenuItem menuDeletar;
	private JMenuItem menuVisualizar;

	public VisaoTelaPrincipal() {
		
		configurarPainel();
		
		configurarFrame();
		
		configurarPainelMenu();
		
		configurarMenu();		
		
		configurarImagem();
	}



	private void configurarPainel() {
		painelPrincipal = new JPanel();
		painelPrincipal.setBackground(new Color(0, 0, 51));
		painelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(painelPrincipal);

	}

	private void configurarMenu() {
		painelMenu.setLayout(new BorderLayout(0, 0));
		barraMenu = new JMenuBar();
		painelMenu.add(barraMenu);
		
		menuInserir = new JMenuItem("Inserir");
		acoes.abrirTelas(menuInserir, inserir, this);
		barraMenu.add(menuInserir);
		
		menuDeletar = new JMenuItem("Deletar");
		acoes.abrirTelas(menuDeletar, excluir, this);
		barraMenu.add(menuDeletar);
				
		menuVisualizar = new JMenuItem("Visualizar");
		acoes.abrirTelas(menuVisualizar, visualizar, this);
		barraMenu.add(menuVisualizar);
	
	}

	private void configurarPainelMenu() {
		painelMenu = new JPanel();
		painelMenu.setBackground(new Color(0, 0, 51));
		painelPrincipal.add(painelMenu, BorderLayout.NORTH);
		
	}

	private void configurarImagem() {
		painelImagem = new JPanel();
		painelImagem.setBackground(Color.RED);
		painelPrincipal.add(painelImagem, BorderLayout.CENTER);
		
		labelImagem = new JLabel(new ImagemCriadorImagem().imagemFundo("anubis.jpg", "anubis.jpg"));
		painelImagem.add(labelImagem);
	}

	private void configurarFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
		setResizable(false);
		setSize(440,500);
		setLocationRelativeTo(null);
		
	}
}
