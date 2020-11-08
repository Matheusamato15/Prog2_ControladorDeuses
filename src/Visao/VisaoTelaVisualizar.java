package Visao;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import Controle.ControleAcoes;
import Controle.ControleDeus;
import Modelo.ModeloDeus;
import Modelo.ModeloJTable;
import Util.UtilValidacoes;

public class VisaoTelaVisualizar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ModeloJTable modeloTable = new ModeloJTable();
	ControleDeus controleDeus = new ControleDeus();
	ControleAcoes controleTelas = new ControleAcoes();
	UtilValidacoes validacoes = new UtilValidacoes();
	
	private JPanel painelPrincipal;
	private JPanel painelBotoes;

	protected JTable table;
	protected JScrollPane barraDeRolagem;
	protected Button botaoSair;
	protected Button botaoRetornar;
	protected Button botaoEditar;
	protected Button botaoExcluir;

	public VisaoTelaVisualizar() {
		configurarFrame();
		
		configurarPainelPrincipal();
		
		configurarPainelBotoes();
		
		configurarJTable();
		
		configurarScrollPane();
		
		visualizarTable();
		
		configurarBotoes();

	}
	
	private void configurarScrollPane() {
		barraDeRolagem = new JScrollPane();
		painelPrincipal.add(barraDeRolagem, BorderLayout.CENTER);
		barraDeRolagem.setViewportView(table);
		barraDeRolagem.getViewport().setBackground(new Color(0,0,51));
		
	}
	
	private void configurarJTable() {
		table = new JTable(); 
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		table.setBackground(new Color(0,0,51));
		table.setForeground(Color.WHITE);
		table.setSelectionMode(0);
		table.setModel(modeloTable);
	}

	private void configurarPainelPrincipal() {
		painelPrincipal = new JPanel();
		painelPrincipal.setBackground(new Color(0, 0, 51));
		painelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(painelPrincipal);

	}

	private void configurarPainelBotoes() {
		painelBotoes = new JPanel();
		painelBotoes.setBackground(new Color(0, 0, 51));
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
		painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.X_AXIS));

	}
	
	
	private void configurarBotoes() {
		
		botaoEditar = new Button("Realizar Edição");
		montarEdicao();
		configurarLayoutBotoes(botaoEditar);
		painelBotoes.add(botaoEditar);

		botaoExcluir = new Button("Excluir deus");
		configurarLayoutBotoes(botaoExcluir);
		if(controleTelas.saberTela(1)) {
			botaoExcluir.setVisible(false);
		}
		painelBotoes.add(botaoExcluir);
		
		botaoRetornar = new Button("Retornar ao menu");
		configurarLayoutBotoes(botaoRetornar);
		controleTelas.retornarInicio(botaoRetornar, this);
		painelBotoes.add(botaoRetornar);
		
		botaoSair = new Button("Sair do Programa");
		configurarLayoutBotoes(botaoSair );
		controleTelas.sairPrograma(botaoSair, this);
		painelBotoes.add(botaoSair);
		
		
	}

	private void montarEdicao() {
		botaoEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validacoes.validarLinhaSelecionada(table)){
					atualizarFrame();
				}else {
					new ControleAcoes().abrirJanelaBotao(new VisaoTelaAtualizar(getDeusSelecionado()));
					dispose();
				}
				
			}
		});
	}
	
	private void configurarLayoutBotoes(Button botao) {
		botao.setFont(new Font("Dialog", Font.BOLD, 13));
		botao.setForeground(Color.WHITE);
		botao.setBackground(new Color(0, 0, 51));
	}
	
	protected void visualizarTable() {
		modeloTable = new ModeloJTable();
		
		
		for (int i=0; i<controleDeus.executarVisualizarDAO().size(); i++) {
			modeloTable.adicionarLinha(controleDeus.executarVisualizarDAO().get(i));
		}

		
		table.setModel(modeloTable);
	}
	
	protected ModeloDeus getDeusSelecionado() {
		int linha = table.getSelectedRow();
		
		ModeloJTable modelo = (ModeloJTable) table.getModel();
		
		return modelo.obterLinha(linha);
	}
	
	protected int getLinhaSelecionado() {
		int linha = table.getSelectedRow();

		return linha;
	}
	
	private void atualizarFrame() {
		this.setVisible(true);
	}
	
	private void configurarFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);
		setLocationRelativeTo(null);
		setUndecorated(true);
	}

}
