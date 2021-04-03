package org.ssor.boss.loan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssor.boss.loan.dto.LoanDto;
import org.ssor.boss.loan.entity.Loan;
import org.ssor.boss.loan.repository.LoanRepository;
/**
 * @author Derrian Harris
 */
@Service
public class LoanService {

	
	@Autowired
	private LoanRepository loanDao;
	
	public Loan add(LoanDto loanDto) throws
    IllegalArgumentException{
		return loanDao.save(loanDto.convertToLoanEntity());
	}
	
	public Loan update(Integer id,LoanDto loanDto) throws
    IllegalArgumentException{
		Loan loan = findById(id);
		
		if (loanDto == null) {
		      throw new IllegalArgumentException();
		}
		
		if (loan == null)
		      throw new IllegalArgumentException("No Loan found with id: " + id);
		
		if(loanDto.getAmount() != null) {
			loan.setAmount(loanDto.getAmount());
		}
		if(loanDto.getAmountDue() != null) {
			loan.setAmountDue(loanDto.getAmountDue());
		}
		if(loanDto.getDueBy() != null) {
			loan.setDueBy(loanDto.getDueBy());
		}
		if(loanDto.getInterestRate() != null) {
			loan.setInterestRate(loanDto.getInterestRate());
		}
		if(loanDto.getTakenAt() != null) {
			loan.setTakenAt(loanDto.getTakenAt());
		}
		
		return loanDao.save(loan);
	}
	
	
	public Loan findById(Integer id)throws
    IllegalArgumentException {
		Optional<Loan> result = loanDao.findById(id);
		if(result.isEmpty()) {
			return null;
		}
		return result.get();
	}

	public List<Loan> findByBranchId(Integer branchId) {
		return loanDao.findByBranchId(branchId);
	}

	public Loan findByUserIdAndId(Integer userId, Integer id) {
		return loanDao.findByUserIdAndId(userId,id);
	}

	public List<Loan> findByUserId(Integer userId) {
		return loanDao.findByUserId(userId);
	}
	
	public List<Loan> findAllLoans() {
		return loanDao.findAll();
	}
	public void deleteById(Integer id) throws
    IllegalArgumentException {
		loanDao.deleteById(id);
	}

}
