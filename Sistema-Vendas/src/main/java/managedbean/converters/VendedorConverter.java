package managedbean.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import entity.Vendedor;
import services.VendedorService;

@FacesConverter("converterVendedor")
public class VendedorConverter implements Converter {
	
	private VendedorService service = new VendedorService();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && !value.isEmpty()) {
			for(Vendedor v : service.getVendedores()) {
				if(v.getNome().equals(value)) {
					return v;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object vendedor) {
		if(vendedor == null || vendedor.equals("")) {
			return null;
		}
		else {
			return ((Vendedor) vendedor).getNome();
		}
	}

}
