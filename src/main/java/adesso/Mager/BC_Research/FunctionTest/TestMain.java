package adesso.Mager.BC_Research.FunctionTest;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//NewBlockSubscriber.subriptionTest();
		
		//NewBlockSubscriber.subscriptionTestWithBlockLimit(10);
		
		//EtherCounting.countEther(10);
		
		//PendingTransactionObserver.observePendingTransactions();
		
		try {
			ClientVersionObserver.observeClientVersion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
