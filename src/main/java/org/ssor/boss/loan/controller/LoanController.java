/**
 * 
 */
package org.ssor.boss.loan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssor.boss.loan.dto.LoanDto;
import org.ssor.boss.loan.entity.Loan;
import org.ssor.boss.loan.entity.LoanType;
import org.ssor.boss.loan.service.LoanService;

import javassist.NotFoundException;

/**
 * @author Derrian Harris
 */
@RestController
public class LoanController {
	@Autowired
	private LoanService loanService;

	@GetMapping(path = "api/loans/{loan_id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Object> getLoanById(@PathVariable("loan_id") @Valid String id) {
		Loan loan = new Loan();
		try {
			loan = loanService.findById(Integer.parseInt(id));
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Object>("Invalid request data.", HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>("Loan not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(loan, HttpStatus.OK);
	}

	@GetMapping(path = "api/users/{user_id}/loans", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Object> getLoansByUserId(@PathVariable("user_id") @Valid String userId) {

		List<Loan> loans = new ArrayList<Loan>();
		try {
			loans = loanService.findByUserId(Integer.parseInt(userId));
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Object>("Invalid request data.", HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>("Loans not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(loans, HttpStatus.OK);
	}

	@GetMapping(path = "api/branches/{branch_id}/loans", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Object> getLoanByBranchId(@PathVariable("branch_id") @Valid String branchId) {
		List<Loan> loans = new ArrayList<Loan>();
		try {
			loans = loanService.findByBranchId(Integer.parseInt(branchId));
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Object>("Invalid request data.", HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>("Loans not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(loans, HttpStatus.OK);
	}

	@PostMapping(path = "api/loans", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
			MediaType.TEXT_PLAIN_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> addLoanByBranchId(@RequestBody @Valid LoanDto loanDto) {
		Loan loan;
		try {
			loan = loanService.add(loanDto);
		} catch (IllegalArgumentException | NotFoundException e) {
			return new ResponseEntity<Object>("Invalid request data.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(loan, HttpStatus.CREATED);
	}

	@PutMapping(path = "api/loans/{loan_id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE }, consumes = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> updateLoanByBranchId(@PathVariable("loan_id") @Valid String loanId,
			@RequestBody LoanDto loanDto) {
		Loan newLoan;
		try {
			loanDto.setId(Integer.parseInt(loanId));
			newLoan = loanService.update(loanDto);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Object>("Invalid request data.", HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>("Loan not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(newLoan, HttpStatus.OK);

	}

	@DeleteMapping(path = "api/loans/{loan_id}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Object> deleteLoanById(@PathVariable("loan_id") @Valid String loanId) {
		try {
			loanService.deleteById(Integer.parseInt(loanId));
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Object>("Invalid request data.", HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>("Loan not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@GetMapping(path = "api/loans/types", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Object> getAllLoanTypes() {
		List<LoanType> loanTypes = new ArrayList<LoanType>();
		try {
			loanTypes = loanService.findAllLoanTypes();
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>("Loans Types not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(loanTypes, HttpStatus.OK);
	}
}
