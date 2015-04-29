package ch.berufsbildungscenter.db;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import ch.berufsbildungscenter.projekt.User;

public class PongController {
	private static PongController instance = new PongController();
	private final static UserDao USER_DAO = new UserJDBCDao();

	private PongController() {

	}

	public static PongController getInstance() {
		return PongController.instance;
	}

	public String regTry(User newUser, JLabel console) {
		
		List<User> dbUsers = null;
		boolean userAlreadyExists = true;
		final Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

		if (newUser.getPassword().isEmpty()) {
			return "PwEmpty";
			
		} else if (newUser.getPassword().length() > 16 || newUser.getPassword().length() < 4) {
			return "falsePwLength";
		
		} else {
			if (newUser.getPassword().equals(newUser.getPasswordConf())) {
				
				if (newUser.getID_user().isEmpty()) {
					return "UsEmpty";
				
				
					
				} else if (newUser.getID_user().length() > 12 || newUser.getID_user().length() < 4) {
					return "falseUsLength";

				} else {
					if (newUser.getMail().isEmpty()) {
						return "EmEmpty";
					} else if (newUser.getMail().length() > 60) {
						return "falseEmLength";
					
					} else {
						if (!pattern.matcher(newUser.getMail()).matches()) {
							return "FalseMail";
						} else {
							try {
								dbUsers = USER_DAO.findAllUsers();

								for (User dbUser : dbUsers) {
									if (newUser.getID_user().equals(
											dbUser.getID_user())) {
										return "UsExists";
									} else {
										userAlreadyExists = false;
									}
								}
								if (userAlreadyExists == false) {
									USER_DAO.registrieren(newUser);
									console.setText(console.getText() + newUser.getID_user()
											+ " hat sich Registriert! Erreichbar unter: " + newUser.getMail()
											+ "<br/>");
									return "OK";
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				return "PwFalse";
			}
		}
		return "Error";
	}

	public void registrieren(User u, JLabel console) {
		try {
			USER_DAO.registrieren(u);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public User findUser(User u) {
		try {
			u = USER_DAO.loadUser(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public String login(User u) {
		List<User> user = null;
		if (u.getID_user().isEmpty()) {

			return "UsEmpty";
		} else if (u.getPassword().isEmpty()) {
			return "PwEmpty";
		} else {

			try {
				user = USER_DAO.findAllUsers();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			for (User users : user) {
				if (u.getID_user().equals(users.getID_user())) {
					if (u.getPassword().equals(users.getPassword())) {
						return "OK";

					}

				}

			}

			return "UserNotFount";
		}
	}
	public User refreshPaddle(User u){
		try {
			u = USER_DAO.loadUser(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public static void setInstance(PongController instance) {
		PongController.instance = instance;
	}

	public static UserDao getUserDao() {
		return USER_DAO;
	}

	public User updateUser(User u) {
		try {
			u = USER_DAO.updateUser(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;

	}
	
	public User updateUserPaddle(User u) {
		try {
			u = USER_DAO.updateUserPaddle(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;

	}

}
