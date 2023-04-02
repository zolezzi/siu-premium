package unq.edu.li.pdes.unqpremium.service;

import unq.edu.li.pdes.unqpremium.dto.AccountDTO;
import unq.edu.li.pdes.unqpremium.model.Account;
import unq.edu.li.pdes.unqpremium.vo.AccountVO;

public interface AccountService {

	AccountDTO createAccount(AccountVO accountVO);
	
	Account createAccountByUser(AccountVO accountVO);
}
