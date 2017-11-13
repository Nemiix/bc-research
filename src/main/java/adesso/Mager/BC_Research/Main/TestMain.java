package adesso.Mager.BC_Research.Main;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//NewBlockSubscriber.subriptionTest();
		//NewBlockSubscriber.subscriptionTestWithBlockLimit(10);
		try {
			EtherCounting.countEther(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
