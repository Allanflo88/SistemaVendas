package managedbean.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converterData")
public class DateConverter implements Converter{
	private SimpleDateFormat sdf;
	
	public DateConverter(){
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
		sdf = new SimpleDateFormat(rb.getString("config.datelocale.pattern.date"));
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Date date = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
		if(value != null && !value.isEmpty()) {
			try {
				date = sdf.parse(value);
			} catch (ParseException ex){
				fc.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_WARN,
						rb.getString("messages.error.DataInvalida.title"),
						"\"" + value + "\" " + rb.getString("messages.error.DataInvalida.detail")
				));
			}
		}
		return date;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object data) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
		String date = "";
		try {
			date = sdf.format((Date)data);
		} catch (Exception ex){
			System.out.println(ex.getMessage());
			if (data != null){
				date = data.toString();
			}
			fc.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN,
					rb.getString("messages.error.DataInvalida.title"),
					"\"" + date + "\" " + rb.getString("messages.error.DataInvalida.detail")
			));
			date = "";
		}
		return date;
	}
	
	

}
