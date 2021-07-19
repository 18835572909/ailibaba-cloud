package com.rhb.order.status;

import com.rhb.pojo.entity.Order;
import com.rhb.pojo.enums.OrderStatus;
import com.rhb.pojo.enums.OrderStatusChangeEvent;
import java.util.EnumSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/7/19 10:59
 */
@Configuration
@EnableStateMachine(name = "orderStatusMachine")
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvent> {

  @Override
  public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvent> states)
      throws Exception {
    states.withStates()
        .initial(OrderStatus.WAIT_PAYMENT)
        .states(EnumSet.allOf(OrderStatus.class));
  }

  @Override
  public void configure(
      StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvent> transitions)
      throws Exception {
    transitions.withExternal().source(OrderStatus.WAIT_PAYMENT)
                              .target(OrderStatus.WAIT_DELIVER)
                              .event(OrderStatusChangeEvent.PAYED)
        .and().withExternal().source(OrderStatus.WAIT_DELIVER)
                              .target(OrderStatus.WAIT_RECEIVE)
                              .event(OrderStatusChangeEvent.DELIVERY)
        .and().withExternal().source(OrderStatus.WAIT_RECEIVE)
                              .target(OrderStatus.FINISH)
                              .event(OrderStatusChangeEvent.RECEIVED);
  }

}
