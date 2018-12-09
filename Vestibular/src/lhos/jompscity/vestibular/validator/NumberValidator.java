package lhos.jompscity.vestibular.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("numberValidator")
public class NumberValidator {

	// validando 
	public void numberValidator(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Integer valor = Integer.parseInt(value.toString());
		
		System.out.println("## => " + valor);
		
		if (valor < 1) {
			throw new ValidatorException(new FacesMessage("O valor deve ser um inteiro positivo"));
		}
	}
}
