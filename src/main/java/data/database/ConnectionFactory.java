package data.database;
import java.sql.*;

public class ConnectionFactory implements AutoCloseable{
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static Statement statement;

    public static Connection createConnection() throws SQLException {
        if(connection == null)
            connection = DriverManager.getConnection("jdbc:sqlite:estacionamento.db");
        return connection;
    }

    public static PreparedStatement createPreparedStatement(String sql) throws SQLException {
        return preparedStatement = createConnection().prepareStatement(sql);
    }

    public static Statement createStatement() throws SQLException {
        return statement = createConnection().createStatement();
    }

    public void closeConnection() throws SQLException{
        if(connection!= null)
            connection.close();
    }

    @Override
    public void close() throws Exception {
        if(preparedStatement!=null)
            preparedStatement.close();
        if(statement!=null)
            statement.close();
    }
}
