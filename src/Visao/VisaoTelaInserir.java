package Visao;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controle.ControleDeus;
import Controle.ControleAcoes;
import Imagem.ImagemCriadorImagem;
import Modelo.ModeloDeus;
import Util.UtilValidacoes;
import net.miginfocom.swing.MigLayout;

public class VisaoTelaInserir extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ControleAcoes acoes = new ControleAcoes();
	ControleDeus controleDeus = new ControleDeus();
	UtilValidacoes validacoes = new UtilValidacoes();

	private JPanel painelPrincipal;
	private JPanel painelDados;
	private JPanel painelTitulo;
	private JPanel painelImagem;
	private JPanel painelBotoes;

	private JLabel labelNome;
	private JLabel labelRegiao;
	private JLabel labelRepresentao;
	private JLabel labelPersonalidade;
	private JLabel labelHierarquia;
	private JLabel labelTitulo;
	private JLabel labelImagem;

	protected JTextField textFieldNome;
	protected JTextField textFieldRegiao;
	protected JTextField textFieldRepresentacao;
	protected JTextField textFieldPersonalidade;
	protected JTextField textFieldHierarquia;
	
	protected Button botaoRetornar;
	protected Button botaoSair;
	protected Button botaoSalvar;
	protected Button botaoLimpar;
	protected Button botaoSalvarEdicao;

	public VisaoTelaInserir() {
		configurarFrame();

		configurarPainelPrincipal();

		configurarPainelTitulo();
		
		configurarPainelDados();

		configurarPainelBotoes();

		configurarLabel();

		configurarTextField();

		configurarBotoes();

		configurarImagem("poseidon.jpg", "poseidon.jpg");

		
	}

	private void configurarPainelPrincipal() {
		painelPrincipal = new JPanel();
		painelPrincipal.setBackground(new Color(0, 0, 51));
		painelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(painelPrincipal);

	}

	private void configurarPainelDados() {
		painelDados = new JPanel();
		painelDados.setBackground(new Color(0, 0, 51));
		painelPrincipal.add(painelDados, BorderLayout.WEST);
		painelDados.setLayout(new MigLayout("", "[71.00px,grow][84.00px]", "[20px][20px][20px][20px][20px]"));

	}

	private void configurarPainelTitulo() {
		painelTitulo = new JPanel();
		painelTitulo.setBackground(new Color(0, 0, 51));
		painelPrincipal.add(painelTitulo, BorderLayout.NORTH);
	}

	private void configurarPainelBotoes() {
		painelBotoes = new JPanel();
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
		painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.X_AXIS));

	}
	
	private void configurarTextField() {
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		painelDados.add(textFieldNome, "cell 1 0,alignx left,aligny center");

		textFieldRegiao = new JTextField();
		textFieldRegiao.setColumns(10);
		painelDados.add(textFieldRegiao, "cell 1 1,growx,aligny center");

		textFieldRepresentacao = new JTextField();
		textFieldRepresentacao.setColumns(10);
		painelDados.add(textFieldRepresentacao, "cell 1 2,growx,aligny center");

		textFieldPersonalidade = new JTextField();
		textFieldPersonalidade.setColumns(10);
		painelDados.add(textFieldPersonalidade, "cell 1 3,growx,aligny center");

		textFieldHierarquia = new JTextField();
		textFieldHierarquia.setColumns(10);
		painelDados.add(textFieldHierarquia, "cell 1 4,growx,aligny center");

	}


	private void configurarBotoes() {
		
		botaoSalvar = new Button("Salvar deus");
		configurarLayoutBotoes(botaoSalvar);
		painelBotoes.add(botaoSalvar);
		
		botaoLimpar = new Button("Limpar Campos");
		configurarLayoutBotoes(botaoLimpar);
		painelBotoes.add(botaoLimpar);
		
		botaoSalvarEdicao = new Button("Salvar Edição");
		configurarLayoutBotoes(botaoSalvarEdicao);
		if(acoes.saberTela(1)) {
			botaoSalvarEdicao.setVisible(false);
		}
		painelBotoes.add(botaoSalvarEdicao);
		
		botaoRetornar = new Button("Retornar");
		configurarLayoutBotoes(botaoRetornar);
		acoes.retornarInicio(botaoRetornar, this);
		painelBotoes.add(botaoRetornar);
		
		botaoSair = new Button("Sair");
		configurarLayoutBotoes(botaoSair);
		acoes.sairPrograma(botaoSair, this);
		painelBotoes.add(botaoSair);
		
		salvarDados();
		limparCampos();
	}

	private void limparCampos() {
		botaoLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setText("");
				textFieldPersonalidade.setText("");
				textFieldRegiao.setText("");
				textFieldRepresentacao.setText("");
				textFieldHierarquia.setText("");
				
			}
		});
		
	}

	private void salvarDados() {
		botaoSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ModeloDeus deus = new ModeloDeus();
				deus.setNomeDeus(textFieldNome.getText());
				deus.setRepresentacaoDeus(textFieldRepresentacao.getText());
				deus.setRegiaoDeus(textFieldRegiao.getText());
				deus.setPersonalidadeDeus(textFieldPersonalidade.getText());
				deus.setHierarquiaDeus(textFieldHierarquia.getText());
				
				if(validacoes.validarCamposForamPreenchidos(textFieldNome, textFieldRegiao, textFieldRepresentacao, 
						textFieldPersonalidade, textFieldHierarquia) == 0) {
					if(validacoes.validarSeStringTemNumeros(deus) == 0) {
						
					} else {
						
						controleDeus.executarIncluirDAO(deus);
					}
					
				}
			
			}
		});
	}


	private void configurarLayoutBotoes(Button botao) {
		botao.setFont(new Font("Dialog", Font.BOLD, 13));
		botao.setForeground(Color.WHITE);
		botao.setBackground(new Color(0, 0, 51));
	}

	
	private void configurarLabel() {
		labelTitulo = new JLabel("Bem-Vindo ao cadastro de Deuses");
		labelTitulo.setForeground(Color.WHITE);
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		painelTitulo.add(labelTitulo);

		labelNome = new JLabel("Nome: ");
		layoutLabel(labelNome);
		painelDados.add(labelNome, "cell 0 0,alignx center,aligny center");

		labelRegiao = new JLabel("Região: ");
		layoutLabel(labelRegiao);
		painelDados.add(labelRegiao, "cell 0 1,alignx center,aligny center");

		labelRepresentao = new JLabel("Representação: ");
		layoutLabel(labelRepresentao);
		painelDados.add(labelRepresentao, "cell 0 2,alignx center,aligny center");

		labelPersonalidade = new JLabel("Personalidade: ");
		layoutLabel(labelPersonalidade);
		painelDados.add(labelPersonalidade, "cell 0 3,alignx center,aligny center");

		labelHierarquia = new JLabel("Hierarquia: ");
		layoutLabel(labelHierarquia);
		painelDados.add(labelHierarquia, "cell 0 4,alignx center,aligny center");

	}

	private void layoutLabel(JLabel label) {
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
	}

	protected void configurarImagem(String nome, String nome2) {
		painelImagem = new JPanel();
		painelImagem.setBackground(new Color(0, 0, 51));
		painelPrincipal.add(painelImagem, BorderLayout.CENTER);
		painelImagem.setLayout(new BorderLayout(0, 0));

		labelImagem = new JLabel(new ImagemCriadorImagem().imagemFundo(nome, nome2));
		painelImagem.add(labelImagem, BorderLayout.CENTER);

	}
	
	protected void setTitulo(String titulo) {
		labelTitulo.setText(titulo);
	}

	private void configurarFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(440, 500);
		setLocationRelativeTo(null);
		setUndecorated(true);
	}

}
