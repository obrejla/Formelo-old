
package cz.brejla.pjj.web.graph.presenters;

import cz.brejla.java.web.formelo.application.ICallback;
import cz.brejla.java.web.formelo.application.IComponent;
import cz.brejla.java.web.formelo.forms.Form;
import cz.brejla.java.web.formelo.forms.SelectInput;
import cz.brejla.java.web.formelo.forms.SubmitButton;
import cz.brejla.java.web.formelo.forms.TextareaInput;
import cz.brejla.pjj.web.graph.presenters.models.AuthorsModel;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author warden
 */
public class BooksPresenter extends BasePresenter {

    private AuthorsModel authorsModel = new AuthorsModel();

    public Form createComponentSelectForm() {
        final Form form = new Form();
        final String[] queries = {"SELECT * FROM authors", "SELECT * FROM publishers"};

        Map<Integer, String> options = new HashMap<Integer, String>();
        options.put(0, "Všichni autoři");
        options.put(1, "Všichni vydavatelé");
        options.put(2, "Jeden autor...");
        options.put(3, "Vlastní SQL...");

        form.addInputElement(new SelectInput("action", "Akce", options));

        form.addElement(new SubmitButton("showTable", "Odeslat"));

        form.addCallbackOnSubmit(new ICallback() {

            public void call() {
                if (Integer.parseInt(form.getElement("action").getValue()) == 2) {
                    redirect("Books", "author");
                } else if (Integer.parseInt(form.getElement("action").getValue()) == 3) {
                    redirect("Books", "sql");
                }

                SqlTableComponent sqlTable = (SqlTableComponent) getComponent("SqlTable");

                sqlTable.setQuery(queries[Integer.parseInt(form.getElement("action").getValue())]);
                sqlTable.process();
            }

        });

        return form;
    }

    public SqlTableComponent createComponentSqlTable() {
        SqlTableComponent sqlTable = new SqlTableComponent();
        
        sqlTable.setQuery("SELECT * FROM authors");
        sqlTable.process();

        return sqlTable;
    }

    public void renderShow() {
        IComponent form = getComponent("SelectForm");
        setTemplateAttribute(form.getName(), form);
        
        SqlTableComponent table = (SqlTableComponent) getComponent("SqlTable");

        if (getSessionAttribute("sql") != null) {
            table.setQuery(getSessionAttribute("sql").toString());
            table.process();
            setSessionAttribute("sql", null);
        }

        setTemplateAttribute(table.getName(), table);
    }

    public Form createComponentSelectAuthor() {
        final Form form = new Form();

        form.addInputElement(new SelectInput("authorId", "Autor:", authorsModel.getAuthors()));

        form.addElement(new SubmitButton("authSelectSend", "Vybrat"));

        form.addCallbackOnSubmit(new ICallback() {

            public void call() {
                String sql = "SELECT CONCAT(firstName, ' ', lastName) AS Autor, title, copyright, isbn FROM authors JOIN authorISBN USING (authorID) JOIN titles USING (isbn) WHERE authorID = " + form.getElement("authorId").getValue() + " ORDER BY title";
                setSessionAttribute("sql", sql);
                redirect("Books", "show");
            }

        });

        return form;
    }

    public void renderAuthor() {
        IComponent form = getComponent("SelectAuthor");
        setTemplateAttribute(form.getName(), form);
    }
    
    public Form createComponentSqlForm() {
        final Form form = new Form();
        
        form.addInputElement(new TextareaInput("sqlInput", "Sql:"));
        form.addElement(new SubmitButton("sqlSend", "Vykonat"));

        form.addCallbackOnSubmit(new ICallback() {

            public void call() {
                if (!form.getElement("sqlInput").getValue().trim().equals("")) {
                    setSessionAttribute("sql", form.getElement("sqlInput").getValue());
                }

                redirect("Books", "show");
            }

        });
        
        return form;
    }

    public void renderSql() {
        IComponent form = getComponent("SqlForm");
        setTemplateAttribute(form.getName(), form);
    }

}
