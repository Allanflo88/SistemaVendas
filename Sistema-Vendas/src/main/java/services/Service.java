package services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Service {
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaVendas");
}
