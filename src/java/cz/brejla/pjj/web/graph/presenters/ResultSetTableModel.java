package cz.brejla.pjj.web.graph.presenters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.AbstractTableModel;

/**
 * Model pro komponentu JTable zapouzd�uj�c� v�sledek SQL dotazu
 * ResultSet a umo��uj�c� na��st v�sledky z SQL datab�ze pro ur�it�
 * dotaz.
 */
public class ResultSetTableModel extends AbstractTableModel {

	/**
	 * Spojen� s datab�z�.
	 */
	private Connection connection;

	/**
	 * Statement pro prov�d�n� SQL dotaz�.
	 */
	private Statement statement;

	/**
	 * V�sledek dotazu.
	 */
	private ResultSet resultSet;

	/**
	 * Meta data v�sledku dotazu.
	 */
	private ResultSetMetaData metaData;

	/**
	 * Po�et ��dk� v�sledku.
	 */
	private int numberOfRows;

	/**
	 * Stav p�ipojen� k datab�zi.
	 */
	private boolean connectedToDatabase = false;

        private String query;

	/**
	 * Vytvo�� novou instanci t��dy ResultSetTableModel.
	 * 
	 * @param driver
	 *            pln� kvalifikovan� jm�no pou�it�ho JDBC ovlada�e.
	 * @param url
	 *            url p�ipojen� k datab�zi.
	 * @param username
	 *            u�ivatelsk� jm�no pou�it� pro p��stup k datab�zi.
	 * @param password
	 *            heslo pou�it� pro p��stup k datab�zi.
	 * @param query
	 *            SQL dotaz, jeho� v�sledek bude tento model
	 *            obsahovat.
	 * @throws ClassNotFoundException
	 *             pokud nebude JDBC ovlada� driver um�st�n� v
	 *             classpath.
	 * @throws SQLException
	 *             pokud dojde p�i zpracov�n� SQL dotazu k chyb�.
	 */
	public ResultSetTableModel(String driver, String url, String username, String password, String query) throws ClassNotFoundException, SQLException {
		// na�ten� ovlada�e datab�ze
		Class.forName(driver);

		// p�ipojen� k datab�zi
		connection = DriverManager.getConnection(url, username, password);

		// vytvo�en� objektu Statement pro prov�d�n� dotaz�
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		// aktualizace stavu p�ipojen�
		connectedToDatabase = true;

		// nastaven� a proveden� dotazu
		setQuery(query);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		if (!connectedToDatabase) {
			throw new IllegalStateException("Nepripojeno k databazi.");
		}

		try {
			String className = metaData.getColumnClassName(column + 1);
			return Class.forName(className);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// pokud dojde k probl�m�m, p�edpokl�d�me t��du Object
		return Object.class;

	}

	@Override
	public int getRowCount() {
		if (!connectedToDatabase) {
			throw new IllegalStateException("Nepripojeno k databazi.");
		}

		return numberOfRows;
	}

	@Override
	public int getColumnCount() {
		if (!connectedToDatabase) {
			throw new IllegalStateException("Nepripojeno k databazi.");
		}

		try {
			return metaData.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// pokud dojde k probl�m�m, vr�t�me 0
		return 0;
	}

	@Override
	public String getColumnName(int column) {
		if (!connectedToDatabase) {
			throw new IllegalStateException("Nepripojeno k databazi.");
		}

		try {
			// sloupce jsou ��slov�ny od 0 v JTable,
			// ale od 1 v ResultSetu
			return metaData.getColumnName(column + 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// pokud dojde k probl�m�m, vr�t�me pr�zdn� �et�zec
		return "";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (!connectedToDatabase) {
			throw new IllegalStateException("Nepripojeno k databazi.");
		}

		try {
			// nastaven� ��dku
			// ��dky jsou ��slov�ny od 0 v JTable,
			// ale od 1 v ResultSetu
			resultSet.absolute(rowIndex + 1);
			// vr�cen� hodnoty v dan�m sloupci
			// sloupce jsou ��slov�ny od 0 v JTable,
			// ale od 1 v ResultSetu
			return resultSet.getObject(columnIndex + 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// pokud dojde k probl�m�m, vr�t�me pr�zdn� �et�zec
		return "";
	}

	/**
	 * Nastav� v�sledek obsa�en� v tomto modelu podle zadan�ho
	 * dotazu.
	 * 
	 * @param query
	 *            dotaz, jeho� v�sledek bude obsa�en v tomto modelu.
	 * @throws SQLException
	 *             pokud p�i zpracov�n� dotazu dojde k chyb�.
	 */
	public void setQuery(String query) throws SQLException {
		if (!connectedToDatabase) {
			throw new IllegalStateException("Nepripojeno k databazi.");
		}

                this.query = query;

		// proveden� dotazu
		resultSet = statement.executeQuery(query);

		// z�sk�n� meta dat
		metaData = resultSet.getMetaData();

		// ur�en� po�tu ��dk� v ResultSetu
		resultSet.last();
		numberOfRows = resultSet.getRow();

		// upozorn�n� JTable, �e se model zm�nil
		fireTableStructureChanged();
	}

        public void setUpdateQuery(String updateQuery) throws SQLException {
            if (!connectedToDatabase) {
                throw new IllegalStateException("Nepripojeno k databazi.");
            }

            // proveden� dotazu
            statement.executeUpdate(updateQuery);
            
            this.setQuery(this.query);
        }

	/**
	 * Provede odpojen� od datab�ze a uvoln�n� prost�edk�.
	 */
	public void disconnectFromDatabase() {
		if (!connectedToDatabase) {
			return;
		}

		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectedToDatabase = false;
		}
	}
}
