package br.com.codenation.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		return items.stream()
				.map(orderItem -> {
					Product product = productRepository.findById(orderItem.getProductId()).get();
					if (product != null) {
						if(product.getIsSale()) {
							return product.getValue()*orderItem.getQuantity()*0.8;
						}
						return product.getValue()*orderItem.getQuantity();
					}
					return 0.0;
				})
				.reduce(0.0, Double::sum);
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return ids.stream()
				.map(id -> productRepository.findById(id).orElse(null))
				.filter(product -> product != null)
				.collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders.stream()
				.map(order -> calculateOrderValue(order))
				.reduce(0.0, Double::sum);
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		return productIds.stream()
				.map(id -> productRepository.findById(id).get())
				.collect(Collectors.groupingBy(Product::getIsSale));
	}

}