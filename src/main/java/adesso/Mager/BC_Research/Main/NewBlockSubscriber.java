package adesso.Mager.BC_Research.Main;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;

import rx.Observable;
import rx.Subscription;
/**
 * Listens for new blocks comming in and generates a message for them.
 * @author mager
 *
 */
public class NewBlockSubscriber {

	public static void subriptionTest() {
		//connection to ethereum
		Web3j web3j = Web3j.build(new HttpService());
		//polling for new blocks
		Subscription sub = web3j.blockObservable(false).subscribe(block ->{
			System.out.println("Sweet, new block with number "+block.getBlock().getNumber() +" has been created!");
		}, Throwable::printStackTrace);
		
		//subscription runs in a dedicated thread, so we have to give it time
		try {
			TimeUnit.MINUTES.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sub.unsubscribe();
	}
	
	public static void subscriptionTestWithBlockLimit(int count) {
		Web3j web3j = Web3j.build(new HttpService());
		CountDownLatch countDownLatch = new CountDownLatch(1);
		
		System.out.println("Waiting for "+count+" transactions...");
		Subscription sub = web3j.blockObservable(true)
				.take(count)
				.subscribe(ethBlock -> {
					EthBlock.Block block = ethBlock.getBlock();
					LocalDateTime timestamp = Instant.ofEpochSecond(
							block.getTimestamp().longValueExact()).atZone(ZoneId.of("UTC+1")).toLocalDateTime();
					int transactionCount = block.getTransactions().size();
					String hash = block.getHash();
					String parentHash = block.getParentHash();
				
					System.out.println(
						timestamp + " Tx count: " + transactionCount + ", "+
						"Hash: "+hash +", Parent hash: "+parentHash
						);
					countDownLatch.countDown();
				},Throwable::printStackTrace);
		try {
			TimeUnit.MINUTES.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sub.unsubscribe();
	}
	
	
	

}
