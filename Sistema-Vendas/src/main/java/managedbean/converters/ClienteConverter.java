package managedbean.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entity.Cliente;
import services.ClienteService;

@FacesConverter("converterCliente")
public class ClienteConverter implements Converter{
	
	private ClienteService service = new ClienteService();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && !value.isEmpty()) {
			for(Cliente c : service.getClientes()) {
				if(c.getNome().equals(value)) {
					return c;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cliente) {
		if(cliente == null || cliente.equals("")) {
			return null;
		}
		else {
			return ((Cliente) cliente).getNome();
		}
		
	}
	
	

}
