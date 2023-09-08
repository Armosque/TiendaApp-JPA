package com.aml.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.aml.tienda.modelo.Producto;



public class ProductoDao {
	
	private EntityManager em;
	public ProductoDao(EntityManager em) {
		this.em = em;
	}
	public void guardar(Producto producto) {
		this.em.persist(producto);
		
	}
	public void actualizar(Producto producto) {
		this.em.merge(producto);
	}
	public void remover(Producto producto) {
		producto = this.em.merge(producto);
		this.em.remove(producto);
	}
	public Producto consultarById(Long id) {
		return em.find(Producto.class, id);
	}
	public List<Producto> consultarTodos(){
		String jpql = "select P from Producto as P";
		return em.createQuery(jpql, Producto.class).getResultList();
		
	}
	public List<Producto> consultarByNombre(String nombre){
		String jpql = "select P from Producto as P where P.nombre=: nombre";
		return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
		
	}
	public List<Producto> consultarByNombreCategoria (String nombre ){
		String jpql = "select P from Producto as P where P.categoria.nombre=:nombre";
		return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
		
	}
	public BigDecimal consultarPrecioByNombreProducto(String nombre) {
		String jpql = "select P.precio from Producto as P where P.nombre=:nombre";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
		
	}
}
