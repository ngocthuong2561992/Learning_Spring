package jmaster.io.accountservice.service;

import java.util.List;

import jmaster.io.accountservice.model.AccountDTO;

public interface AccountService {
	void add(AccountDTO accountDTO);

	void update(AccountDTO accountDTO);

	void updatePassword(AccountDTO accountDTO);

	void delete(Long id);

	List<AccountDTO> getAll();

	AccountDTO getOne(Long id);
}
