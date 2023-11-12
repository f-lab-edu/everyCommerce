package com.everycommerce.orderservice.service;

import com.everycommerce.orderservice.domain.Order;
import com.everycommerce.orderservice.dto.OrderDTO;
import com.everycommerce.orderservice.vo.ResponseProduct;

public interface OrderSerive {

	/*주문하는거*/

	void createOrder(OrderDTO orderDTO);


	/*멤버 아이디로 주문찾기 */

	OrderDTO getOrder(String memberId);





}
