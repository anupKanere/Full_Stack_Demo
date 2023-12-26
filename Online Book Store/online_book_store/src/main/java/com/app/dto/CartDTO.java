package com.app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long cartId;
    private List<BookDTO> books;
    private int totalBooks;
    private float totalPrice;
    private float discount;
    private UserDTO user;

    
    
    
    
}