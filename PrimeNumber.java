class PrimeNumber {
	public static void main (String [] args){
		int num = 2;
		int inc = 0;
		for(int i = 1; i<= num ; i++){
			if(num % i ==0) {
				inc++;
			}
		}
		if(inc == 2 ){
			System.out.println(num + " is prime number ");
		}else {
			 System.out.println(num + " is not prime number ");
		}
	}
}











