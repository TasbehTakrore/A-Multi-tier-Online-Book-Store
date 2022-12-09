package order;


public class loadBalance {
	
	static int countCatalog=0;
	static int countOrder=0;
	private static String CATALOG_IP_ADDRESS_1=System.getenv("CATALOG_IP_ADDRESS_1");
	private static String CATALOG_IP_ADDRESS_2=System.getenv("CATALOG_IP_ADDRESS_2");
	private static String CATALOG_PORT_1=System.getenv("CATALOG_PORT_1");
	private static String CATALOG_PORT_2=System.getenv("CATALOG_PORT_2");
	
	
	public static void setCatalogServerIPAndPort(){
		if(countCatalog < 3) {
			//catalog Server #1
			System.out.println("Load Balance: go to Catalog Server #1.");
			OrderServer.CATALOG_IP_ADDRESS=CATALOG_IP_ADDRESS_1; // we need to change IP to be real cat. IP address #1
			OrderServer.CATALOG_PORT=CATALOG_PORT_1;
			countCatalog = (countCatalog+1)%6;
		}else {
			//catalog Server #2
			System.out.println("Load Balance: go to Catalog Server #2.");
			OrderServer.CATALOG_IP_ADDRESS=CATALOG_IP_ADDRESS_2; // we need to change IP to be real cat. IP address #2
			OrderServer.CATALOG_PORT=CATALOG_PORT_2;
			countCatalog = (countCatalog+1)%6;
		}
	}
}
