package Util;

import java.util.ArrayList;
import java.util.List;

public class UtilRetorno {
	@Override
	public String toString() {
		return "Retorno [sucesso=" + sucesso + ", mensagens=" + mensagens + "]";
	}
	private boolean sucesso;
	private List<String> mensagens;
	
	public UtilRetorno(boolean sucesso) {
		this.sucesso = sucesso;
		this.mensagens = new ArrayList<>();
	}
	public UtilRetorno(boolean sucesso, List<String> mensagens) {
		this.sucesso = sucesso;
		this.mensagens = mensagens;
	}
	
	public UtilRetorno (boolean sucesso, String mensagem) {
		this.sucesso = sucesso;
		this.mensagens = new ArrayList<String>();
		this.mensagens.add(mensagem);
	}
	public boolean isSucesso() {
		return sucesso;
	}
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	public List<String> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	public void addMensagem(String mensagem) {
		this.getMensagens().add(mensagem);
	}
}
