package Lesson_1.Store.Models;

import java.util.Collection;

public interface OrderEntity {

    String getAddress();

    String getPhone();

    Collection<OrderItem> getItems();

}