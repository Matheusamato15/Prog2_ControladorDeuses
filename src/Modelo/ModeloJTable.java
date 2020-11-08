package Modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ModeloJTable extends AbstractTableModel{

	
	private String [] colunas = {"Nome", "Personalidade", "Região", "Representação", "Hierarquia"};
	private ArrayList<ModeloDeus> listaDeDeuses = new ArrayList<ModeloDeus>();
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return listaDeDeuses.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
			case 0: 
				return listaDeDeuses.get(linha).getNomeDeus();
			case 1:
				return listaDeDeuses.get(linha).getPersonalidadeDeus();
			case 2:
				return listaDeDeuses.get(linha).getRegiaoDeus();
			case 3:
				return listaDeDeuses.get(linha).getRepresentacaoDeus();
			case 4:
				return listaDeDeuses.get(linha).getHierarquiaDeus();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}
	
	public void adicionarLinha (ModeloDeus deus) {
		this.listaDeDeuses.add(deus);
	
	}
	
	public ModeloDeus obterLinha(int linha) {
		return this.listaDeDeuses.get(linha);
	}
}
