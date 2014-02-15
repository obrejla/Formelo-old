
package cz.brejla.pjj.web.graph.presenters;

import cz.brejla.java.web.formelo.application.*;
import cz.brejla.java.web.formelo.forms.*;
import cz.brejla.java.web.formelo.forms.rules.*;



/**
 *
 * @author warden
 */
public class GraphPresenter extends BasePresenter {

    /**
     * Creates Form component.
     *
     * @return
     */
    public Form createComponentNumbersForm() {
        final Form form = new Form();

        form.addInputElement(new TextInput("numbers", "Počet čísel:"))
                .addRule(new FilledRule("Počet čísel musí být zadán."))
                .addRule(new IntegerRule("Počet čísel musí být číslo."))
                .addRule(new GtRule("Počet čísel musí být větší než %d.", 0));
        form.addInputElement(new TextInput("width", "Šířka:"))
                .addRule(new FilledRule("Šířka musí být zadána."))
                .addRule(new IntegerRule("Šířka musí být číslo."))
                .addRule(new GtRule("Šířka musí být větší než %d.", 0));
        form.addInputElement(new TextInput("height", "Výška:"))
                .addRule(new FilledRule("Výška musí být zadána."))
                .addRule(new IntegerRule("Výška musí být číslo."))
                .addRule(new GtRule("Výška musí být větší než %d.", 0));

        form.addElement(new SubmitButton("sendNumbers", "Vygenerovat"));

        form.addCallbackOnSubmit(new ICallback() {

            public void call() {
                setSessionAttribute("numbers", form.getElement("numbers").getValue());
                setSessionAttribute("width", form.getElement("width").getValue());
                setSessionAttribute("height", form.getElement("height").getValue());

                redirect("Graph", "fill");
            }

        });

        return form;
    }

    /**
     * Creates Form component.
     *
     * @return
     */
    public Form createComponentFillForm() {
        final Form form = new Form();

        for (int i = 0; i < Integer.parseInt(getSessionAttribute("numbers").toString()); i++) {
            form.addInputElement(new TextInput("number" + i, "Číslo " + (i + 1) + " :"))
                    .addRule(new FilledRule("Číslo musí být zadáno."))
                    .addRule(new IntegerRule("Číslo musí být číslo."))
                    .addRule(new GtRule("Číslo musí být větší než %d.", 0));
        }

        form.addElement(new SubmitButton("sendFilledNumbers", "Vygenerovat"));

        form.addCallbackOnSubmit(new ICallback() {

            public void call() {
                for (int i = 0; i < Integer.parseInt(getSessionAttribute("numbers").toString()); i++) {
                    setSessionAttribute("number" + i, form.getElement("number" + i).getValue());
                }

                redirect("Graph", "show");
            }

        });

        return form;
    }

    /**
     * Sets variables, which will be shown in the template for insert action.
     */
    public void renderInsert() {
        IComponent form = getComponent("NumbersForm");
        
        setTemplateAttribute(form.getName(), form);
    }

    /**
     * Sets variables, which will be shown in the template for fill action.
     */
    public void renderFill() {
        IComponent form = getComponent("FillForm");

        setTemplateAttribute(form.getName(), form);
    }

    /**
     * Sets variables, which will be shown in the template for show action.
     */
    public void renderShow() {
        setTemplateAttribute("width", getSessionAttribute("width"));
        setTemplateAttribute("height", getSessionAttribute("height"));
    }

}
