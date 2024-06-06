import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class db1 {
	// 관리자 권한
	public static int schemaInit() {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root","1234");
             Statement statement = connection.createStatement()) {
            // SQL 파일 경로
            String sqlFilePath = "initTables_movies.sql";

            // SQL 파일 읽기
            StringBuilder sqlQueries = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(sqlFilePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // 주석 제거
                    if (!line.trim().startsWith("--")) {
                        sqlQueries.append(line);
                        sqlQueries.append("\n");
                    }
                }
                
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }

            // 쿼리 실행
            String[] queries = sqlQueries.toString().split(";");
            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    statement.executeUpdate(query);
                }
            }

            System.out.println("SQL 파일 실행이 완료되었습니다.");
    		return 0;
        } catch (SQLException e) {
            e.printStackTrace();
			System.out.println(e.getMessage());
            return -1;
        }
    }
	public static int init() {
		if (schemaInit()==-1) {
			return -1;
		}
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 로드
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root","1234"); // JDBC 연결
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			
			
			String insertMovieData = "INSERT INTO movie (movieId, movieName, moveGrade, director, actor, genre, movieSummary, openDate, rate) VALUES "
                    + "(1, 'The Silent Echo', 0, 'John Smith,Emily Johnson', 'Michael Brown,Jessica Taylor,David Anderson', 'Drama', 'A young musician discovers an old, silent piano in an abandoned house and uncovers its mysterious past, leading to a journey of self-discovery and reconciliation.', '2023-01-01', 5),"
                    + "(2, 'Shadows of the Past', 2, 'Robert Davis,Laura Miller', 'Daniel Wilson,Sarah Moore,James Jackson', 'Thriller', 'A detective must unravel the secrets of a small town\\'s dark history to solve a series of gruesome murders linked to long-forgotten crimes.', '2023-02-25', 4),"
                    + "(3, 'The Last Horizon', 1, 'William White,Olivia Harris', 'Joshua Martin,Emily Thompson,Christopher Garcia', 'Science Fiction', 'In a future where Earth is dying, a team of astronauts embarks on a mission to find a new habitable planet, but they face unexpected challenges and discover a shocking truth.','2023-03-05', 4),"
                    + "        (4, 'Echoes of Eternity', 0, 'Richard Clark,Madison Martinez', 'Andrew Lee,Megan Hernandez,Ryan King', 'Fantasy', 'A young girl finds an ancient book that transports her to a magical realm where she must fulfill an ancient prophecy to save both worlds from an impending doom.', '2023-04-13', 5),"
                    + "        (5, 'Whispering Pines', 2, 'Charles Walker,Sophia Hall', 'Kevin Young,Rachel Allen,Brian Scott', 'Horror', 'A group of friends decides to spend a weekend in a remote cabin in the woods, only to find themselves haunted by the spirits of a long-forgotten tragedy.', '2023-05-11', 3),"
                    + "        (6, 'Beyond the Veil', 1, 'Thomas Adams,Isabella Nelson', 'Jason Wright,Amanda Baker,Eric Perez', 'Mystery', 'After the sudden death of her twin sister, a woman begins to experience visions that lead her to uncover a hidden world of secrets and lies within her family.', '2023-06-07', 4),"
                    + "        (7, 'Crimson Moon', 2, 'Henry Mitchell,Ava Roberts', 'Patrick Turner,Laura Phillips,Steven Campbell', 'Supernatural Thriller', 'A detective with a dark past is drawn into a deadly game of cat and mouse with a cunning vampire, leading to a battle between the living and the undead.', '2023-07-21', 3),"
                    + "        (8, 'The Forgotten City', 0, 'George Evans,Mia Parker', 'Matthew Edwards,Angela Collins,Jacob Murphy', 'Adventure', 'An archaeologist discovers an ancient map that leads to a hidden city lost to time, sparking a race against treasure hunters and rival explorers.', '2023-08-03', 4),"
                    + "        (9, 'Infinite Dreams', 0, 'Ethan Baker,Chloe Rivera', 'Brandon Howard,Olivia Cox,Jeremy Sanchez', 'Romantic Drama', 'Two strangers find themselves connected through their dreams, leading them to search for each other in real life while overcoming personal struggles.', '2023-09-12', 5),"
                    + "        (10, 'Midnight Sun', 0, 'Mason Morgan,Lily Reed', 'Nicholas Ward,Allison Brooks,Dylan Bennett', 'Romance', 'A young woman with a rare condition that makes her allergic to sunlight forms a deep connection with a boy she can only meet under the cover of night.', '2023-10-01', 4),"
                    + "        (11, 'Whispering Shadows', 2, 'Alexander Hayes,Victoria Hughes', 'Jonathan Long,Anna Foster,Raymond Price', 'Psychological Thriller', 'A renowned psychologist starts experiencing strange phenomena that blur the line between reality and illusion as she delves into the mind of a troubled patient.', '2023-11-02', 3),"
                    + "        (12, 'The Hidden Path', 0, 'Nathan Bell,Samantha Cook', 'Zachary Green,Emily Foster,Samuel Griffin', 'Fantasy Adventure', 'A young orphan discovers a magical path that leads to a hidden world where she must embark on a quest to find her true identity and save the realm from darkness.', '2023-12-11', 4),"
                    + "        (13, 'Eternal Quest', 1, 'Logan Carter,Natalie Bryant', 'Benjamin Perry,Victoria Russell,Michael Wood', 'Historical Fantasy', 'A warrior from an ancient civilization is given a chance at immortality if he can complete a series of impossible tasks, but he soon learns that the true quest is one of the heart.', '2023-12-01', 4),"
                    + "       (14, 'The Lost Chronicles', 1, 'Ryan Hughes,Grace Foster', 'Elijah Powell,Sophia Henderson,Hunter Jenkins', 'Action Adventure', 'A journalist stumbles upon a series of hidden documents that reveal a conspiracy spanning centuries, thrusting her into a dangerous adventure to expose the truth.', '2023-12-25', 4);";
			stmt.executeUpdate(insertMovieData);
			System.out.println("movie 데이터 추가 완료");
			String insertRoomData = "INSERT INTO room (roomId, seatCol, seatRow, isUsed) "
			+ "VALUES (1, 10, 10, 1),"
			+ "       (2, 8, 8, 0),"
			+ "       (3, 10, 10, 1),"
			+ "       (4, 12, 10, 1),"
			+ "       (5, 20, 20, 1),"
			+ "       (6, 8, 10, 1),"
			+ "       (7, 10, 10, 1),"
			+ "       (8, 10, 12, 1),"
			+ "       (9, 15, 10, 1),"
			+ "       (10, 10, 10, 1),"
			+ "       (11, 17, 17, 1),"
			+ "       (12, 11, 11, 1);";
			stmt.executeUpdate(insertRoomData);
			System.out.println("room 데이터 추가 완료");
			
			String insertScheduleData = "INSERT INTO showSchedule (showScheduleId, movieId, roomId, showingStartDay, dayType, showRound, startTime)"
					+ "VALUES (1, 1, 12, '2023-03-01', 'WED', 1, '08:45:00'),"
					+ "       (2, 2, 10, '2023-03-02', 'THU', 1, '09:20:00'),"
					+ "       (3, 2, 3, '2023-03-27', 'MON', 2, '11:00:00'),"
					+ "       (4, 3, 1, '2023-04-02', 'SUN', 1, '11:45:00'),"
					+ "       (5, 4, 7, '2023-05-01', 'MON', 1, '12:30:00'),"
					+ "       (6, 6, 4, '2023-05-22', 'MON', 1, '13:15:00'),"
					+ "       (7, 7, 5, '2023-06-15', 'THU', 1, '14:00:00'),"
					+ "       (8, 8, 11, '2023-07-01', 'SAT', 1, '14:45:00'),"
					+ "       (9, 8, 9, '2023-08-26', 'SAT', 2, '15:30:00'),"
					+ "       (10, 10, 6, '2023-09-09', 'SAT', 1, '16:15:00'),"
					+ "       (11, 11, 8, '2023-10-19', 'THU', 1, '17:00:00'),"
					+ "       (12, 12, 3, '2023-10-22', 'SUN', 1, '17:45:00'),"
					+ "       (13, 13, 8, '2023-11-30', 'THU', 1, '18:30:00'),"
					+ "       (14, 14, 5, '2023-12-02', 'SAT', 1, '21:45:00'),"
					+ "       (15, 1, 10, '2023-05-12', 'FRI', 2, '22:00:00'),"
					+ "       (16, 5, 11, '2023-06-09', 'FRI', 1, '23:10:00');";

			stmt.executeUpdate(insertScheduleData);
			System.out.println("showSchedule 데이터 추가 완료");
			
			String insertUserInfoData = "INSERT INTO userInfo (userId, name, phoneNumber, addressEmail) VALUES ('apple124', 'John Doe', '01012345678', 'john.doe@gmail.com'),"
					+ "('orange567', 'Emily Johnson', '01023456789', 'emily.johnson@yahoo.com'),"
					+ "('banana890', 'Michael Smith', '01034567890', 'michael.smith@hotmail.com'),"
					+ "('grape123', 'Jessica Taylor', '01045678901', 'jessica.taylor@aol.com'),"
					+ "('kiwi456', 'David Anderson', '01056789012', 'david.anderson@outlook.com'),"
					+ "('melon789', 'Sarah Brown', '01067890123', 'sarah.brown@icloud.com'),"
					+ "('strawberry012', 'Chris Wilson', '01078901234', 'chris.wilson@protonmail.com'),"
					+ "('pineapple345', 'Linda Garcia', '01089012345', 'linda.garcia@live.com'),"
					+ "('blueberry678', 'James Martinez', '01090123456', 'james.martinez@msn.com'),"
					+ "('pear901', 'Mary Brown', '01001234567', 'mary.brown@me.com'),"
					+ "('cherry234', 'Robert Lee', '01012345678', 'robert.lee@ymail.com'),"
					+ "('watermelon567', 'Karen Rodriguez', '01023456789', 'karen.rodriguez@rocketmail.com'),"
					+ "('user1', 'Sejong Tester', '01011111111', 'user1234@naver.com');";
			stmt.executeUpdate(insertUserInfoData);
			System.out.println("userInfo 데이터 추가 완료");
			
			String insertPayInfoData = "INSERT INTO payInfo (payId, payMethod, payState, price, userId, payDate) VALUES (1, 'CreditCard', 1, 9000, 'apple124', '2023-05-01'),"
					+ "(2, 'Cash', 0, 8000, 'orange567', '2023-05-10'),"
					+ "(3, 'DebitCard', 1, 18000, 'banana890', '2023-05-15'),"
					+ "(4, 'PayPal', 1, 8000, 'grape123', '2023-05-23'),"
					+ "(5, 'BankTransfer', 1, 8000, 'kiwi456', '2023-06-05'),"
					+ "(6, 'Cash', 0, 8000, 'melon789', '2023-06-12'),"
					+ "(7, 'CreditCard', 1, 8000, 'strawberry012', '2023-06-20'),"
					+ "(8, 'DebitCard', 1, 16000, 'pineapple345', '2023-06-26'),"
					+ "(9, 'Cash', 0, 8000, 'blueberry678', '2023-07-04'),"
					+ "(10, 'PayPal', 1, 8000, 'pear901', '2023-07-12'),"
					+ "(11, 'BankTransfer', 1, 8000, 'cherry234', '2023-07-19'),"
					+ "(12, 'CreditCard', 1, 8000, 'watermelon567', '2023-07-27'),"
					+ "(13, 'DebitCard', 1, 25000, 'apple124', '2023-08-02'),"
					+ "(14, 'Cash', 0, 10000, 'orange567', '2023-08-08'),"
					+ "(15, 'PayPal', 1, 16000, 'banana890', '2023-08-15');";
			stmt.executeUpdate(insertPayInfoData);
			System.out.println("payInfo 데이터 추가 완료");
			
			String insertSeatData = "INSERT INTO seat (seatId, roomId, isUsed, colX, rowY, showScheduleId)"
					+ "VALUES (1, 1, 1, 8, 3, 4),"
					+ "       (2, 1, 1, 4, 5, 4),"
					+ "       (3, 3, 1, 9, 1, 3),"
					+ "       (4, 3, 1, 2, 2, 12),"
					+ "       (5, 4, 1, 10, 9, 6),"
					+ "       (6, 5, 1, 15, 15, 7),"
					+ "       (7, 6, 1, 4, 2, 10),"
					+ "       (8, 7, 1, 6, 8, 5),"
					+ "       (9, 8, 1, 4, 4, 11),"
					+ "       (10, 10, 1, 3, 2, 2),"
					+ "       (11, 11, 1, 15, 15, 8),"
					+ "       (12, 12, 1, 10, 6, 1),"
					+ "       (13, 4, 1, 4, 4, 6),"
					+ "       (14, 5, 1, 9, 17, 14),"
					+ "       (15, 6, 1, 5, 6, 10),"
					+ "       (16, 7, 1, 5, 2, 5),"
					+ "       (17, 8, 1, 3, 3, 13),"
					+ "       (18, 9, 1, 1, 8, 9),"
					+ "       (19, 10, 1, 8, 7, 15),"
					+ "       (20, 11, 1, 12, 12, 16);";
			stmt.executeUpdate(insertSeatData);
			System.out.println("seat 데이터 추가 완료");
			
			String insertTicketData = "INSERT INTO ticket (ticketId, seatId, roomId, showScheduleId, payId, isPrinted, averageSale, price)"
					+ "VALUES (1, 1, 1, 4, 1, 1, 10000, 9000),"
					+ "       (2, 2, 1, 4, 2, 0, 10000, 8000),"
					+ "       (3, 3, 3, 3, 3, 0, 10000, 8000),"
					+ "       (4, 4, 3, 12, 3, 1, 12000, 10000),"
					+ "       (5, 5, 4, 6, 4, 1, 10000, 8000),"
					+ "       (6, 6, 5, 7, 5, 1, 10000, 8000),"
					+ "       (7, 7, 6, 10, 6, 0, 10000, 8000),"
					+ "       (8, 8, 7, 5, 7, 0, 10000, 8000),"
					+ "       (9, 9, 8, 11, 8, 0, 10000, 8000),"
					+ "       (10, 10, 10, 2, 8, 1, 10000, 8000),"
					+ "       (11, 11, 11, 8, 9, 0, 10000, 8000),"
					+ "       (12, 12, 12, 1, 10, 1, 10000, 8000),"
					+ "       (13, 13, 4, 6, 11, 1, 10000, 8000),"
					+ "       (14, 14, 5, 14, 12, 0, 12000, 8000),"
					+ "       (15, 15, 6, 10, 13, 0, 12000, 8000),"
					+ "       (16, 16, 7, 5, 13, 0, 12000, 9000),"
					+ "       (17, 17, 8, 13, 13, 1, 12000, 8000),"
					+ "       (18, 18, 9, 9, 14, 0, 12000, 10000),"
					+ "       (19, 19, 10, 15, 15, 1, 12000, 8000),"
					+ "       (20, 20, 11, 16, 15, 1, 12000, 8000);";

			stmt.executeUpdate(insertTicketData);
			System.out.println("ticket 데이터 추가 완료");
			
			
			return 0;
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
			return -1;
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	public static List<String> printAllTable() {
		List<String> returnResult = new ArrayList<>();
		String jdbcUrl = "jdbc:mysql://localhost:3306/db1"; // 데이터베이스 URL
        String jdbcUser = "root"; // MySQL 사용자 이름
        String jdbcPassword = "1234"; // MySQL 사용자 비밀번호
        String query;
        String format;
        String header;

        // 데이터베이스 연결
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            // SELECT 쿼리 실행
            query = "SELECT * FROM movie"; // 테이블 이름을 원하는 대로 변경하세요
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                // 테이블 헤더 출력
                format = "| %-8s | %-20s | %-10s | %-40s | %-60s | %-30s | %-50s | %-10s | %-4s |%n";
                System.out.format(format, "MovieID", "MovieName", "MovieGrade", "Director", "Actor", "Genre", "MovieSummary", "OpenDate", "Rate");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                header = String.format(format, "MovieID", "MovieName", "MovieGrade", "Director", "Actor", "Genre", "MovieSummary", "OpenDate", "Rate");
                returnResult.add(header);
                returnResult.add("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                // 결과 출력
                while (resultSet.next()) {
                    int movieId = resultSet.getInt("movieId");
                    String movieName = resultSet.getString("movieName");
                    int moveGrade = resultSet.getInt("moveGrade");
                    String director = resultSet.getString("director");
                    String actor = resultSet.getString("actor");
                    String genre = resultSet.getString("genre");
                    String movieSummary = resultSet.getString("movieSummary");
                    String openDate = resultSet.getDate("openDate").toString();
                    int rate = resultSet.getInt("rate");

                    String row = String.format(format, movieId, movieName, moveGrade, director, actor, genre, movieSummary, openDate, rate);
                    returnResult.add(row);
                    System.out.println(row);
                    
                }
            }
            query = "SELECT * FROM room"; // 테이블 이름을 원하는 대로 변경하세요
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                // 테이블 헤더 출력
                format = "| %-8s | %-8s | %-8s | %-6s |%n";
                header = String.format(format, "RoomID", "SeatCol", "SeatRow", "IsUsed");;
                System.out.println(header);
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                returnResult.add(header);
                returnResult.add("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                // 결과 출력
                while (resultSet.next()) {
                	int roomId = resultSet.getInt("roomId");
                    int seatCol = resultSet.getInt("seatCol");
                    int seatRow = resultSet.getInt("seatRow");
                    int isUsed = resultSet.getInt("isUsed");

                    String row = String.format(format, roomId, seatCol, seatRow, isUsed);
                    returnResult.add(row);

                    System.out.println(row);
                    
                }
            }
            
            query = "SELECT * FROM showSchedule"; 
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                format = "| %-15s | %-7s | %-7s | %-15s | %-7s | %-9s | %-8s |%n";
                header = String.format(format, "ShowScheduleID", "MovieID", "RoomID", "ShowingStartDay", "DayType", "ShowRound", "StartTime");
                System.out.println(header);
                System.out.println("------------------------------------------------------------------------------------------------------");

                returnResult.add(header);
                returnResult.add("------------------------------------------------------------------------------------------------------");

                while (resultSet.next()) {
                    int showScheduleId = resultSet.getInt("showScheduleId");
                    int movieId = resultSet.getInt("movieId");
                    int roomId = resultSet.getInt("roomId");
                    String showingStartDay = resultSet.getDate("showingStartDay").toString();
                    String dayType = resultSet.getString("dayType");
                    int showRound = resultSet.getInt("showRound");
                    String startTime = resultSet.getTime("startTime").toString();

                    String row = String.format(format, showScheduleId, movieId, roomId, showingStartDay, dayType, showRound, startTime);
                    returnResult.add(row);

                    System.out.println(row);
                }
            }
            
            query = "SELECT * FROM userInfo"; 
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                format = "| %-20s | %-30s | %-12s | %-30s |%n";
                header = String.format(format, "UserID", "Name", "PhoneNumber", "AddressEmail");
                System.out.println(header);
                System.out.println("-------------------------------------------------------------------------------------------");

                returnResult.add(header);
                returnResult.add("-------------------------------------------------------------------------------------------");

                // 결과 출력 및 저장
                while (resultSet.next()) {
                    String userId = resultSet.getString("userId");
                    String name = resultSet.getString("name");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String addressEmail = resultSet.getString("addressEmail");

                    String row = String.format(format, userId, name, phoneNumber, addressEmail);
                    returnResult.add(row);

                    System.out.println(row);
                }
            }
            query = "SELECT * FROM payInfo"; // 테이블 이름을 원하는 대로 변경하세요
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                // 테이블 헤더 출력 및 저장
                format = "| %-8s | %-20s | %-8s | %-8s | %-20s | %-10s |%n";
                header = String.format(format, "PayID", "PayMethod", "PayState", "Price", "UserID", "PayDate");
                System.out.println(header);
                System.out.println("--------------------------------------------------------------------------");

                returnResult.add(header);
                returnResult.add("--------------------------------------------------------------------------");

                // 결과 출력 및 저장
                while (resultSet.next()) {
                    int payId = resultSet.getInt("payId");
                    String payMethod = resultSet.getString("payMethod");
                    int payState = resultSet.getInt("payState");
                    int price = resultSet.getInt("price");
                    String userId = resultSet.getString("userId");
                    String payDate = resultSet.getString("payDate");

                    String row = String.format(format, payId, payMethod, payState, price, userId, payDate);
                    returnResult.add(row);

                    System.out.println(row);
                }
            }
            
            query = "SELECT * FROM seat"; // 테이블 이름을 원하는 대로 변경하세요
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                // 테이블 헤더 출력 및 저장
                format = "| %-8s | %-8s | %-6s | %-5s | %-5s | %-15s |%n";
                header = String.format(format, "SeatID", "RoomID", "IsUsed", "ColX", "RowY", "ShowScheduleID");
                System.out.println(header);
                System.out.println("-------------------------------------------------------------");

                returnResult.add(header);
                returnResult.add("-------------------------------------------------------------");

                // 결과 출력 및 저장
                while (resultSet.next()) {
                    int seatId = resultSet.getInt("seatId");
                    int roomId = resultSet.getInt("roomId");
                    int isUsed = resultSet.getInt("isUsed");
                    int colX = resultSet.getInt("colX");
                    int rowY = resultSet.getInt("rowY");
                    int showScheduleId = resultSet.getInt("showScheduleId");

                    String row = String.format(format, seatId, roomId, isUsed, colX, rowY, showScheduleId);
                    returnResult.add(row);

                    System.out.println(row);
                }
            }
            
            query = "SELECT * FROM ticket"; // 테이블 이름을 원하는 대로 변경하세요
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                // 테이블 헤더 출력 및 저장
                format = "| %-8s | %-14s | %-8s | %-8s | %-8s | %-10s | %-12s | %-6s |%n";
                header = String.format(format, "TicketID", "ShowScheduleID", "RoomID", "SeatID", "PayID", "IsPrinted", "AverageSale", "Price");
                System.out.println(header);
                System.out.println("------------------------------------------------------------------------------------------------------");

                returnResult.add(header);
                returnResult.add("------------------------------------------------------------------------------------------------------");

                // 결과 출력 및 저장
                while (resultSet.next()) {
                    int ticketId = resultSet.getInt("ticketId");
                    int showScheduleId = resultSet.getInt("showScheduleId");
                    int roomId = resultSet.getInt("roomId");
                    int seatId = resultSet.getInt("seatId");
                    int payId = resultSet.getInt("payId");
                    int isPrinted = resultSet.getInt("isPrinted");
                    int averageSale = resultSet.getInt("averageSale");
                    int price = resultSet.getInt("price");

                    String row = String.format(format, ticketId, showScheduleId, roomId, seatId, payId, isPrinted, averageSale, price);
                    returnResult.add(row);

                    System.out.println(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return returnResult;
	}
	
	public static int delete(String tableName, String condition) {
		String query = "DELETE FROM " + tableName + " WHERE " + condition + ";";
		int rowsAffected;
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "1234");
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        	rowsAffected = preparedStatement.executeUpdate();
        	System.out.println("Deleted " + rowsAffected + " rows from table " + tableName);
	        
	    } catch (SQLException e) {
	        //e.printStackTrace();
	        return -1;
	    }
		return rowsAffected; //삭제 시 삭제된 row 수(0 이상이 정상 실행)
	}
	
	public static int update(String tableName, String setString, String condition) {
		String query = "Update " + tableName + " Set "+ setString +" WHERE " + condition + ";";
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "1234");
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        	int rowsAffected = preparedStatement.executeUpdate();
        	System.out.println("Updated " + rowsAffected + " rows from table " + tableName);
	        
	    } catch (SQLException e) {
	        return -1;
	    }
		return 0;
	}
	// 사용자 권한
	public static List<String> printMovie(String MovieName, String Director, String Actor, String Genre){
		//Director나 Actor는 한 명의 이름씩만 넣기
		List<String> returnResult = new ArrayList<>();
		String jdbcUrl = "jdbc:mysql://localhost:3306/db1"; // 데이터베이스 URL
        String jdbcUser = "user1"; // MySQL 사용자 이름
        String jdbcPassword = "user1"; // MySQL 사용자 비밀번호
        String query;
        String format;
        String header;
        
        // 데이터베이스 연결
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            // SELECT 쿼리 실행
            query = "SELECT * FROM movie"; // 테이블 이름을 원하는 대로 변경하세요
            String Condi = " WHERE ";
            boolean isExistCondition = false;
            if(MovieName != "") {
            	Condi += "movie.movieName = \'"+MovieName+"\'";
            	isExistCondition = true;
            }
            if(Director != "") {
            	if(isExistCondition) {
            		Condi += " AND ";
            	}
            	else {
            		isExistCondition = true;
            	}
            	
            	Condi += "movie.director LIKE \'%"+Director+"%\'";
            	isExistCondition = true;
            }
            if(Actor != "") {
            	if(isExistCondition) {
            		Condi += " AND ";
            	}
            	else {
            		isExistCondition = true;
            	}
            	
            	Condi += "movie.actor LIKE \'%"+Actor+"%\'";
            	isExistCondition = true;
            }
            if(Genre != "") {
            	if(isExistCondition) {
            		Condi += " AND ";
            	}
            	else {
            		isExistCondition = true;
            	}
            	
            	Condi += "movie.genre = \'"+Genre+"\'";
            	isExistCondition = true;
            }
            if(isExistCondition) {
            	query += Condi;
            }
            query +=";";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                // 테이블 헤더 출력
                format = "| %-8s | %-20s | %-10s | %-40s | %-60s | %-30s | %-50s | %-10s | %-4s |%n";
                System.out.format(format, "MovieID", "MovieName", "MovieGrade", "Director", "Actor", "Genre", "MovieSummary", "OpenDate", "Rate");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                header = String.format(format, "MovieID", "MovieName", "MovieGrade", "Director", "Actor", "Genre", "MovieSummary", "OpenDate", "Rate");
                returnResult.add(header);
                returnResult.add("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                // 결과 출력
                while (resultSet.next()) {
                    int movieId = resultSet.getInt("movieId");
                    String movieName = resultSet.getString("movieName");
                    int moveGrade = resultSet.getInt("moveGrade");
                    String director = resultSet.getString("director");
                    String actor = resultSet.getString("actor");
                    String genre = resultSet.getString("genre");
                    String movieSummary = resultSet.getString("movieSummary");
                    String openDate = resultSet.getDate("openDate").toString();
                    int rate = resultSet.getInt("rate");

                    String row = String.format(format, movieId, movieName, moveGrade, director, actor, genre, movieSummary, openDate, rate);
                    returnResult.add(row);
                    System.out.println(row);
                    
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return returnResult;
	}
	
	public static List<String> seatBySchedule(int scheduleID){
		//스케쥴 아이디 입력
		//출력 첫째 줄 : 해당 스케쥴의 room 좌석 가로 수 세로 수 ex) 20,15
		//출력 둘째 줄 : 해당 스케쥴의 room 속 예매된 좌석 좌표들 ex) 12,2 14,5 20,1
		List<String> returnResult = new ArrayList<>();
		String jdbcUrl = "jdbc:mysql://localhost:3306/db1";
        String jdbcUser = "user1";
        String jdbcPassword = "user1";
        String query;
        String query2;
        String query3;
        // 데이터베이스 연결
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            // SELECT 쿼리 실행
            query = "SELECT * FROM showSchedule WHERE showSchedule.showScheduleId = "+scheduleID+";"; // 
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                int tRoomId = -1;
                if (resultSet.next()) {
                    tRoomId = resultSet.getInt("roomId");
                }
                query2 = "SELECT * FROM room WHERE room.roomId = " + tRoomId + ";";
                resultSet = statement.executeQuery(query2);
                if (resultSet.next()) {
                    int seatC = resultSet.getInt("seatCol");
                    int seatR = resultSet.getInt("seatRow");
                    String line = seatC + "," + seatR;
                    returnResult.add(line);
                    System.out.println(line);
                }
                query3 = "SELECT * FROM seat WHERE seat.showScheduleId = "+scheduleID+";";
                resultSet = statement.executeQuery(query3);
                String line="";
                while (resultSet.next()) {
                    int seatC = resultSet.getInt("colX");
                    int seatR = resultSet.getInt("rowY");
                    line += seatC + "," + seatR +" ";
                }
                returnResult.add(line);
                System.out.println(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return returnResult;
	}
	
	public static int bookTicket(int n, int[] colx, int[] rowy, int[] ticketPrice, int scheduleid, String paymethod) {
        String query = "";
        int tRoomId = -1;
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "user1", "user1")){
    		query = "SELECT * FROM showSchedule WHERE showSchedule.showScheduleId = "+scheduleid+";"; // 
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    tRoomId = resultSet.getInt("roomId");
                }
                int totalPrice = 0;
                for(int i=0; i<n;i++) {
                	totalPrice += ticketPrice[i];
	                query = "INSERT INTO seat (roomId, isUsed, colX, rowY, showScheduleId) VALUE ("+tRoomId 
	                		+","+1+","+colx[i]+","+rowy[i]+","+scheduleid+");";
	                statement.executeUpdate(query);
                }
                
                query = "INSERT INTO payInfo (payMethod, payState, price, userId, payDate) VALUE (\'"+paymethod+"\',"+0+","+totalPrice+", \'user1\', curdate());";
                statement.executeUpdate(query);
                
                for(int i=0; i<n;i++) {
	                query = "INSERT INTO ticket (showScheduleId, roomId, seatId, PayID, isPrinted, averageSale, price) VALUE ("+scheduleid
	                		+","+tRoomId+", (select seatId from seat where showScheduleId = "+scheduleid+" and colX = " +colx[i]+" and rowY = "+rowy[i]+" ),(select max(payInfo.payId) from payInfo), 0,"+ticketPrice[i]+","+ticketPrice[i]+");";
	                statement.executeUpdate(query);
                }
	        }
	    } catch (SQLException e) {
	        return -1;
	    }
		return 0;
	}
	
	public static int cancelPay(int payID) {
		String query = "";
        int tRoomId = -1;
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "user1", "user1")){
            try (Statement statement = connection.createStatement()) {
            	query = "SELECT * FROM ticket WHERE ticket.payId = "+payID+";";
                ResultSet resultSet = statement.executeQuery(query);
                List <Integer> seatsToDelete = new ArrayList<>();
                int n = 0;
                while (resultSet.next()) {
                	n += 1;
                	seatsToDelete.add(resultSet.getInt("seatId"));
                }
                query = "DELETE from ticket WHERE ticket.payId = "+payID+";";
                statement.executeUpdate(query);
                
                for(int i=0; i<n;i++) {
                	query = "DELETE from seat WHERE seat.seatId = "+seatsToDelete.removeFirst()+";";
                    statement.executeUpdate(query);
                }
                query = "DELETE from payInfo WHERE payInfo.payId = "+payID+";";
                statement.executeUpdate(query);
            }
	    } catch (SQLException e) {
            e.printStackTrace();
	        return -1;
	    }
		return 0;
	}
	
	/*public static List<String> checkMyPayment(){
		List<String> returnResult = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "user1", "user1")) {
            try (Statement statement = connection.createStatement()) {
                
                String query = "SELECT * FROM payInfo WHERE payInfo.userId = user1;";
                ResultSet resultSet = statement.executeQuery(query);
                
                String format = "| %-8s | %-20s | %-8s | %-8s | %-20s | %-10s |%n";
                
                System.out.format(format, "PayID", "PayMethod", "PayState", "Price", "UserID", "PayDate");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                String header = String.format(format, "PayID", "PayMethod", "PayState", "Price", "UserID", "PayDate");
                returnResult.add(header);
                returnResult.add("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                
                while (resultSet.next()) {
                    int seatC = resultSet.getInt("colX");
                    int seatR = resultSet.getInt("rowY");
                    String row = String.format(format, movieId, movieName, moveGrade, director, actor, genre, movieSummary, openDate, rate);
                    returnResult.add(row);
                    System.out.println(row);
                }
                returnResult.add(line);
                System.out.println(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return returnResult;
	}
	*/
	public static void main (String[] args) {
		
		init();
		//delete("movie", "movie.movieId=3");
		//update("movie", "movie.movieName = \'test1\'", "movie.movieId = 3");
		//printAllTable();
		//printMovie("Midnight Sun","","Nicholas Ward","");
		//seatBySchedule(4);
		/*int [] x = {3,4};
		int [] y = {5,6};
		int [] pay = {12345,34252};
		bookTicket(2, x, y, pay, 4,"Cash");
		printAllTable();
		cancelPay(16);
		printAllTable();*/
		
		
	}
}
