package fr.insa.BankWebServices.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import io.spring.payment.Payment;
import io.spring.payment.Type;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class PaymentRepository {
	private static final Map<Integer, Payment> payments = new HashMap<>();

	@PostConstruct
	public void initData() {
		Payment op1 = new Payment();
		op1.setIdOperation(1);
		op1.setPrix(200);
		op1.setType(Type.DEBIT);

		payments.put(op1.getIdOperation(), op1);

		Payment op2 = new Payment();
		op2.setIdOperation(2);
		op2.setPrix(500);
		op2.setType(Type.REMBOURSEMENT);

		payments.put(op2.getIdOperation(), op2);

		Payment op3 = new Payment();
		op3.setIdOperation(3);
		op3.setPrix(400);
		op3.setType(Type.DEBIT);

		payments.put(op3.getIdOperation(), op3);
	}

	public Payment findPayment(Integer idOperation) {
		Assert.notNull(idOperation, "The idOperation must not be null");
		return payments.get(idOperation);
	}
}