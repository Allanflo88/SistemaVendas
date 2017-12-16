package managedbean.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converterData")
public class DateConverter implements Converter{
	private SimpleDateFormat format;
	private SimpleDateFormat parse;
	
	public DateConverter(){
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceBundle rb = ResourceBundle.getBundle("application", fc.getViewRoot().getLocale());
		format = new SimpleDateFormat(rb.getString("config.datelocale.pattern.date"));
		parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss zz yyyy", fc.getViewRoot().getLocale());
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Date date = null;
		if(value != null && !value.isEmpty()) {
			try {
				date = parse.parse(value);
			} catch (ParseException ex){
				System.out.println("1 " + ex.getMessage());
			}
			if (date == null){
				try {
					date = format.parse(value);
				} catch (ParseException ex){
					System.out.println("2 " + ex.getMessage());
				}
			}
		}
		return date;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		String value = "";
		Date date = null;
		try {
			date = parse.parse((String)obj);
		} catch (ParseException ex){
			System.out.println("3 " + ex.getMessage());
		}
		if (date == null){
			try {
				date = format.parse(value);
			} catch (ParseException ex){
				System.out.println("4 " + ex.getMessage());
			}
		}
		if (date != null){
			value = format.format(date);
		}
		return value;
	}
	
	

}
