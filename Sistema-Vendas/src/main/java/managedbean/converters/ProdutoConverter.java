package managedbean.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import entity.Produto;
import services.ProdutoService;

@FacesConverter("converterProduto")
public class ProdutoConverter implements Converter{
	
	private ProdutoService service = new ProdutoService();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && !value.isEmpty()) {
			for(Produto p : service.getProdutos()) {
				if(p.getDescricao().equals(value)) {
					return p;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object produto) {
		if(produto == null || produto.equals("")) {
			return null;
		}
		else {
			return ((Produto) produto).getDescricao();
		}
	}

}
