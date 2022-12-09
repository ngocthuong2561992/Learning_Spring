package jmaster.io.accountservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmaster.io.accountservice.entity.Account;
import jmaster.io.accountservice.model.AccountDTO;
import jmaster.io.accountservice.repository.AccountRepository;

@Transactional
@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public void add(AccountDTO accountDTO) {
		// TODO Auto-generated method stub
		Account account = modelMapper.map(accountDTO, Account.class);
		
		accountRepository.save(account);
		
		accountDTO.setId(account.getId());
		
	}

	@Override
	public void update(AccountDTO accountDTO) {
		// TODO Auto-generated method stub
		Account account = accountRepository.getById(accountDTO.getId());
		if (account != null) {
			modelMapper.typeMap(AccountDTO.class, Account.class).addMappings(mapper ->mapper.skip(Account::setPassword)).map(accountDTO , account);
			accountRepository.save(account);
		}
	}

	@Override
	public void updatePassword(AccountDTO accountDTO) {
		// TODO Auto-generated method stub
		Account account = accountRepository.getById(accountDTO.getId());
		if (account != null) {
			accountRepository.save(account);
		}
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.getById(id);
		if (account != null) {
			accountRepository.delete(account);
		}
	}

	@Override
	public List<AccountDTO> getAll() {
		// TODO Auto-generated method stub
		List<AccountDTO> accountDTOs = new ArrayList<>();
		
		accountRepository.findAll().forEach((account)->{
			accountDTOs.add(modelMapper.map(account, AccountDTO.class));
		});
		return accountDTOs;
	}

	@Override
	public AccountDTO getOne(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.getById(id);
		if (account != null) {
			return modelMapper.map(account, AccountDTO.class);
		}
		return null;
	}

}
