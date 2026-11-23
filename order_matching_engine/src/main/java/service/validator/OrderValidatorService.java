package service.validator;

import exception.InvalidOrderException;
import model.Order;

public interface OrderValidatorService {
  void validate(Order order) throws InvalidOrderException;
}
