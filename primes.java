
public class primes {
  public static void main(String[] args){
    System.out.print("hello \r");

  }

  public static boolean isprime(int num){
    if (num<2){
      return false;
    }
    if ((num==2)||(num==3)){
      return true;
    }
    if (((num+1)%6!=0)&&((num-1)%6!=0)){
      return false;
    }
    int cap = (int)Math.pow(num, 0.5)+1;
    for (int i=6;i<=cap;i+=6){
      if ((num%(i+1)==0)||(num%(i-1)==0)){
        return false;
      }
    }
    return true;
  }

  public static long getprime(long guess){
    if (guess%2==0){
      guess++;
    }
    long i = 0;
    while (true){
      if (primes.isprime(guess+i)){
        return(guess+i);
      }
      i = (i>0) ? -i:-i+2;
    }
  }


  public static boolean isprime(long num){
    if (num<2){
      return false;
    }
    if ((num==2)||(num==3)){
      return true;
    }
    if (((num+1)%6!=0)&&((num-1)%6!=0)){
      return false;
    }
    int cap = (int)Math.pow(num, 0.5)+1;
    for (int i=6;i<=cap;i+=6){
      if ((num%(i+1)==0)||(num%(i-1)==0)){
        return false;
      }
    }
    return true;
  }
}
