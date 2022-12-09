package order;


public class loadBalance {
	
	static int countCatalog=0;
	static int countOrder=0;
	
	
	public static void setCatalogServerIPAndPort(){
		if(countCatalog < 3) {
			//catalog Server #1
			System.out.println("Load Balance: go to Catalog Server #1.");
			OrderServer.CATALOG_IP_ADDRESS="localhost"; // we need to change IP to be real cat. IP address #1
			OrderServer.CATALOG_PORT="4567";
			countCatalog = (countCatalog+1)%6;
		}else {
			//catalog Server #2
			System.out.println("Load Balance: go to Catalog Server #2.");
			OrderServer.CATALOG_IP_ADDRESS="localhost"; // we need to change IP to be real cat. IP address #2
			OrderServer.CATALOG_PORT="4568";
			countCatalog = (countCatalog+1)%6;
		}
	}
}
