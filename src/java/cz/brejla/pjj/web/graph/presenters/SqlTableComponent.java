
package cz.brejla.pjj.web.graph.presenters;

import cz.brejla.java.web.formelo.application.Component;
import java.sql.SQLException;

/**
 *
 * @author warden
 */
public class SqlTableComponent extends Component {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static final String DATABASE_URL = "jdbc:mysql://localhost/books";

    public static final String USERNAME = "root";

    public static final String PASSWORD = "";

    private String query;

    private ResultSetTableModel model;

    public void setQuery(String query) {
        this.query = query;
    }

    public void process() {
        if (query == null) {
            throw new IllegalStateException("Sql query can not be set to 'null'.");
        }

        try {
            model = new ResultSetTableModel(JDBC_DRIVER, DATABASE_URL, USERNAME, PASSWORD, query);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String output = "";

        output += "<table>\n";
        output += "<tr>";

            for (int i = 0; i < model.getColumnCount(); i++) {
                output += "<th>" + model.getColumnName(i) + "</th>";
            }

        output += "</tr>";

        for (int j = 0; j < model.getRowCount(); j++) {
            output += "<tr>\n";

            for (int k = 0; k < model.getColumnCount(); k++) {
                output += "<td>" + model.getValueAt(j, k) + "</td>\n";
            }

            output += "</tr>\n";
        }

        output += "</table>\n";

        return output;
    }

}
