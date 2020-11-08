package Util;

import java.util.List;

public class UtilRetornoDado<MODELO> extends UtilRetorno {
	private MODELO dado;

	public UtilRetornoDado(boolean sucesso, List<String> mensagens) {
		super(sucesso, mensagens);
	}

	public UtilRetornoDado(boolean sucesso, String mensagem) {
		super(sucesso, mensagem);
	}

	public UtilRetornoDado(boolean sucesso) {
		super(sucesso);
	}

	public MODELO getDado() {
		return dado;
	}

	public void setDado(MODELO dado) {
		this.dado = dado;
	}


}
