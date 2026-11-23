package service.validator;

import model.Order;

import java.util.Set;

public class CountryValidator implements OrderValidatorService {

    private static final Set<String> APPROVED_COUNTRIES =
            Set.of("US", "UK", "IN", "SG", "JP", "DE", "FR");

    @Override
    public void validate(Order order) {
        String country = order.getCountry();
        if (country == null || !APPROVED_COUNTRIES.contains(country.toUpperCase())) {
            throw new IllegalArgumentException("Country not approved: " + country);
        }
    }
}


//package service.validator;
////import exception.CountryCodeNotAllowedException;
//import exception.InvalidOrderException;
//import model.Order;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//public class CountryValidator implements OrderValidatorService{
//
//    private static final Set<String> APPROVED = new HashSet<>(Arrays.asList("US","UK","IN","SG","JP","DE","FR"));
//
//    @Override
//    public void validate(Order order) throws InvalidOrderException {
//        if(order.getCountryCode()==null || !APPROVED.contains(order.getCountryCode().toUpperCase())){
//            throw new CountryCodeNotAllowedException("Country not Allowed: "+order.getCountryCode());
//        }
//    }
//}
