package by.htp.database.dao;
import java.sql.*;
import java.util.logging.*;

public class DBExample {

	public static void main(String[] args) {

		Connection connection = null;
		// URL ê áàçå ñîñòîèò èç
		// ïğîòîêîëà:ïîäïğîòîêîëà://[õîñòà]:[ïîğòà_ÑÓÁÄ]/[ÁÄ] è äğóãèõ_ñâåäåíèé
		String url = "jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
		// Èìÿ ïîëüçîâàòåëÿ ÁÄ
		String name = "root";
		// Ïàğîëü
		String password = "êùùå";
		try {
			// Çàãğóæàåì äğàéâåğ
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Äğàéâåğ ïîäêëş÷åí");
			// Ñîçäà¸ì ñîåäèíåíèå
			connection = DriverManager.getConnection(url, name, password);
			System.out.println("Ñîåäèíåíèå óñòàíîâëåíî");
			// Äëÿ èñïîëüçîâàíèÿ SQL çàïğîñîâ ñóùåñòâóşò 3 òèïà îáúåêòîâ:
			// 1.Statement: èñïîëüçóåòñÿ äëÿ ïğîñòûõ ñëó÷àåâ áåç ïàğàìåòğîâ
			Statement statement = null;

			statement = connection.createStatement();
			// Âûïîëíèì çàïğîñ
			ResultSet result1 = statement
					.executeQuery("SELECT * FROM users where id >2 and id <10");
			// result ıòî óêàçàòåëü íà ïåğâóş ñòğîêó ñ âûáîğêè
			// ÷òîáû âûâåñòè äàííûå ìû áóäåì èñïîëüçîâàòü
			// ìåòîä next() , ñ ïîìîùüş êîòîğîãî ïåğåõîäèì ê ñëåäóşùåìó ıëåìåíòó
			System.out.println("Âûâîäèì statement");
			while (result1.next()) {
				System.out.println("Íîìåğ â âûáîğêå #" + result1.getRow()
						+ "\t Íîìåğ â áàçå #" + result1.getInt("id") + "\t"
						+ result1.getString("username"));
			}
			// Âñòàâèòü çàïèñü
			statement
					.executeUpdate("INSERT INTO users(username) values('name')");
			// Îáíîâèòü çàïèñü
			statement
					.executeUpdate("UPDATE users SET username = 'admin' where id = 1");

			// 2.PreparedStatement: ïğåäâàğèòåëüíî êîìïèëèğóåò çàïğîñû,
			// êîòîğûå ìîãóò ñîäåğæàòü âõîäíûå ïàğàìåòğû
			PreparedStatement preparedStatement = null;
			// ? - ìåñòî âñòàâêè íàøåãî çíà÷åíÿ
			preparedStatement = connection
					.prepareStatement("SELECT * FROM users where id > ? and id < ?");
			// Óñòàíàâëèâàåì â íóæíóş ïîçèöèş çíà÷åíèÿ îïğåäåë¸ííîãî òèïà
			preparedStatement.setInt(1, 2);
			preparedStatement.setInt(2, 10);
			// âûïîëíÿåì çàïğîñ
			ResultSet result2 = preparedStatement.executeQuery();

			System.out.println("Âûâîäèì PreparedStatement");
			while (result2.next()) {
				System.out.println("Íîìåğ â âûáîğêå #" + result2.getRow()
						+ "\t Íîìåğ â áàçå #" + result2.getInt("id") + "\t"
						+ result2.getString("username"));
			}

			preparedStatement = connection
					.prepareStatement("INSERT INTO users(username) values(?)");
			preparedStatement.setString(1, "user_name");
			// ìåòîä ïğèíèìàåò çíà÷åíèå áåç ïàğàìåòğîâ
			// òåìæå ñïîñîáîì ìîæíî ñäåëàòü è UPDATE
			preparedStatement.executeUpdate();

			// 3.CallableStatement: èñïîëüçóåòñÿ äëÿ âûçîâà õğàíèìûõ ôóíêöèé,
			// êîòîğûå ìîãóò ñîäåğæàòü âõîäíûå è âûõîäíûå ïàğàìåòğû
			CallableStatement callableStatement = null;
			// Âûçûâàåì ôóíêöèş myFunc (õğàíèòñÿ â ÁÄ)
			callableStatement = connection
					.prepareCall(" { call myfunc(?,?) } ");
			// Çàäà¸ì âõîäíûå ïàğàìåòğû
			callableStatement.setString(1, "Dima");
			callableStatement.setString(2, "Alex");
			// Âûïîëíÿåì çàïğîñ
			ResultSet result3 = callableStatement.executeQuery();
			// Åñëè CallableStatement âîçâğàùàåò íåñêîëüêî îáúåêòîâ ResultSet,
			// òî íóæíî âûâîäèòü äàííûå â öèêëå ñ ïîìîùüş ìåòîäà next
			// ó ìåíÿ ôóíêöèÿ âîçâğàùàåò îäèí îáúåêò
			result3.next();
			System.out.println(result3.getString("MESSAGE"));
			// åñëè ôóíêöèÿ âñòàâëÿåò èëè îáíîâëÿåò, òî èñïîëüçóåòñÿ ìåòîä
			// executeUpdate()

		} catch (Exception ex) {
			// âûâîäèì íàèáîëåå çíà÷èìûå ñîîáùåíèÿ
			Logger.getLogger(DBExample.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(DBExample.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		}

	}
}