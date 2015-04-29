package ch.berufsbildungscenter.db;

import java.sql.SQLException;
import java.util.List;

import ch.berufsbildungscenter.projekt.User;

public interface UserDao {
	public abstract List<User> findAllUsers() throws SQLException;

	public abstract void registrieren(User u) throws SQLException;

	public abstract User loadUser(User u) throws SQLException;

	public abstract User updateUser(User u) throws SQLException;

	public abstract User updateUserPaddle(User u) throws SQLException;

}
