package adesso.Mager.BC_Research.FunctionTest;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import rx.Subscription;

public class PendingTransactionObserver {

		public static void observePendingTransactions() {
			Web3j web3j = Web3j.build(new HttpService());
			Subscription sub = web3j.pendingTransactionObservable().subscribe(tx ->{
				
				String input = tx.getInput();
				BigInteger value = tx.getValue();
				System.out.println("Tx Input: "+input+", value: "+value);
			}, Throwable::printStackTrace);
			try {
				TimeUnit.MINUTES.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sub.unsubscribe();
		}
}
