
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;

public class InsetTest {
	static int i = 0;

	public static void batchInsert() throws ClassNotFoundException, SQLException {
		long start = System.currentTimeMillis();
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://192.168.2.10:1545/kiamdev2", "pgxz",
				"postgres");

		connection.setAutoCommit(false);

		PreparedStatement cmd = connection.prepareStatement(
				"INSERT INTO tb_log_content VALUES (?,?, NULL, '2018-05-03 ', NULL, '1');");

		for (int i = 0; i < 20000000; i++) {// 100万条数据
			cmd.setString(1, UUID.randomUUID().toString());
			cmd.setString(2, getLog());
			cmd.addBatch();
			if (i % 1000 == 0) {
				cmd.executeBatch();
				connection.commit();
			}
		}
		cmd.executeBatch();
		connection.commit();

		cmd.close();
		connection.close();

		long end = System.currentTimeMillis();
		end = end - start;
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("./time.txt"));
			out.write((double) end / 1000 + "");
			out.close();
			System.out.println(end);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getLog() {

		int year = (int) (Math.random() * 1000) % 8;
		year = 2009 + year;
		int maxMonth = 13;
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		if (thisYear == year) {
			maxMonth = Calendar.getInstance().get(Calendar.MONTH) + 2;
		}
		int hour = (int) (Math.random() * 1000) % 24;
		int day = (int) (Math.random() * 1000) % 31;
		int month = (int) (Math.random() * 1000) % maxMonth;
		if (0 == month) {
			month = 8;
		}
		if (0 == hour) {
			hour = 10;
		}
		if (0 == day) {
			day = 8;
		}
		if (2 == month && day > 28) {
			day = 28;
		}
		// String log = String.format("senderId=\"OA\",logType=\"2\",logTime=\"%d-%d-%d
		// %d:40:45.0010\",subjectId=\"10000001\",opName=\"上传文档\",opContent=\"线程号：%d,任务号：%d\",opResult=\"0\",opDesc=\"%d\",subjectIp=\"192.168.60.200\",timestamp=\"\",signature=\"\"",
		// year,month,day,hour,threadNum,testCount, i);
		String log = String.format(
				"<174>senderId=\"kmail\",logType=\"2\",logTime=\"%d-%d-%d %d:25:31\",subjectId=\"kmail安全保密管理员\",opName =\"登录\",opContent=\"线程号：%d,任务号：%d\",opResult=\"1\",opDesc=\"成功%d\",subjectIp=\"1.1.1.1\",timestamp=\"\",signature=\"\"",
				year, month, day, hour, 5, 2000000, i++);

		return log;

	}

	public static void main(String[] args) {
		try {
			InsetTest.batchInsert();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
