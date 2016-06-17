package com.ebuy.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebuy.model.Address;
import com.ebuy.model.EGender;
import com.ebuy.model.Order;
import com.ebuy.model.Orderline;
import com.ebuy.model.Person;
import com.ebuy.model.Product;
import com.ebuy.repository.OrderLineRepository;

@Service
@Transactional
public class OrderLineService {

	@Autowired
	OrderLineRepository orderLineRepository;
	
	////////zelale
	@Autowired
	PersonService personService;
	@Autowired
	OrderService orderService;
	private double price;
	private List<Orderline> orline=new ArrayList<>();
	private int quantity;
	private int quan;
	/////////////

	
	public OrderLineService() {
	
	}
	
	public Orderline save(Orderline orderline){
		return orderLineRepository.save(orderline);
		
	}
	
	public void delete(Orderline orderline){
		orderLineRepository.delete(orderline);
	}
	
	public List<Orderline> findByOrder(Order order){
		return orderLineRepository.findByOrder(order);
	}
	
	public Orderline findById(int id){
		return orderLineRepository.findById(id);
	}
	
	public  List<Order> findByProductAndOrder(Product product,Person person){
		return orderLineRepository.findByProductAndOrder(product, person);
	}

	
	////////////////zelalem
	public void deleteOrder(Orderline oline){
		if(oline.getQuantity()>1){
			oline.setQuantity(oline.getQuantity()-1);
		}
		else{
		orline.remove(oline);
		}
	}

	public void listOrder(Product product,int quantity) {
		//Orderline(  Product product,int quantity, double price, double subtotal)
		
		//orderLineRepository.save(or);
		//price=price+(product.getPrice()* quantity);
		
		for(int i=0;i<orline.size();i++){
			if(orline.get(i).getProduct().getProductName().equals(product.getProductName())){
				int qu=orline.get(i).getQuantity();
				orline.get(i).setQuantity(qu+quantity);
				return;
			}
		}
		
		Orderline or=new Orderline(product,quantity,product.getPrice(),quantity * product.getPrice());
		  
		orline.add(or);
		
	}

	public OrderLineRepository getOrderLineRepository() {
		return orderLineRepository;
	}

	public void setOrderLineRepository(OrderLineRepository orderLineRepository) {
		this.orderLineRepository = orderLineRepository;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuan() {
		return quan;
	}

	public void setQuan(int quan) {
		this.quan = quan;
	}

	public List<Orderline> getOrline() {
		return orline;
	}

	public void setOrline(List<Orderline> orline) {
		this.orline = orline;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
//	---------------------------------------
public int listSize(){
	   quan=0;
	for(int i=0;i<orline.size();i++){
	quan=quan+orline.get(i).getQuantity();
	}
	return quan;
}
public double totPrice(){
	price=0;
	for(int i=0;i<orline.size();i++){
		price=price+orline.get(i).getSubtotal();
	}
	return price;
}


public void processPayment() throws AddressException, MessagingException {
	System.out.println("save1 " );
	  Order order = new Order(); 
	  order.setOrderDate(new Date());
	  order.setOrderLine(orline);
	  order.setTotalAmount(totPrice());
	  order.setQuantity(listSize());
	  System.out.println("save2 " );
	  
//	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	  String username = auth.getName();
//	  List<Address> address = new ArrayList<>();
//	 
//	address.add(new Address("alex home" , "iwoa","us","1234","123" ));
//	address.add(new Address("crio home" , "tanta office","eg","123","56" ));
		
//	Person newPerson =new Person( "ahmed", "ahmed", "amin", "8988888","amin", "8988888", EGender.Male,address,true);
	//personService.save(newPerson);
	//order.setPerson(personService.findByUserName(username));
	  System.out.println("save3 " );
	FacesContext facesContext = FacesContext.getCurrentInstance();
    ELContext elContext = facesContext.getELContext();
    ValueExpression targetExpression =
        facesContext.getApplication().getExpressionFactory().createValueExpression(elContext, "#{loginBean.username}", Object.class);
	
    String currentUserName=targetExpression.getValue(elContext).toString();

	Person person = personService.findByUserName(currentUserName);

	System.out.println("save4 " + currentUserName);
	  
	order.setPerson(person);
	//  System.out.println("Hello" + username);
	  orderService.save(order);
	  System.out.println("save5 " );
	  saveOrderLineToDB(orline,order);
	  System.out.println("save6 " );
	  MailService.sendEmail(order.getPerson().getEmail(), "ebuy system order No." + order.getId() ,
			  "ebuy system order No." + order.getId() + " thanks for shooping with us.");
	     orline.removeAll(orline);
	 }

	 public void saveOrderLineToDB(List<Orderline> orderline, Order order) {
	  for (Orderline order_line : orderline) {
		  order_line.setOrder(order);
	   save(order_line);
	  }
	 }

	 
	//////////////////////zelalem
	
}
