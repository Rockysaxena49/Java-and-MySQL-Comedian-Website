import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/PeopleDAO")
public class PeopleDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	
	public PeopleDAO() {}
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/classproject?"
  			          + "useSSL=false&user=project&password=Project1234");
            System.out.println(connect);
        }
    }
    
    public boolean addNewUser(User newUser) throws SQLException {
    	connect_func();
    	
    	String getAllUsers = "SELECT Username FROM user";
    	statement = (Statement) connect.createStatement();
    	ResultSet usernames = statement.executeQuery(getAllUsers);
    	
    	while(usernames.next()) {
    		if(usernames.getString("Username").equals(newUser.username)) {
    			usernames.close();
    			statement.close();
    			disconnect();
    			System.out.println("Username already exists");
    			return false;
    		}
    	}
    	
		String insert = "INSERT INTO user(Username, Password, FirstName, LastName, Age) VALUES (?, ?, ?, ?, ?)";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(insert);
    	preparedStatement.setString(1, newUser.username);
    	preparedStatement.setString(2, newUser.password);
    	preparedStatement.setString(3, newUser.firstName);
    	preparedStatement.setString(4, newUser.lastName);
    	preparedStatement.setString(5, Integer.toString(newUser.age));
    	int rowInserted = preparedStatement.executeUpdate();
    	preparedStatement.close();
    	disconnect();
    	return rowInserted > 0;
    }
    
    public boolean checkLogin(User loginInfo) throws SQLException {
    	connect_func();
    	
    	String sql = "SELECT * FROM user WHERE Username='" + loginInfo.username + "'";
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	if(!resultSet.next()) {
    		return false;
    	}
    	String databasePassword = resultSet.getString("Password");
    	resultSet.close();
        statement.close();
        disconnect();
    	if(loginInfo.password.equals(databasePassword)) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public User getUserInfo(User loginInfo) throws SQLException {
    	connect_func();
    	
    	String sql = "SELECT * FROM user WHERE Username='" + loginInfo.username + "'";
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	resultSet.next();
    	User userInfo;
    	if(loginInfo.username.contentEquals("root")) {
    		userInfo = new User(resultSet.getString("Username"), resultSet.getString("Password"));
    		
    	}else {
    		userInfo = new User(resultSet.getString("Username"), resultSet.getString("Password"),
        			resultSet.getString("FirstName"), resultSet.getString("LastName"),
        			Integer.parseInt(resultSet.getString("Age")));
    	}
    	resultSet.close();
        statement.close();     
        disconnect();
    	return userInfo;
    }
    
    public void dropAllTables() throws SQLException{
    	connect_func();
    	
    	String[] dropStatements = {"DROP TABLE isfavorite", "DROP TABLE youtubetags", "DROP TABLE reviews", "DROP TABLE youtubevideos",
    			"DROP TABLE comedians", "DROP TABLE user"};
    	
    	String[] createStatements = {"CREATE TABLE user (Username CHAR(50), Password CHAR(20), FirstName CHAR(50), LastName CHAR(50), Age INTEGER, PRIMARY KEY(Username))",
					"CREATE TABLE comedians (comid INTEGER, FirstName VARCHAR(50), LastName VARCHAR(50), Birthday DATE, BirthPlace VARCHAR(50), PRIMARY KEY(comid))",
					"CREATE TABLE youtubevideos (url VARCHAR(150), Title VARCHAR(50), VideoDescription VARCHAR(200), comid INTEGER, PostUser VARCHAR(50), PostDate DATE, PRIMARY KEY (url), FOREIGN KEY (comid) REFERENCES comedians(comid))",
					"CREATE TABLE reviews (reviewid INTEGER NOT NULL auto_increment, Remark VARCHAR(100), Rating CHAR(1), Author VARCHAR(50) NOT NULL, Youtubeid VARCHAR(150) NOT NULL, PRIMARY KEY (reviewid), FOREIGN KEY (Youtubeid) REFERENCES YoutubeVideos(url), CONSTRAINT RatingCheck CHECK (Rating IN ('P', 'F', 'G', 'E')))",
					"CREATE TABLE youtubetags (url VARCHAR(150), Tag VARCHAR(50), PRIMARY KEY(url, Tag))",
    				"CREATE TABLE isfavorite (Username VARCHAR(50), comid INTEGER, PRIMARY KEY (Username, comid), FOREIGN KEY (comid) REFERENCES Comedians (comid))"};
    	
    	String addRootUser = "INSERT INTO user(Username, Password) VALUES ('root','pass1234')";
    	
    	String[] users = {"INSERT INTO user(Username, Password, FirstName, LastName, Age) VALUES ('user1', 'pass1', 'user1First', 'user1Last', 1)",
    			"INSERT INTO user(Username, Password, FirstName, LastName, Age) VALUES ('user2', 'pass2', 'user2First', 'user2Last', 2)",
    			"INSERT INTO user(Username, Password, FirstName, LastName, Age) VALUES ('user3', 'pass3', 'user3First', 'user3Last', 3)",
    			"INSERT INTO user(Username, Password, FirstName, LastName, Age) VALUES ('user4', 'pass4', 'user4First', 'user4Last', 4)",
    			"INSERT INTO user(Username, Password, FirstName, LastName, Age) VALUES ('user5', 'pass5', 'user5First', 'user5Last', 5)",
    			"INSERT INTO user(Username, Password, FirstName, LastName, Age) VALUES ('user6', 'pass6', 'user6First', 'user6Last', 6)",
    			"INSERT INTO user(Username, Password, FirstName, LastName, Age) VALUES ('user7', 'pass7', 'user7First', 'user7Last', 7)",
    			"INSERT INTO user(Username, Password, FirstName, LastName, Age) VALUES ('user8', 'pass8', 'user8First', 'user8Last', 8)",
    			"INSERT INTO user(Username, Password, FirstName, LastName, Age) VALUES ('user9', 'pass9', 'user9First', 'user9Last', 9)",
    			"INSERT INTO user(Username, Password, FirstName, LastName, Age) VALUES ('user10', 'pass10', 'user10First', 'user10Last', 10)"
    			};
    	
    	String[] comedians = {"INSERT INTO comedians(comid, FirstName, LastName, Birthday, BirthPlace) VALUES (1, 'Dane', 'Cook', '1972-03-18', 'Cambridge, MA')",
    			"INSERT INTO comedians(comid, FirstName, LastName, Birthday, BirthPlace) VALUES (2, 'Gabriel', 'Iglesias', '1976-07-15', 'San Diego, CA')",
    			"INSERT INTO comedians(comid, FirstName, LastName, Birthday, BirthPlace) VALUES (3, 'Bill', 'Burr', '1968-06-10', 'Canton, MA')", 
    			"INSERT INTO comedians(comid, FirstName, LastName, Birthday, BirthPlace) VALUES (4, 'Chris', 'Rock', '1965-02-07', 'Andrews, SC')", 
    			"INSERT INTO comedians(comid, FirstName, LastName, Birthday, BirthPlace) VALUES (5, 'Sebastian', 'Maniscalco', '1973-07-08', 'Arlington Heights, IL')",
    			"INSERT INTO comedians(comid, FirstName, LastName, Birthday, BirthPlace) VALUES (6, 'George', 'Lopez', '1961-04-23', 'Los Angeles, CA')", 
    			"INSERT INTO comedians(comid, FirstName, LastName, Birthday, BirthPlace) VALUES (7, 'Kevin', 'Hart', '1979-07-06', 'Philadelphia, PA')", 
    			"INSERT INTO comedians(comid, FirstName, LastName, Birthday, BirthPlace) VALUES (8, 'Dave', 'Chappelle', '1973-08-24', 'Washington, D.C.')"
    			};
    	
    	String[] videos = {"INSERT INTO youtubevideos(url, Title, VideoDescription, comid, PostUser, PostDate) VALUES ('https://www.youtube.com/watch?v=YDkOZaolWQE', 'Hot and Fluffy', 'Gabriel Iglesias is one of the fastest rising comics today!', 2, 'user1', '2020-06-26')",
    			"INSERT INTO youtubevideos(url, Title, VideoDescription, comid, PostUser, PostDate) VALUES ('https://www.youtube.com/watch?v=x2X6I4LShac', 'What was your favorite mix tape name?', 'My First special �Sebastian LIVE!� Is now available on @amazonprime.', 5, 'user7', '2020-06-26')",
    			"INSERT INTO youtubevideos(url, Title, VideoDescription, comid, PostUser, PostDate) VALUES ('https://www.youtube.com/watch?v=JxhG3H2-EIE', 'For What Its Worth', 'Full video. stand up comedy', 8, 'user2', '2020-06-26')",
    			"INSERT INTO youtubevideos(url, Title, VideoDescription, comid, PostUser, PostDate) VALUES ('https://www.youtube.com/watch?v=1h5sRgW6sQY', 'Bad Apple Metaphor', 'Chris Rocks bad apple metaphor for bad cops.', 4, 'user2', '2020-06-26')",
    			"INSERT INTO youtubevideos(url, Title, VideoDescription, comid, PostUser, PostDate) VALUES ('https://www.youtube.com/watch?v=NBO3vF8p0J0', 'Netflix Is A Joke', 'Kevin Hart shows all of his cards in his very own hilarious and ridiculous way as he talks about getting kicked in the face.', 7, 'user5', '2020-06-26')",
    			"INSERT INTO youtubevideos(url, Title, VideoDescription, comid, PostUser, PostDate) VALUES ('https://www.youtube.com/watch?v=iIp93sEmzQM', 'Has anyone been to the gym lately?', 'STAY HUNGRY Special on @Netflix Is A Joke', 5, 'user6', '2020-06-26')"
    			};
    	
    	String[] tags = {"INSERT INTO youtubetags(url, Tag) VALUES ('https://www.youtube.com/watch?v=YDkOZaolWQE', 'fluffy, hot')",
    			"INSERT INTO youtubetags(url, Tag) VALUES ('https://www.youtube.com/watch?v=x2X6I4LShac', 'mixtape')",
    			"INSERT INTO youtubetags(url, Tag) VALUES ('https://www.youtube.com/watch?v=JxhG3H2-EIE', 'worth')",
    			"INSERT INTO youtubetags(url, Tag) VALUES ('https://www.youtube.com/watch?v=1h5sRgW6sQY', 'bad, apple, cops')",
    			"INSERT INTO youtubetags(url, Tag) VALUES ('https://www.youtube.com/watch?v=NBO3vF8p0J0', 'netflix, face')",
    			"INSERT INTO youtubetags(url, Tag) VALUES ('https://www.youtube.com/watch?v=iIp93sEmzQM', 'gym')"};
    	
    	statement = (Statement) connect.createStatement();
    	
    	try {
    		for(int i = 0; i < dropStatements.length; i++) {
        		statement.execute(dropStatements[i]);
        	}
    		for(int i = 0; i < createStatements.length; i++) {
        		statement.execute(createStatements[i]);
        	}
        	
        	statement.execute(addRootUser);
        	
        	for(int i = 0; i < 10; i++) {
        		statement.execute(users[i]);
        	}
        	
        	for(int i = 0; i < comedians.length; i++) {
        		statement.execute(comedians[i]);
        	}
        	
        	for(int i = 0; i < videos.length; i++) {
        		statement.execute(videos[i]);
        		statement.execute(tags[i]);
        	}
    	    } catch (Exception e) {
    	      System.out.println("Something went wrong.");
    	      for(int i = 1; i < createStatements.length; i++) {
    	    		statement.execute(createStatements[i]);
    	    	}
    	    	
    	    	for(int i = 0; i < 10; i++) {
    	    		statement.execute(users[i]);
    	    	}
    	    	
    	    	for(int i = 0; i < comedians.length; i++) {
    	    		statement.execute(comedians[i]);
    	    	}
    	    	
    	    	for(int i = 0; i < videos.length; i++) {
    	    		statement.execute(videos[i]);
    	    		statement.execute(tags[i]);
    	    	}
    	    }
    	
    	statement.close();
    	disconnect();
    }
    
    public List<User> getAllUsers() throws SQLException{
    	List<User> listOfUsers = new ArrayList<User>(); 
    	connect_func();
    	
    	String sql = "SELECT * FROM user";
    	statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	if(resultSet.getString("Username").equals("root")) {
        		continue;
        	}else {
        		String username = resultSet.getString("Username");
        		String password = resultSet.getString("Password");
        		String firstName = resultSet.getString("FirstName");
        		String lastName = resultSet.getString("LastName");
        		int age = Integer.parseInt(resultSet.getString("Age"));
        		
        		User newUser = new User(username, password, firstName, lastName, age);
                listOfUsers.add(newUser);
        	}
            
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listOfUsers;
    }
    // Function to insert video into MYSQL database.
    public void insertVideo(String userName, String link, String videoTitle, String videoDescription, String videoTags, String comid) throws SQLException{
    	connect_func();
    	
    	
    	// This line of code is to get the current date and convert object to string 
    	long millis=System.currentTimeMillis();  
    	java.sql.Date date=new java.sql.Date(millis);  
    	String dateString=date.toString(); 
    	
    	// This line of code is to see if the url (String link) is already in the youtubevideos table
    	String urlCheck = "SELECT COUNT(*) FROM youtubevideos WHERE URL ='"+ link + "'";
    	statement = (Statement) connect.createStatement();
    	ResultSet urlExist = statement.executeQuery(urlCheck);
    	if (urlExist.next()) {
    		int numUrl = urlExist.getInt(1);
        	if (numUrl > 0) {
        		System.out.println("The URL is already in the table cannot add video");
        		return;
        	}
    	}else {
    		System.out.println("Error checking if the URL exist");
    		return;
    	}
    	
    	// This line of code is to check if the user has posted more than 5 videos in same day
    	String numberVideoPosts = "SELECT COUNT(*) FROM youtubevideos WHERE PostUser ='"+ userName +"' AND PostDate = '"+ dateString +"'";
    	ResultSet videoNum = statement.executeQuery(numberVideoPosts);
    	if (videoNum.next()) {
    		int numberOfVideos = videoNum.getInt(1); // 1 is the column number starts at 1
        	System.out.println("Number of Videos Posted in a day: " + numberOfVideos);
        	if (numberOfVideos < 5) { // if video posts in a day are less than 5 then continue adding video
        		// This line of code is going to extract the comid from the comedian table based on
            	// the nameComedian string which holds the comedians last name which is used to search for 
            	// comid in the comedian table.
            	// This line of code is to insert video into youtubevideos table
            	String insert = "INSERT INTO youtubevideos(url, Title, VideoDescription, comid, PostUser, PostDate) "
            			+ "VALUES (?, ?, ?, ?, ?, ?)";
            	preparedStatement = (PreparedStatement) connect.prepareStatement(insert);
            	preparedStatement.setString(1, link);
            	preparedStatement.setString(2, videoTitle);
            	preparedStatement.setString(3, videoDescription);
            	preparedStatement.setString(4, comid);
            	preparedStatement.setString(5, userName);
            	preparedStatement.setString(6, dateString);
            	preparedStatement.executeUpdate();
            	preparedStatement.close();
            	// This line of code is to insert into youtubetags table
            	String enter = "INSERT INTO youtubetags(url, Tag) "
            			+ "VALUES (?, ?)";
            	preparedStatement = (PreparedStatement) connect.prepareStatement(enter);
            	preparedStatement.setString(1, link);
            	preparedStatement.setString(2, videoTags);
            	preparedStatement.executeUpdate();
            	preparedStatement.close();
            	statement.close();
            	disconnect();
            	System.out.println("Sucessfully inserted a video and added video tags");
        	}
        	else {
        		System.out.println("The number of videos allowed to post in a day exceeds the limit 5");
        		return; 
        	}
       	}else {
       		System.out.println("Error checking number of videos posted in a day");
       		return; 
       	}
    	
    	
    }
    
    public List<YoutubeVideo> getSearchResults(String userInput) throws SQLException {
    	connect_func();
    	List<YoutubeVideo> searchResults = new ArrayList<YoutubeVideo>();
    	String sql;
    	if(userInput.contains(" ")) {
    		String[] query = userInput.split(" ");
    		String first = query[0];
    		String second = query[1];
    		sql = "SELECT comid FROM comedians WHERE FirstName='" + first + "' OR FirstName='" + second + "' OR LastName='" + first + "' OR LastName='" + second + "'";
    	}else if(userInput.contains(",")){
    		String[] query = userInput.split(",");
    		sql = "SELECT url FROM youtubetags WHERE Tag LIKE '%" + query[0] + "%' ";
    		for(int i = 1; i < query.length; i++) {
    			sql = sql.concat("OR Tag LIKE '%" + query[i] +"%' ");
    		}
    		statement =  (Statement) connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()) {
            	String sql2 = "SELECT * FROM youtubevideos WHERE url='" + resultSet.getString("url") + "'";
            	Statement statement2 =  (Statement) connect.createStatement();
                ResultSet videoResultSet = statement2.executeQuery(sql2);
                while(videoResultSet.next()) {
        			YoutubeVideo video = new YoutubeVideo(videoResultSet.getString("url"), videoResultSet.getString("Title"), videoResultSet.getString("PostUser"));
        			searchResults.add(video);
        		}
            }
            
            resultSet.close();
        	statement.close();         
            disconnect();
        	return searchResults;
    		
    	}else {
    		sql = "SELECT comid FROM comedians WHERE FirstName='" + userInput + "' OR LastName='" + userInput + "'";
    	}
    	
    	statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<String> comids = new ArrayList<String>(); 
        while(resultSet.next()) {
        	comids.add(resultSet.getString("comid"));
        }
        
        for(int i = 0; i < comids.size();i++) {
        	String sql2 = "SELECT * FROM youtubevideos WHERE comid='" + comids.get(i) + "'";
    		ResultSet videoResultSet = statement.executeQuery(sql2);
    		while(videoResultSet.next()) {
    			YoutubeVideo video = new YoutubeVideo(videoResultSet.getString("url"), videoResultSet.getString("Title"), videoResultSet.getString("PostUser"));
    			searchResults.add(video);
    		}
        }
    	resultSet.close();
    	statement.close();         
        disconnect();
    	return searchResults;
    }
    public List<Comedian> getAllComediansNotInFavorite(String givenUsername) throws SQLException{
    	List<Comedian> allComedians = new ArrayList<Comedian>();
    	connect_func();
    	String sql = "SELECT * FROM comedians WHERE comid NOT IN (SELECT comid FROM isfavorite WHERE Username='" + givenUsername +"')";
    	statement =  (Statement) connect.createStatement();
        ResultSet comedianResultSet = statement.executeQuery(sql);
        while(comedianResultSet.next()) {
        	allComedians.add(new Comedian(Integer.parseInt(comedianResultSet.getString("comid")), comedianResultSet.getString("FirstName"), comedianResultSet.getString("LastName"), 
        			comedianResultSet.getString("Birthday"), comedianResultSet.getString("BirthPlace")));
        }
        comedianResultSet.close();
        statement.close();
        disconnect();
    	return allComedians;
    }
    
    public List<Comedian> getFavoriteList(String givenUsername) throws SQLException{
    	List<Comedian> favoriteList = new ArrayList<Comedian>(); 
    	connect_func();
    	
    	String sql = "SELECT * FROM isfavorite WHERE Username='" + givenUsername + "'";
    	statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        List<String> comids = new ArrayList<String>(); 
        while(resultSet.next()) {
        	comids.add(resultSet.getString("comid"));
        }
        
        for(int i = 0; i < comids.size();i++) {
        	String sql2 = "SELECT * FROM comedians WHERE comid='" + comids.get(i) + "'";
        	ResultSet comedianResultSet = statement.executeQuery(sql2);
        	
        	comedianResultSet.next(); 
    		favoriteList.add(new Comedian(Integer.parseInt(comedianResultSet.getString("comid")), comedianResultSet.getString("FirstName"), comedianResultSet.getString("LastName"), 
    			comedianResultSet.getString("Birthday"), comedianResultSet.getString("BirthPlace")));
        }
        resultSet.close();
    	statement.close();
        disconnect();        
        return favoriteList;
    }
    
    public void deleteFromFavorite(String username, String comid) throws SQLException{
    	
    	String sql = "DELETE FROM isfavorite WHERE Username='" + username + "' AND comid='" + comid + "'";
    	
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.executeUpdate();
    	
    	preparedStatement.close();
    	disconnect();
    }
    
    public void addToFavorite(String username, String comid) throws SQLException{
    	
    	String sql = "INSERT INTO isfavorite (Username, comid) VALUES ('" + username + "', '" + comid + "')";
    	
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.executeUpdate();
        
        preparedStatement.close();
    	disconnect();
    }
    // add review into the database
    public void insertReview(String userName, String remark, String rating, String url) throws SQLException{
    	connect_func();
    	
    	// This line of code is to insert review into reviews table
    	String insert = "INSERT INTO reviews(Remark, Rating, Author, Youtubeid) "
    			+ "VALUES (?, ?, ?, ?)";

    	preparedStatement = (PreparedStatement) connect.prepareStatement(insert);
    	preparedStatement.setString(1, remark);
    	preparedStatement.setString(2, rating);
    	preparedStatement.setString(3, userName);
    	preparedStatement.setString(4, url);
    	preparedStatement.executeUpdate();
    	preparedStatement.close();
    	disconnect();

    }
    // function to get all the reviews
    public List<Review> getAllReviews(String url) throws SQLException{
    	List<Review> allReviews = new ArrayList<Review>();
    	
    	connect_func();
    	
    	String sql = "SELECT * FROM reviews WHERE Youtubeid='" + url + "'";
    	statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next()) {
        	allReviews.add(new Review(resultSet.getString("Author"), resultSet.getString("Remark")));
        }
        
        resultSet.close();
        statement.close();
        disconnect();
        return allReviews;
    }
    // This function is to check if user posted a review for video return true if did and false if did not
    public boolean getHasReview(String url, User user) throws SQLException{
    	connect_func();
    	
    	String sql="SELECT * FROM reviews WHERE Author='" + user.username +"' AND Youtubeid='" + url + "'";
    	statement = (Statement)connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	if(resultSet.next()) {
    		resultSet.close();
    		statement.close();
    		disconnect();
    		return true;
    	}else {
    		resultSet.close();
    		statement.close();
    		disconnect();
    		return false;
    	}
    	
    }
    
    public void videoAddToFavorite(String username, String url) throws SQLException{
    	connect_func();
    	
    	String sql= "SELECT comid FROM youtubevideos WHERE url='" + url + "'";
    	statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
    	resultSet.next();
    	
    	String sql2 = "INSERT INTO isfavorite (Username, comid) VALUES ('" + username + "', '" + resultSet.getString("comid") + "')";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
        preparedStatement.executeUpdate();
        
        preparedStatement.close();
        resultSet.close();
        statement.close();
        disconnect();
    }
    // Function to get the YouTube video based on the url in video page
    public YoutubeVideo getVideo(String url) throws SQLException {
    	connect_func();
    	
    	String sql= "SELECT * FROM youtubevideos WHERE url='" + url + "'";
    	statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
    	resultSet.next();
    	
        YoutubeVideo videoData = new YoutubeVideo(resultSet.getString("url"), resultSet.getString("Title"), resultSet.getString("VideoDescription"),
        		Integer.parseInt(resultSet.getString("comid")), resultSet.getString("PostUser"), resultSet.getDate("PostDate"));
    	
        resultSet.close();
        statement.close();
        disconnect();
        
        return videoData;
    }
    
    public boolean isFavorite(String username, String comid) throws SQLException{
    	connect_func();
    	
    	String sql = "SELECT * FROM isfavorite WHERE Username='" + username + "' AND comid='" + comid + "'";
    	statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

    	if(resultSet.next()) {
    		resultSet.close();
            statement.close();
            disconnect();
    		return true;
    	}else {
    		resultSet.close();
            statement.close();
            disconnect();
            return false;
    	}
    }
    // Function to get all the comedians when uploading a video to choose which comedian video is for
    public List<Comedian> getAllComedians() throws SQLException{
    	//Create a list of type Comedian class to hold all the comedians in each index 
    	List<Comedian> comedians = new ArrayList<Comedian>();
    	connect_func();
    	
    	String sql = "SELECT * FROM comedians";
    	statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

    	while(resultSet.next()) {
    		comedians.add(new Comedian(Integer.parseInt(resultSet.getString("comid")), resultSet.getString("FirstName"), resultSet.getString("LastName"), 
    				resultSet.getString("Birthday"), resultSet.getString("BirthPlace")));
    	}
        resultSet.close();
    	statement.close();
    	disconnect();
    	return comedians;
    }
    
    public void insertComedian(Comedian newComedian) throws SQLException{
    	connect_func();
    	
    	String insert = "INSERT INTO comedians(comid, FirstName, LastName, Birthday, BirthPlace) "
    			+ "VALUES (?, ?, ?, ?, ?)";

    	preparedStatement = (PreparedStatement) connect.prepareStatement(insert);
    	preparedStatement.setString(1, Integer.toString(newComedian.comid));
    	preparedStatement.setString(2, newComedian.firstName);
    	preparedStatement.setString(3, newComedian.lastName);
    	preparedStatement.setString(4, newComedian.birthday);
    	preparedStatement.setString(5, newComedian.birthPlace);
    	preparedStatement.executeUpdate();
    	preparedStatement.close();
    	disconnect();
    }
    
    public List<ComedianInfo> getVideoCount() throws SQLException{
    	connect_func();
    	
    	List<ComedianInfo> comedianData = new ArrayList<ComedianInfo>();
    	
    	String sql = "SELECT comid, COUNT(comid) FROM youtubevideos GROUP BY comid ORDER BY COUNT(comid) DESC";
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.next();
    	String sql2 = "SELECT * FROM comedians WHERE comid='" + resultSet.getString(1) + "'";
    	Statement statement2 = (Statement) connect.createStatement();
    	ResultSet resultSet2 = statement2.executeQuery(sql2);
    	
    	resultSet2.next();
    	comedianData.add(new ComedianInfo(new Comedian(resultSet2.getString(2), resultSet2.getString(3)), Integer.parseInt(resultSet.getString(2))));
    	
    	while(resultSet.next()) {
    		if(Integer.parseInt(resultSet.getString(2)) == comedianData.get(0).getVideoCount()) {
    			String sql3 = "SELECT * FROM comedians WHERE comid='" + resultSet.getString(1) + "'";
    	    	Statement statement3 = (Statement) connect.createStatement();
    	    	ResultSet resultSet3 = statement3.executeQuery(sql3);
    	    	resultSet3.next();
    	    	comedianData.add(new ComedianInfo(new Comedian(resultSet3.getString(2), resultSet3.getString(3)), Integer.parseInt(resultSet.getString(2))));
    		}
    	}
    	
    	statement.close();
    	statement2.close();
    	resultSet.close();
    	resultSet2.close();
    	disconnect();
    	return comedianData;
    }
    
    public List<String> getCommonFavorites(String user1, String user2) throws SQLException{
    	connect_func();
    	List<String> commonFavorites = new ArrayList<String>();
    	List<Comedian> user1FavoriteList = this.getFavoriteList(user1);
    	List<Comedian> user2FavoriteList = this.getFavoriteList(user2);
    	
    	for(int i = 0; i < user1FavoriteList.size(); i++) {
    		for(int j = 0; j < user2FavoriteList.size(); j++) {
    			if(user2FavoriteList.get(j).getComid() == user1FavoriteList.get(i).getComid()) {
    				commonFavorites.add(user1FavoriteList.get(i).getFirstName() + " " + user1FavoriteList.get(i).getLastName());
    				break;
    			}
    		}
    	}
    	
    	if(commonFavorites.size() == 0) {
    		if(user1 == null && user2 == null) {
    			commonFavorites.add("Users not selected");
    		}else {
    		commonFavorites.add("No common favorite comedians between " + user1 + " and " + user2);
    		}
    	}
    	
    	disconnect();
    	return commonFavorites;
    }
    
    public List<String> getPostiveReviewers() throws SQLException{
    	connect_func();
    	List<String> users = new ArrayList<String>();
    	
    	String sql = "SELECT DISTINCT Author FROM reviews WHERE Rating='G' OR Rating='E'";
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	while(resultSet.next()) {
    		users.add(resultSet.getString(1));
    	}
    	
    	if(users.size() == 0) {
    		users.add("No positive reviewers");
    	}
    	statement.close();
    	resultSet.close();
    	disconnect();
    	return users;
    }
    
    public List<String> getPoorVideos() throws SQLException{
    	connect_func();
    	List<String> videos = new ArrayList<String>();
    	
    	String sql = "SELECT Title FROM youtubevideos YT INNER JOIN reviews R ON YT.url=R.Youtubeid WHERE R.Rating='P'";
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	while(resultSet.next()) {
    		videos.add(resultSet.getString(1));
    	}
    	
    	if(videos.size() == 0) {
    		videos.add("No poor videos");
    	}
    	statement.close();
    	resultSet.close();
    	disconnect();
    	return videos;
    }
    
    public List<MultipleUsers> getSameFavorites() throws SQLException{
    	connect_func();
    	List<MultipleUsers> users = new ArrayList<MultipleUsers>();
    	
    	String sql = "SELECT DISTINCT a.Username, b.Username FROM isfavorite AS a, isfavorite AS b WHERE a.comid=b.comid AND a.Username!=b.Username";
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    
    	List<MultipleUsers> tempUsers = new ArrayList<MultipleUsers>();
    	
    	while(resultSet.next()) {
    		tempUsers.add(new MultipleUsers(resultSet.getString(1), resultSet.getString(2)));
    	}
    	
    	for(int i = 0; i < tempUsers.size(); i++) {

			List<Comedian> user1List = this.getFavoriteList(tempUsers.get(i).getUserOne());
			List<Comedian> user2List = this.getFavoriteList(tempUsers.get(i).getUserTwo());
			if(user1List.size() == user2List.size()) {
				if(users.size() == 0) {
					users.add(tempUsers.get(i));
				}else {
					for(int j = 0; j < users.size(); j++) {
						if(users.get(j).getUserTwo().equals(tempUsers.get(i).getUserOne()) && users.get(j).getUserOne().equals(tempUsers.get(i).getUserTwo())) {
							break;
						}else {
							users.add(tempUsers.get(i));
							break;
						}
					}
				}
			}else {
				continue;
			}
    	}
    	
    	statement.close();
    	resultSet.close();
    	disconnect();
    	return users;
    }
 // Function to get all the comedians videos that were posted today
    public List<Comedian> postedToday() throws SQLException{
    	
    	// This line of code is to get the current date and convert object to string 
    	long millis=System.currentTimeMillis();  
    	java.sql.Date date=new java.sql.Date(millis);  
    	String dateString=date.toString(); 
    	
    	//Create a list of type Comedian class to hold all the comedians in each index 
    	List<Comedian> comedians = new ArrayList<Comedian>();
    	connect_func();
    	
    	String sql = "SELECT * FROM comedians C WHERE C.comid NOT IN (SELECT Y.comid FROM youtubevideos Y WHERE PostDate != '"+ dateString + "')";
    	statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
    	while(resultSet.next()) {
    		comedians.add(new Comedian(Integer.parseInt(resultSet.getString("comid")), resultSet.getString("FirstName"), resultSet.getString("LastName"), 
    				resultSet.getString("Birthday"), resultSet.getString("BirthPlace")));
    	}
        resultSet.close();
    	statement.close();
    	disconnect();
    	return comedians;
    }
    
 // Function to get top 3 comedians that were reviewed the most
    public List<Comedian> topReview() throws SQLException{
    	
    	
    	//Create a list of type Comedian class to hold all the comedians in each index 
    	List<Comedian> comedians = new ArrayList<Comedian>();
    	connect_func();
    	
    	String sql = "SELECT *\r\n" + 
    			"FROM comedians K,\r\n" + 
    			"	(SELECT C.comid, COUNT(*) as numReviews\r\n" + 
    			"	FROM reviews R, youtubevideos Y, comedians C\r\n" + 
    			"	WHERE R.Youtubeid = Y.url AND Y.comid = C.comid\r\n" + 
    			"	GROUP BY C.comid\r\n" + 
    			"	ORDER BY numReviews DESC) AS comedianReviews\r\n" + 
    			"WHERE K.comid = comedianReviews.comid \r\n" + 
    			"ORDER BY numReviews DESC\r\n" + 
    			"LIMIT 3;";
    	statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
    	while(resultSet.next()) {
    		comedians.add(new Comedian(Integer.parseInt(resultSet.getString("comid")), resultSet.getString("FirstName"), resultSet.getString("LastName"), 
    				resultSet.getString("Birthday"), resultSet.getString("BirthPlace")));
    	}
        resultSet.close();
    	statement.close();
    	disconnect();
    	return comedians;
    }

// function to get the tags used by every user 
public ArrayList<String> topTags() throws SQLException{
		connect_func();
		
    	// variable to hold MYSQL users tag
    	List<String[]> tags = new ArrayList<String[]>();
    	
    	// variable to hold , and " " separated tags
    	String[] arrOfTags;
    	
    	
    	String sql = "SELECT GROUP_CONCAT(T.Tag) as \"Tags\"\r\n" + 
    			"FROM youtubetags T, youtubevideos V, user U\r\n" + 
    			"WHERE T.url = V.url AND V.PostUser = U.Username\r\n" + 
    			"GROUP BY U.Username\r\n" + 
    			"ORDER BY U.Username;";
    	statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
    	while(resultSet.next()) {
    		
    		String listOfTags = resultSet.getString("Tags");
            arrOfTags = listOfTags.split("[ ,]+", -2); 
    		tags.add(arrOfTags); // each tag element is an [array] of tags

    	}
    	// create hash map that holds all elements in our first users tags
        Set<String> tagChecker = new HashSet<>(Arrays.asList(tags.get(0)));
        // Initialize variable to hold matching tags
        ArrayList<String> matchingElements=new ArrayList<String>();
        for(int j = 1; j < tags.size(); j++) {
        	
        	// clear the matchingElements array, first loop this code is redundant.
        	matchingElements.clear();
	        for (String str : tags.get(j)) {
	            if (tagChecker.contains(str)) {
	            	// store matching str in a matchingElements
	            	matchingElements.add(str); 
	            }
	          
	        }
	        // reached the end of the inner for loop so we clear our hash table and put 
	        // in only those tags that were the same for user 1 and user 2 
	        // then we check the same tags that both user 1 and 2 had with the next user
	        // clear and put the ones that are same with user 1, 2, and 3, and do it
	        // until all users have been iterated through (tags.get(i))
	        tagChecker.clear();
	        tagChecker.addAll(matchingElements); 
	        
            	
        }
        resultSet.close();
    	statement.close();
    	disconnect();
    	return matchingElements;
    }

//Function to get comedians whose reviews are ALL excellent
public List<Comedian> excellentReview() throws SQLException{
	connect_func();
	
	List<Comedian> comedians = new ArrayList<Comedian>();
	
	
	String sql = "SELECT *\r\n" + 
			"FROM comedians C,\r\n" + 
			"(\r\n" + 
			"	SELECT C.FirstName, Group_concat(R.Rating) as Rating\r\n" + 
			"	FROM reviews R, youtubevideos Y, comedians C\r\n" + 
			"	WHERE R.Youtubeid = Y.url AND Y.comid = C.comid\r\n" + 
			"	GROUP BY C.FirstName) as excellent\r\n" + 
			"WHERE excellent.Rating NOT LIKE '%P%' AND excellent.Rating NOT LIKE '%F%' AND excellent.Rating NOT LIKE '%G%' \r\n" + 
			"AND C.FirstName = excellent.FirstName;";
	statement =  (Statement) connect.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);
	while(resultSet.next()) {
		comedians.add(new Comedian(Integer.parseInt(resultSet.getString("comid")), resultSet.getString("FirstName"), resultSet.getString("LastName"), 
				resultSet.getString("Birthday"), resultSet.getString("BirthPlace")));
	}
    resultSet.close();
	statement.close();
	disconnect();
	return comedians;
	}


public List<YoutubeVideo>excellentComediansVideos(String comidId) throws SQLException{
	connect_func();
	
	List<YoutubeVideo> videoData = new ArrayList<YoutubeVideo>();
	String URL;
	String endOfUrl; 
	int i = 0; 
	String sql = "SELECT * FROM comedians C, youtubevideos Y WHERE C.comid = Y.comid AND Y.comid ='"+ comidId + "'"; 
	
	

	statement =  (Statement) connect.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);
	while(resultSet.next()) {
		videoData.add(new YoutubeVideo(resultSet.getString("url"), resultSet.getString("Title"), resultSet.getString("VideoDescription"),
				Integer.parseInt(resultSet.getString("comid")), resultSet.getString("PostUser"), resultSet.getDate("PostDate")));
		URL = videoData.get(i).getUrl();
		endOfUrl = URL.split("=")[1];
		videoData.get(i).setUrl(endOfUrl);
		i++;
	}
    resultSet.close();
    statement.close();
    disconnect();
    
    return videoData;
	
	}
public List<User> topPost() throws SQLException{
	connect_func();
	
	List<User> topUsers = new ArrayList<User>();
	
	
	String sql = "SELECT *\r\n" + 
			"FROM\r\n" + 
			"(\r\n" + 
			"SELECT totalUploads.Username, totalUploads.numOfVideos\r\n" + 
			"FROM\r\n" + 
			"	(\r\n" + 
			"	SELECT U.Username, COUNT(*) AS numOfVideos \r\n" + 
			"	FROM user U, youtubevideos Y \r\n" + 
			"	WHERE U.Username = Y.PostUser\r\n" + 
			"	GROUP BY U.Username\r\n" + 
			"	ORDER BY numOfVideos DESC) as totalUploads\r\n" + 
			"WHERE totalUploads.numOfVideos = (SELECT MAX(numVideos)\r\n" + 
			"FROM\r\n" + 
			"	(\r\n" + 
			"	SELECT COUNT(U.Username) as numVideos\r\n" + 
			"	FROM user U, youtubevideos Y \r\n" + 
			"	WHERE U.Username = Y.PostUser\r\n" + 
			"	GROUP BY U.Username) as temp)) as temp2, user P\r\n" + 
			"WHERE P.Username = temp2.Username;";
	statement =  (Statement) connect.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);
	while(resultSet.next()) {
		topUsers.add(new User(resultSet.getString("Username"), resultSet.getString("Password"), resultSet.getString("FirstName"), 
				resultSet.getString("LastName"), Integer.parseInt(resultSet.getString("Age"))));
	}
	
    resultSet.close();
	statement.close();
	disconnect();
	return topUsers;
	}

	public List<YoutubeVideo>userVideos(String userName) throws SQLException{
		connect_func();
		
		List<YoutubeVideo> videoData = new ArrayList<YoutubeVideo>();
		String URL;
		String endOfUrl; 
		int i = 0; 
		String sql = "SELECT * FROM user U, youtubevideos Y WHERE U.Username = Y.PostUser AND Y.PostUser ='"+ userName + "'";
		
		statement =  (Statement) connect.createStatement();
	    ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
			videoData.add(new YoutubeVideo(resultSet.getString("url"), resultSet.getString("Title"), resultSet.getString("VideoDescription"),
					Integer.parseInt(resultSet.getString("comid")), resultSet.getString("PostUser"), resultSet.getDate("PostDate")));
			URL = videoData.get(i).getUrl();
			endOfUrl = URL.split("=")[1];
			videoData.get(i).setUrl(endOfUrl);
			i++;
		}
	    resultSet.close();
	    statement.close();
	    disconnect();
	    
	    return videoData;
	
	}

}