package adesso.Mager.BC_Research.FunctionTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import rx.Observable;
import rx.Subscription;

public class EtherCounting {

	
	public static void countEther(int count) throws InterruptedException {
		Web3j web3j = Web3j.build(new HttpService());
		CountDownLatch latch = new CountDownLatch(count);
		System.out.println("Waiting for " + count+ " transactions...");
		
		Observable<BigInteger> transactionValue = web3j.transactionObservable()
															.take(count)
															.map(Transaction::getValue)
															.reduce(BigInteger.ZERO, BigInteger::add);
		
		Subscription sub = transactionValue.subscribe(total -> {
			System.out.println(". Transaction value: " + Convert.fromWei(new BigDecimal(total), Convert.Unit.ETHER) + " Ether");
			latch.countDown();
		}, Throwable::printStackTrace);
		latch.await(10, TimeUnit.MINUTES);
		sub.unsubscribe();
	}
}
