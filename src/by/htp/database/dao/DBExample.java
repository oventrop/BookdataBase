package by.htp.database.dao;
import java.sql.*;
import java.util.logging.*;

public class DBExample {

	public static void main(String[] args) {

		Connection connection = null;
		// URL � ���� ������� ��
		// ���������:������������://[�����]:[�����_����]/[��] � ������_��������
		String url = "jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
		// ��� ������������ ��
		String name = "root";
		// ������
		String password = "����";
		try {
			// ��������� �������
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("������� ���������");
			// ������ ����������
			connection = DriverManager.getConnection(url, name, password);
			System.out.println("���������� �����������");
			// ��� ������������� SQL �������� ���������� 3 ���� ��������:
			// 1.Statement: ������������ ��� ������� ������� ��� ����������
			Statement statement = null;

			statement = connection.createStatement();
			// �������� ������
			ResultSet result1 = statement
					.executeQuery("SELECT * FROM users where id >2 and id <10");
			// result ��� ��������� �� ������ ������ � �������
			// ����� ������� ������ �� ����� ������������
			// ����� next() , � ������� �������� ��������� � ���������� ��������
			System.out.println("������� statement");
			while (result1.next()) {
				System.out.println("����� � ������� #" + result1.getRow()
						+ "\t ����� � ���� #" + result1.getInt("id") + "\t"
						+ result1.getString("username"));
			}
			// �������� ������
			statement
					.executeUpdate("INSERT INTO users(username) values('name')");
			// �������� ������
			statement
					.executeUpdate("UPDATE users SET username = 'admin' where id = 1");

			// 2.PreparedStatement: �������������� ����������� �������,
			// ������� ����� ��������� ������� ���������
			PreparedStatement preparedStatement = null;
			// ? - ����� ������� ������ �������
			preparedStatement = connection
					.prepareStatement("SELECT * FROM users where id > ? and id < ?");
			// ������������� � ������ ������� �������� ������������ ����
			preparedStatement.setInt(1, 2);
			preparedStatement.setInt(2, 10);
			// ��������� ������
			ResultSet result2 = preparedStatement.executeQuery();

			System.out.println("������� PreparedStatement");
			while (result2.next()) {
				System.out.println("����� � ������� #" + result2.getRow()
						+ "\t ����� � ���� #" + result2.getInt("id") + "\t"
						+ result2.getString("username"));
			}

			preparedStatement = connection
					.prepareStatement("INSERT INTO users(username) values(?)");
			preparedStatement.setString(1, "user_name");
			// ����� ��������� �������� ��� ����������
			// ����� �������� ����� ������� � UPDATE
			preparedStatement.executeUpdate();

			// 3.CallableStatement: ������������ ��� ������ �������� �������,
			// ������� ����� ��������� ������� � �������� ���������
			CallableStatement callableStatement = null;
			// �������� ������� myFunc (�������� � ��)
			callableStatement = connection
					.prepareCall(" { call myfunc(?,?) } ");
			// ����� ������� ���������
			callableStatement.setString(1, "Dima");
			callableStatement.setString(2, "Alex");
			// ��������� ������
			ResultSet result3 = callableStatement.executeQuery();
			// ���� CallableStatement ���������� ��������� �������� ResultSet,
			// �� ����� �������� ������ � ����� � ������� ������ next
			// � ���� ������� ���������� ���� ������
			result3.next();
			System.out.println(result3.getString("MESSAGE"));
			// ���� ������� ��������� ��� ���������, �� ������������ �����
			// executeUpdate()

		} catch (Exception ex) {
			// ������� �������� �������� ���������
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