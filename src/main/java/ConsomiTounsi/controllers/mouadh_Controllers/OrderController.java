package ConsomiTounsi.controllers.mouadh_Controllers;

import ConsomiTounsi.Service.OrderManager;
import ConsomiTounsi.entities.Order;
import ConsomiTounsi.entities.Product;
import ConsomiTounsi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    OrderManager om;
    @Autowired
    OrderRepository orderRepository;
    //Create A new Order
    @PostMapping
    ResponseEntity<?> createNewOrder( @RequestBody Order o){
        om.addOrder(o);
        return new ResponseEntity<>(new MessageResponseModel("Order Added "), HttpStatus.CREATED);
    }
    //Show All Orders
    @GetMapping
    ResponseEntity<?> getAllOrder(){
        List<Order> o=om.retrieveAllOrder();
        GetAllOrderResponse orders = new GetAllOrderResponse(o);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
    //Show an order by id
    @GetMapping("/{id}")
    ResponseEntity<?> getOrderById(@PathVariable Long id){
        return new ResponseEntity<>(om.FindOrder(id),HttpStatus.OK);

    }
    //Show an oder by user id
    @GetMapping("user/{id}")
    ResponseEntity<?> getOrderByuserId(@PathVariable Long id){
        List<Order> o=orderRepository.findOrderByIdUser(id);
         List<Product> products=new ArrayList<>();
        for (Order order : o) {
            products =order.getProducts();
        }

        GetAllProductResponse orders = new  GetAllProductResponse(products);
        return new ResponseEntity<>(orders,HttpStatus.OK);

    }
    //Delete an Order by id
    @DeleteMapping ("/{id}")
    ResponseEntity<?> deleteProductById(@PathVariable Long id){
        om.deleteOrder(id);
        return new ResponseEntity<>(new MessageResponseModel("Order Deleted"),HttpStatus.OK);

    }
    //Update an Order
    @PutMapping("/{id}")
    ResponseEntity<?> updateProduct(@PathVariable Long id ,@RequestBody Order o){

        om.updateOrder(id,o);
        return new ResponseEntity<>(new MessageResponseModel("Order Updated"), HttpStatus.OK);
    }
    //addProducttoorder
    @PutMapping("man/{id}")
    ResponseEntity<?> addprodtoorder(@PathVariable Long id ,@RequestBody List<Product> product){
        Optional<Order> order=orderRepository.findById(id);
        order.get().setProducts(product);
        orderRepository.save(order.get());
    return new ResponseEntity<>(new MessageResponseModel("Order Updated"), HttpStatus.OK);}

    @PutMapping ("del/{id}")
    ResponseEntity<?> deleteProductfromorder(@PathVariable Long id ,@RequestBody List<Product> product){
        Optional<Order> order=orderRepository.findById(id);
        List<Product> productList=order.get().getProducts();
        List<Product> P= new ArrayList<>();
        for (Product products : productList) {
           for(Product product1 :product){
               if ((products.getId().equals(product1.getId()))==false){
                   P.add(products);
               }
           }
        }
        order.get().setProducts(P);
        orderRepository.save(order.get());

        return new ResponseEntity<>(order,HttpStatus.OK);

    }

}