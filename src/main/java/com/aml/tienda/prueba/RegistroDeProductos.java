package com.aml.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.aml.tienda.modelo.Categoria;
import com.aml.tienda.modelo.Producto;
import com.aml.tienda.utils.JPAUtils;
import com.aml.tienda.dao.ProductoDao;
import com.aml.tienda.dao.CategoriaDao;

public class RegistroDeProductos {
	


	public static void main(String[] args) {
		
		registrarProducto();
		EntityManager em = JPAUtils.getEntityManager();
		ProductoDao productoDao = new ProductoDao(em);
		Producto producto = productoDao.consultarById(1L);
		System.out.println(producto.getNombre());
		
		List<Producto> productos = productoDao.consultarTodos();
		productos.forEach(prod -> System.out.println(prod.getDescripcion()));
		
		List<Producto> productosN = productoDao.consultarByNombre("Samsung");
		productosN.forEach(prod -> System.out.println(prod.getDescripcion()));
		
		List<Producto> productoNC = productoDao.consultarByNombreCategoria("CELULARES");
		productoNC.forEach(prod-> System.out.println(prod.getNombre()));
		
		BigDecimal precio = productoDao.consultarPrecioByNombreProducto("Samsung");
		System.out.println(precio);
	}

	private static void registrarProducto() {
		Categoria celulares = new Categoria("CELULARES");
		Producto celular = new Producto("Samsung", "Telefono nuevo", new BigDecimal("1000"), celulares);

		EntityManager em = JPAUtils.getEntityManager();
		
		ProductoDao productoDao = new ProductoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		productoDao.guardar(celular);
		categoriaDao.guardar(celulares);
		em.persist(celular);
		em.persist(celulares);
		em.getTransaction().commit();
		em.close();
	}

}
