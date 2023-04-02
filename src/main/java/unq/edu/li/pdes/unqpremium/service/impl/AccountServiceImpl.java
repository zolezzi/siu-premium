package unq.edu.li.pdes.unqpremium.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unq.edu.li.pdes.unqpremium.dto.AccountDTO;
import unq.edu.li.pdes.unqpremium.exception.UnqPremiumException;
import unq.edu.li.pdes.unqpremium.mapper.Mapper;
import unq.edu.li.pdes.unqpremium.model.Account;
import unq.edu.li.pdes.unqpremium.repository.AccountRepository;
import unq.edu.li.pdes.unqpremium.service.AccountService;
import unq.edu.li.pdes.unqpremium.vo.AccountVO;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

	private final AccountRepository repository;
	private final Mapper mapper;
	
	@Override
	public AccountDTO createAccount(AccountVO accountVO) {
		var accountDB = repository.findOneByDni(accountVO.getDni());
		if(accountDB.isPresent()) {
			throw new UnqPremiumException(String.format("error there is already an account registered with this DNI: %s ", accountVO.getDni()));
		}
		var accountNew = mapper.map(accountVO, Account.class);
		accountNew = repository.save(accountNew);
		return mapper.map(accountNew, AccountDTO.class);
	}

	@Override
	public Account createAccountByUser(AccountVO accountVO) {
		var accountDB = repository.findOneByDni(accountVO.getDni());
		if(accountDB.isPresent()) {
			throw new UnqPremiumException(String.format("error there is already an account registered with this DNI: %s ", accountVO.getDni()));
		}
		var accountNew = mapper.map(accountVO, Account.class);
		return repository.save(accountNew);
	}

}
