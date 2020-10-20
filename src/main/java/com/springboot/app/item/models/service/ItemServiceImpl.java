//package com.springboot.app.item.models.service;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.formacionbdi.springboot.app.item.models.Item;
//import com.formacionbdi.springboot.app.item.models.Producto;
//
//@Service("serviceRestTemplate")
//public class ItemServiceImpl implements ItemService {
//
//	@Autowired
//	private RestTemplate clienteRest;
//	
//	@Override
//	public List<Item> findAll() {
//		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/listar", Producto[].class));
//		
//		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
//	}
//
//	@Override
//	public Item findById(Long id, Integer cantidad) {
//		Map<String, String> pathVariables = new HashMap<String, String>();
//		pathVariables.put("id", id.toString());
//		Producto producto = clienteRest.getForObject("http://servicio-productos/ver/{id}", Producto.class, pathVariables);
//		return new Item(producto, cantidad);
//	}
//
//	@Override
//	public Producto save(Producto producto) {
//
//		//Este request contiene el Objeto producto que vamos a mandar mas adelante.
//		HttpEntity<Producto> requestBody = new HttpEntity<Producto>(producto);
//		
//		/*
//		 * Para consumir el apiRest de productos. Utilizamos el metodo exchange de RestTtemplate
//		 * El primer parametro del excange es la url la cual va ser la que enviemos para crear el objeto producto.
//		 * El segundo es el tipo de metodo de envio(Post, Put, Get, Delte...Upadate)-> HttpMethod.POST
//		 * La siguiente es enviar el request que contiene el objeto que vamos a mandar, en este caso el objeto producto -> requestBody
//		 * El siquiente dato es el tipo de dato el cual queremos recibir este objeto, el json que nos retorna, en este caso de tipo producto.
//		 */
//				
//		/*
//		 * El clienteRest.exchange retorna ResponseEntity<T> a si que lo guardamos en una variable de tipo ResponseEntity<T> en nuestro casto ResponseEntity<Producto>
//		 */
//		ResponseEntity<Producto> response =	clienteRest.exchange("http://servicio-productos/crear", HttpMethod.POST, requestBody, Producto.class);
//		
//		/*
//		 * Ahora tenemos que retornar el prodcuto y lo retornamos atraves de la respuesta. Y con el metodo response.getBody() obtenemos el cuerpo de la respuesta
//		 * que como habiamos incidacado anteriormente es de tipo producto.
//		 */
//		
//		Producto productoResponse = response.getBody();
//
//		return productoResponse;
//	}
//
//	@Override
//	public Producto update(Producto producto, Long id) {
//
//		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
//		Map<String, String> pathVariables = new HashMap<String, String>();
//		pathVariables.put("id", id.toString());
//		
//		ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/editar/{id}", 
//				HttpMethod.PUT,body, Producto.class,pathVariables);
//		
//		return response.getBody();
//	}
//
//	@Override
//	public void delete(Long id) {
//
//		Map<String, String> pathVariables = new HashMap<String, String>();
//		pathVariables.put("id", id.toString());
//		clienteRest.delete("http://servicio-productos/delete/{id}", pathVariables);
//		
//	}
//	
//
//}
