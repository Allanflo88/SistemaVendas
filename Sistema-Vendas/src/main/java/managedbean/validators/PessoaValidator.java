package managedbean.validators;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import entity.Pessoa;

@ManagedBean
@SessionScoped
@FacesValidator("pessoaValidator")
public class PessoaValidator implements Validator {
	@Override
	public void validate(FacesContext fc, UIComponent uic, Object obj) throws ValidatorException {

		String cpf = (String) obj;
		
		Locale locale = fc.getViewRoot().getLocale();
		ResourceBundle rb = ResourceBundle.getBundle("application", locale);
		
		if (!Pessoa.cpfValido(cpf)) {
			FacesMessage msg = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					rb.getString("messages.error.CpfInvalido.title"),
					rb.getString("messages.error.CpfInvalido.detail")
					);
			throw new ValidatorException(msg);
		}

	}
}
