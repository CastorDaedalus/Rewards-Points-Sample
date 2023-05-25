package com.van.josh.rewardspoints;

import com.github.javafaker.Faker;
import com.van.josh.rewardspoints.Constants.TransactionType;
import com.van.josh.rewardspoints.model.Customer;
import com.van.josh.rewardspoints.model.RewardsPoints;
import com.van.josh.rewardspoints.model.RewardsPointsConfig;
import com.van.josh.rewardspoints.model.Transaction;
import com.van.josh.rewardspoints.repository.CustomerRepository;
import com.van.josh.rewardspoints.repository.RewardsPointsConfigRepository;
import com.van.josh.rewardspoints.repository.TransactionRepository;
import com.van.josh.rewardspoints.service.RewardsPointsService;
import com.van.josh.rewardspoints.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class RewardsPointsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RewardsPointsApplication.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	RewardsPointsConfigRepository rewardsPointsConfigRepository;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker();

		RewardsPointsConfig config = new RewardsPointsConfig();
		config.setPointsPerDollar(BigDecimal.valueOf(1));
		config.setPointsPerDollarMultiplier(BigDecimal.valueOf(2));
		config.setPointsPerDollarThreshold(BigDecimal.valueOf(50));
		config.setPointsPerDollarMultiplierThreshold(BigDecimal.valueOf(100));

		rewardsPointsConfigRepository.save(config);

		Customer customer1 = new Customer(faker.funnyName().name(), faker.internet().emailAddress());
		Customer customer2 = new Customer(faker.funnyName().name(), faker.internet().emailAddress());

		customerRepository.save(customer1);
		customerRepository.save(customer2);

		List<Transaction> transactions = new ArrayList<>();
		for(int i = 0; i < 400; i++) {
			Transaction transaction = new Transaction();
			transaction.setAmount(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 300)));
			transaction.setTransactionType(faker.options().option(TransactionType.class));
			transaction.setTransactionDate(faker.date().past(90, TimeUnit.DAYS)
					.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate());
			transaction.setCustomerId(faker.options().option(customer1.getId(), customer2.getId()));

			transactions.add(transaction);
        }
		transactionService.saveAll(transactions);

	}
}
