
package cz.brejla.pjj.web.graph.presenters;

import cz.brejla.java.web.formelo.application.FormeloApplication;
import cz.brejla.java.web.formelo.application.ICallback;
import cz.brejla.java.web.formelo.application.IComponent;
import cz.brejla.java.web.formelo.forms.Form;
import cz.brejla.java.web.formelo.forms.SelectInput;
import cz.brejla.java.web.formelo.forms.SubmitButton;
import cz.brejla.java.web.formelo.forms.TextInput;
import cz.brejla.java.web.formelo.forms.rules.DoubleRule;
import cz.brejla.java.web.formelo.forms.rules.FilledRule;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author warden
 */
public class TemperatureConversionPresenter extends BasePresenter {

    private String convertedTemperature = "";

    public Form createComponentConversionForm() {
        Map<Integer, String> options = new HashMap<Integer, String>();
        options.put(0, "Celsia");
        options.put(1, "Fahrenheit");
        options.put(2, "Kelvin");

        final Form form = new Form();
        form.addInputElement(new TextInput("temperature", "Teplota:"))
                .addRule(new FilledRule("Teplota musí být vyplněna."))
                .addRule(new DoubleRule("Teplota musí být platné desetinné číslo."));
        form.addInputElement(new SelectInput("oldTemperature", "Staré stupně:", options));
        form.addInputElement(new SelectInput("newTemperature", "Nové stupně:", options));

        form.addElement(new SubmitButton("makeConversion", "Konvertovat"));

        form.addCallbackOnSubmit(new ICallback() {

            public void call() {
                convertedTemperature = String.valueOf(convertTemperature(Double.parseDouble(form.getElement("temperature").getValue())
                        , Integer.parseInt(form.getElement("oldTemperature").getValue())
                        , Integer.parseInt(form.getElement("newTemperature").getValue())));
                System.out.println(form.getElement("oldTemperature").getValue());
                System.out.println(form.getElement("newTemperature").getValue());
            }

        });

        return form;
    }

    public void renderShow() {
        IComponent form = getComponent("ConversionForm");

        FormeloApplication.getRequest().setAttribute(form.getName(), form);
        FormeloApplication.getRequest().setAttribute("convertedTemperature", convertedTemperature);
    }

    private Double convertTemperature(double temp, int oldTemp, int newTemp) {
        if (oldTemp == newTemp) {
            return temp;
        }

        switch (oldTemp) {
            case 0: switch (newTemp) {
                case 1: return ((9. / 5) * temp + 32);
                case 2: return (temp + 273.15);
            };
            case 1: switch (newTemp) {
                case 0: return ((5. / 9) * (temp - 32));
                case 2: return convertTemperature(convertTemperature(temp, oldTemp, 0), 0, 2);
            };
            case 2: switch (newTemp) {
                case 0: return (273.15 - temp);
                case 1: return convertTemperature(convertTemperature(temp, oldTemp, 0), 0, 1);
            };
        }

        return 0.;
    }

}
