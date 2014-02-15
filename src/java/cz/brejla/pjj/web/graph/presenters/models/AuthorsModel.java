
package cz.brejla.pjj.web.graph.presenters.models;

import cz.brejla.pjj.web.graph.presenters.ResultSetTableModel;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author warden
 */
public class AuthorsModel {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static final String DATABASE_URL = "jdbc:mysql://localhost/books";

    public static final String USERNAME = "root";

    public static final String PASSWORD = "";

    public static final String DEFAULT_QUERY = "SELECT authorID, CONCAT(firstName, ' ', lastName) FROM authors";

    private ResultSetTableModel model;

    public AuthorsModel() {
        try {
            this.model = new ResultSetTableModel(JDBC_DRIVER, DATABASE_URL, USERNAME, PASSWORD, DEFAULT_QUERY);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Map<Integer, String> getAuthors() {
        Map<Integer, String> authors = new HashMap<Integer, String>();

        for (int i = 0; i < model.getRowCount(); i++) {
            authors.put((Integer) model.getValueAt(i, 0), (String) model.getValueAt(i, 1));
        }

        return authors;
    }

}
