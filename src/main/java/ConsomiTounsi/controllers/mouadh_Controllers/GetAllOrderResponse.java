package ConsomiTounsi.controllers.mouadh_Controllers;

import ConsomiTounsi.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllOrderResponse {
    List<Order> orders;
}
