package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Modelo.ModeloDeus;
import Util.UtilRetorno;
import Util.UtilRetornoDado;


public class PersistenciaDAODeus {
	
	public static final String SERVIDOR = "localhost:5433";
	public static final String BANCO_NOME = "crud";
	public static final String USUARIO_NOME = "postgres";
	public static final String USUARIO_SENHA = "";
	private static PersistenciaDAODeus dao;
	private Connection conexao;
	
	private PersistenciaDAODeus() {
		inicializarDriverConexao();
		inicializarConexao();
		
	}

	private void inicializarConexao() {
		try {
			conexao = DriverManager.getConnection("jdbc:postgresql://"+SERVIDOR+"/"+BANCO_NOME, 
					USUARIO_NOME, 
					USUARIO_SENHA);
		} catch (Exception e) {
			System.out.println("Erro de conexão!");
			e.printStackTrace();
			System.exit(2);
		}
		
		
	}

	private void inicializarDriverConexao() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			
			System.out.println("Falha ao carregar o Driver de conexão do banco de dados!");
			e.printStackTrace();
			System.exit(1);
			
		}
	}
	
	public UtilRetornoDado<ModeloDeus> getDeus(Integer id){// throws ExceptionPersistencia {
		UtilRetornoDado<ModeloDeus> retornoDado = new UtilRetornoDado<>(true);
		
		ModeloDeus modeloDeus = new ModeloDeus();
		modeloDeus.setId(id);
		
		UtilRetornoDado<List<ModeloDeus>> listarDeus = this.pesquisarDeus(modeloDeus);
		retornoDado.setMensagens(listarDeus.getMensagens());
		
		if(listarDeus.isSucesso()) {				
			retornoDado.setDado(listarDeus.getDado().get(0));
		}
		return retornoDado;
	}
	
	public UtilRetornoDado<List<ModeloDeus>> listarDeus(){
		return this.pesquisarDeus(null);
	}
	
	public UtilRetornoDado<List<ModeloDeus>> pesquisarDeus(ModeloDeus deus){
		UtilRetornoDado<List<ModeloDeus>> retornoDado = new UtilRetornoDado<>(true);
		String sql = "select id, nome, regiao, representacao, personalidade, hierarquia from deus ";
		
		String where = preparaStringDeWhereListar(deus);
		sql += where;

		ModeloDeus modeloDeus = null;
		try {
			PreparedStatement pst = conexao.prepareStatement(sql);
			
			configurarPreparedStatementListar(deus, pst);
			
			ResultSet rs = pst.executeQuery();
			List<ModeloDeus> listaDeuses = new ArrayList<ModeloDeus>();

			while (rs.next()) {
				modeloDeus = new ModeloDeus();
				modeloDeus.setId(rs.getInt("id"));
				modeloDeus.setNomeDeus(rs.getString("nome"));
				modeloDeus.setRegiaoDeus(rs.getString("regiao"));
				modeloDeus.setRepresentacaoDeus(rs.getString("representacao"));
				modeloDeus.setPersonalidadeDeus(rs.getString("personalidade"));
				modeloDeus.setHierarquiaDeus(rs.getString("hierarquia"));
				listaDeuses.add(modeloDeus);
			}
			retornoDado.setDado(listaDeuses);

		} catch (SQLException e) {
			retornoDado.setSucesso(false);
			retornoDado.getMensagens().add(e.getMessage());
		} catch (NullPointerException e) {
			retornoDado.setSucesso(false);
			retornoDado.addMensagem("Falha ao usar a conexão!!");
		}
		return retornoDado;
	}
	
	private void configurarPreparedStatementListar(ModeloDeus deus, PreparedStatement pst) throws SQLException {
		if(deus!=null) {
			int seqSet = 1;
			if(deus.getId()!=null) {
				pst.setInt(seqSet++, deus.getId());
			}
			if(deus.getNomeDeus()!=null && !deus.getNomeDeus().equals("")) {
				pst.setString(seqSet++, deus.getNomeDeus()+"%");
			}
			if(deus.getPersonalidadeDeus()!=null && !deus.getPersonalidadeDeus().equals("")) {
				pst.setString(seqSet++, deus.getPersonalidadeDeus());
			}
			if(deus.getRegiaoDeus()!=null && !deus.getRegiaoDeus().equals("")) {
				pst.setString(seqSet++, deus.getRegiaoDeus());
			}
			if(deus.getRepresentacaoDeus()!=null && !deus.getRepresentacaoDeus().toString().equals("")) {
				pst.setString(seqSet++, deus.getRepresentacaoDeus().toString());
			}
			
		}
	}

	private String preparaStringDeWhereListar(ModeloDeus deus) {
		String where = "";
		if(deus!=null) {
			if(deus.getId()!=null) {
				where +=" where id = ?";
			}
			if(deus.getNomeDeus()!=null && !deus.getNomeDeus().equals("")){
				if(where.equals("")) {
					where = " where nome like ?";
				}else {
					where +=" and nome like ?";
				}
			}
			
			if(deus.getPersonalidadeDeus()!=null && !deus.getPersonalidadeDeus().equals("")) {
				if(where.equals("")) {
					where = " where personalidade like ?";
				}else {
					where +=" and personalidade like ?";
				}
			}
			
			if(deus.getRegiaoDeus()!=null && !deus.getRegiaoDeus().equals("")) {
				if(where.equals("")) {
					where = " where regiao like ?";
				}else {
					where +=" and regiao like ?";
				}
			}

			if(deus.getRepresentacaoDeus()!=null && !deus.getRepresentacaoDeus().toString().equals("")) {
				if(where.equals("")) {
					where = " where representacao = ?";
				}else {
					where +=" and representacao = ?";
				}
			}

		}
		return where;
	}
	
	public UtilRetorno gravarDeus(String acao, String sql, ModeloDeus deus, Map<Integer, Object> parametros) {
		UtilRetorno retorno = new UtilRetorno(true);
		try {
			PreparedStatement pst = conexao.prepareStatement(sql);

			for(Integer key: parametros.keySet()) {
				pst.setObject(key, parametros.get(key));
			}

			pst.executeUpdate();

			pst.close();

		} catch (SQLException e) {
			retorno.setSucesso(false);
			if (e.getMessage().contains("idx_titulo_unico")) {
				retorno.getMensagens().add("Já existe um deus com esse nome!");
			} else {
				retorno.getMensagens().add("Erro ao "+acao+" deus");
			}
			retorno.getMensagens().add(e.getMessage());
			return retorno;
		}
		retorno.getMensagens().add("Ação("+acao+") Realizada com sucesso!");
		return retorno;
	}
	
	public UtilRetorno incluir(ModeloDeus deus) {
		String sql = "insert into deus(nome,personalidade,regiao,representacao,hierarquia) values(?,?,?,?,?)";
		String acao = "incluir";
		
		UtilRetornoDado<Map<Integer, Object>> retornoParametros = getParametrosDeus(acao,deus);
		if(!retornoParametros.isSucesso()) {
			return retornoParametros;
		}
		return gravarDeus(acao, sql, deus, retornoParametros.getDado());
	}
	
	public UtilRetorno editar(ModeloDeus deus) {	
		String sql = "update deus set "
				+ "nome=?, regiao=?, representacao=?, personalidade=?, hierarquia=? where id=?";
		
		String acao = "salvar";
		
		UtilRetornoDado<Map<Integer, Object>> retornoParametros = getParametrosDeus(acao,deus);
		if(!retornoParametros.isSucesso()) {
			return retornoParametros;
		}

		//Configura a PK
		int indicePk = retornoParametros.getDado().size()+1;
		retornoParametros.getDado().put(indicePk, deus.getId());
		
		return gravarDeus(acao, sql, deus, retornoParametros.getDado());
	}

	private UtilRetornoDado<Map<Integer, Object>> getParametrosDeus(String acao,ModeloDeus deus) {
		UtilRetornoDado<Map<Integer, Object>> ret = isParametroDeusPreenchido(acao, deus);
		if(!ret.isSucesso()) {return ret;}
		
		Map<Integer, Object> parametros =new HashMap<>();
		
		parametros.put(1, deus.getNomeDeus());
		parametros.put(2, deus.getPersonalidadeDeus());
		parametros.put(3, deus.getRegiaoDeus());
		parametros.put(4, deus.getRepresentacaoDeus());
		parametros.put(5, deus.getHierarquiaDeus());
		
		ret.setDado(parametros);
		return ret;
	}

	private UtilRetornoDado<Map<Integer, Object>> isParametroDeusPreenchido(String acao, ModeloDeus deus) {
		UtilRetornoDado<Map<Integer, Object>> retorno = new UtilRetornoDado<>(true);
		if (deus == null) {
			retorno.setSucesso(false);
			retorno.addMensagem("Não foi informado objeto deus para "+acao);
			return retorno;
		}
		return retorno;
	}
	
	public UtilRetorno remover(ModeloDeus deus) {
		UtilRetornoDado<Map<Integer, Object>> retorno = isParametroDeusPreenchido("remover", deus);
		if(!retorno.isSucesso()) {return retorno;}
		
		String sql = "delete from deus where id=?";
		
		PreparedStatement pst;
		try {
			pst = conexao.prepareStatement(sql);
			pst.setInt(1,deus.getId());
			
			pst.executeUpdate();
			
			retorno.addMensagem("Ação(remover) realizada com sucesso!!"); 
		} catch (SQLException e) {
			retorno.addMensagem("Erro ao Remover. Erro: "+e.getMessage());
			retorno.setSucesso(false);
		}
		return retorno;
	}
	
	
	public static PersistenciaDAODeus getInstance() {
		if(dao == null) {
			dao = new PersistenciaDAODeus();
		}
		return dao;
	}
	
	
}
