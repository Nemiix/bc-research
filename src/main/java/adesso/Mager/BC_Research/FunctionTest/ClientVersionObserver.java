package adesso.Mager.BC_Research.FunctionTest;

import java.util.concurrent.CountDownLatch;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import rx.Subscription;

public class ClientVersionObserver {
	public static void observeClientVersion() throws Exception {
		Web3j web3j = Web3j.build(new HttpService());
		CountDownLatch countDownLatch = new CountDownLatch(1);

        Subscription subscription = web3j.web3ClientVersion().observable().subscribe(x -> {
            System.out.println(x.getWeb3ClientVersion());
            countDownLatch.countDown();
        });

        countDownLatch.await();
        subscription.unsubscribe();
	}
}
