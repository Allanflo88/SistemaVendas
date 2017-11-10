package managedbean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class UFMB {
	private static String[] uf = new String[]{"AC","AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT","PA","PB","PE","PI","PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO"};
	
	public String[] getListaUF (){
		return uf;
	}
}
