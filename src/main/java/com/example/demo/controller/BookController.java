package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@RestController
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/getallBooks")
	public ResponseEntity<List<Book>> getallBooks() {
		try {
			
			List<Book> book = bookRepository.findAll();
			return new ResponseEntity<>(book,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			// TODO: handle exception
		}
		
	} 
	
	@GetMapping("/getBookById/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		
		try {
			Optional<Book> book = bookRepository.findById(id);
			return new ResponseEntity<>(book.get(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			// TODO: handle exception
		}
		
	}
	
	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		
		Book returnbook = bookRepository.save(book);
		return new ResponseEntity<>(returnbook,HttpStatus.OK);
		
		
	}
	
	@PostMapping("/updateBook/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
		
		Optional<Book> oldbook = bookRepository.findById(id);
		Book updateBook = oldbook.get();
		updateBook.setTittle(book.getTittle());
		updateBook.setPrice(book.getPrice());
		
		Book returnbook = bookRepository.save(updateBook);
		
		return new ResponseEntity<>(returnbook,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
		
		bookRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
