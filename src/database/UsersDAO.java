package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {

	public Users users;
	public BD bd;
	private PreparedStatement st;
	private ResultSet rs;
	private String men, sql;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;

	public UsersDAO() {
		bd = new BD();
		users = new Users();
	}

	public boolean localizar(String nome) {
		users.setNome(nome);
		sql = "select * from tb_jogo where nome=?;";
		try {
			if(bd.getConnection()) {
				st = bd.c.prepareStatement(sql);
				st.setString(1, users.getNome());
				st.executeQuery();
				rs = st.executeQuery();
				rs.next();
				users.setId(rs.getInt("id"));
				users.setNome(rs.getString("nome"));
				users.setGalinha(rs.getInt("galinha"));
				users.setPato(rs.getInt("pato"));
				users.setVaca(rs.getInt("vaca"));
				users.setCabra(rs.getInt("cabra"));
				users.setOvelha(rs.getInt("ovelha"));
				users.setDinheiro(rs.getInt("dinheiro"));
				users.setLogin(rs.getInt("login"));
				return true;
			}
			return false;
		} catch (SQLException erro) {
			return false;
		}
	}

	public String atualizar(int operacao) {
		men = "Operação realizada com sucesso ";
		try {
			if (operacao == INCLUSAO) {
				sql = "insert into tb_jogo values (?,?,?,?,?,?,?,?,?)";
				st = bd.c.prepareStatement(sql);

				st.setInt(1, users.getId());
				st.setString(2, users.getNome());
				st.setInt(3, users.getGalinha());
				st.setInt(4, users.getPato());
				st.setInt(5, users.getVaca());
				st.setInt(6, users.getCabra());
				st.setInt(7, users.getOvelha());
				st.setInt(8, users.getDinheiro());
				st.setInt(9, users.getLogin());

			} else if (operacao == ALTERACAO) {
				sql = "update tb_jogo set nome = ?, galinha = ?, pato = ?, vaca = ?, cabra = ?, ovelha = ?, dinheiro = ?,login = ? where id =?";
				st = bd.c.prepareStatement(sql);

				//st.setInt(1, users.getId());
				st.setString(1, users.getNome());
				st.setInt(2, users.getGalinha());
				st.setInt(3, users.getPato());
				st.setInt(4, users.getVaca());
				st.setInt(5, users.getCabra());
				st.setInt(6, users.getOvelha());
				st.setInt(7, users.getDinheiro());
				st.setInt(8, users.getLogin());
				st.setInt(9, users.getId());
			} else if (operacao == EXCLUSAO) {
				sql = "delete from tb_jogo where id =?";
				st = bd.c.prepareStatement(sql);
				st.setInt(1, users.getId());
			}
			if (st.executeUpdate() == 0) {
				men = "Falha na operação";
			}

		} catch (SQLException erro) {
			men = "Falha na operação " + erro.toString();
		}
		return men;
	}
}
