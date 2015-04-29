package ch.berufsbildungscenter.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.berufsbildungscenter.projekt.User;

public class UserJDBCDao extends Database implements UserDao {
	// Variable fuer Verbindung
	private Connection con = null;

	public List<User> findAllUsers() throws SQLException {
		String sql = "SELECT * FROM user";
		List<User> p = new ArrayList<User>();

		con = getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
			User user = new User();
			user.setID_user(rs.getString("ID_user"));
			user.setPassword(rs.getString("password"));
			p.add(user);
		}
		closeCon();
		return p;
	}

	public void registrieren(User user) throws SQLException {
		String sql = "INSERT INTO user (ID_user, email, password,selectedPaddle,hasPaddle1,hasPaddle2,hasPaddle3,hasPaddle4,hasPaddle5,Geld,rankedPunkte,gespielteSpiele,gewonneneSpiele,winLose) VALUES (?, ?, ?,0,false,false,false,false,false,0,0,0,0,0)";
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, user.getID_user());
		ps.setString(2, user.getMail());
		ps.setString(3, user.getPassword());
		ps.executeUpdate();
		closeCon();
	}

	@Override
	public User loadUser(User u) throws SQLException {
		String sql = "SELECT * FROM user WHERE ID_user='" + u.getID_user()
				+ "'";

		con = getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {

			u.setSelectedPaddle(rs.getInt("selectedPaddle"));
			u.setGeld(rs.getInt("Geld"));
			u.setRankedPunkte(rs.getInt("rankedPunkte"));
			u.setGespielteSpiele(rs.getInt("gespielteSpiele"));
			u.setGewonneneSpiele(rs.getInt("gewonneneSpiele"));
			u.setWinLose(rs.getFloat("winLose"));
			u.setHasPaddle1(rs.getBoolean("hasPaddle1"));
			u.setHasPaddle2(rs.getBoolean("hasPaddle2"));
			u.setHasPaddle3(rs.getBoolean("hasPaddle3"));
			u.setHasPaddle4(rs.getBoolean("hasPaddle4"));
			u.setHasPaddle5(rs.getBoolean("hasPaddle5"));
		}
		closeCon();
		return u;

	}

	@Override
	public User updateUser(User u) throws SQLException {
		String sql = "UPDATE user SET `Geld`=" + u.getGeld()
				+ " WHERE ID_user='" + u.getID_user() + "'";

		con = getCon();
		ps = con.prepareStatement(sql);
		eu = ps.executeUpdate();
		return null;
	}

	@Override
	public User updateUserPaddle(User u) throws SQLException {
		String sql = "UPDATE `user` SET `hasPaddle0`=" + u.isHasPaddle0()
				+ ",`hasPaddle1`=" + u.isHasPaddle1() + ",`hasPaddle2`="
				+ u.isHasPaddle2() + ",`hasPaddle3`=" + u.isHasPaddle3()
				+ ",`hasPaddle4`=" + u.isHasPaddle4() + ",`hasPaddle5`="
				+ u.isHasPaddle5() +",`hasPaddle6`="+u.isHasPaddle6()+ ",`Geld`=" + u.getGeld()
				+ ",`selectedPaddle`=" + u.getSelectedPaddle()
				+ " WHERE ID_user='" + u.getID_user() + "'";

		con = getCon();
		ps = con.prepareStatement(sql);
		eu = ps.executeUpdate();
		return u;

	}

}