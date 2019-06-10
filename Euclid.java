package numbers; //tuple


public class Euclid{
  /**
    Class for performing certain numeric calculations. All numbers are longs
  **/
  public static void main(String[] args) throws EuclidException{
    long a = 8;
    long b = 35;
    tuple pre = diophantine(a, b);
    System.out.println("x, y: ", pre.x, pre.y);

  }

  public static long GCD(long a, long b){
    while(a%b!=0){
    long r = a%b;
    a = b;
    b = r;
    }
    return b;
  }

  public static tuple diophantine(long a, long b) throws EuclidException{
    /**
      Inputs a and b are the coefficients a and b in the equation
      a*x+b*y==1
      diophantine(a,b) returns a tuple (x, y), where x and y are integer
      solutions to the above equation (all inputs and outputs are of long type)
    **/
    if ((a==0)||(b==0)||(a%b==0)){
      throw new OneOrMoreArgumentsIsZeroException("either a or b is 0");
    }
    long r = a%b;
    long k = a/b;
    if (b%r==0){
      if (r==1){
        return new tuple(1, -k);
      }
      else if (r==-1){
        return new tuple(-1, k);
      }else{
        throw new ArgumentsNotCoprimeException("a and b must be coprime (gcd(a,b)==1)");
      }
    }
    else {
      tuple pre = diophantine(b,r);
      return(new tuple(pre.y, pre.x-k*pre.y));
    }
  }

  public static tuple diophantine_nx (long a, long b) throws EuclidException{
    /**
    Inputs a and b are the coefficients a and b in the equation
    a*x+b*y==1
    diophantine(a,b) returns a tuple (x, y), where x and y are
    non negative integer solutions to the above equation
    (all inputs and outputs are of long type)
    **/
    tuple pre = diophantine(a, b);
    long x = pre.x;
    long y = pre.y;
    if (x > 0){
      long k = (-x)/b;
      if (b>0){
        x += (k-1)*b;
        y -= (k-1)*a;
      }
      else{
        x += (k+1)*b;
        y -= (k+1)*a;
      }
    }
    return new tuple(x,y);
  }

  public static long mod_exp(long base, long exp, long mod){
    /**
      calculates (base^exp)%mod using a medium speed algorithm
    **/
    long result = base%mod;
    for (long i=1;i<exp;i++){
      result = (result*base)%mod;
    }
    return result;
  }

  public static long fast_exp(long base, long exp, long mod){
    /**
      calculates (base^exp)%mod using a fast algorithm
    **/
    long result = 1;
    long x=base%mod;
    while (exp>0){
      long i = exp % 2;
      exp /= 2;
      if (i==1){
        result = ((result%mod)*(x%mod))%mod;
      }
      x = ((x%mod)*(x%mod))%mod;
    }
    return result;
  }

  public static long log_int(long input, long base){
      /**
        Calculates log(input) with the given base, rounded down
        to the nearest integer (long)
      **/
    long exp = 0;
    while (input/base > 0){
      input /= base;
      exp ++;
    }
    return exp;
  }

//end of class ________________
}

class tuple{
  public long x,y;
  public tuple(long x, long y){
    this.x = x;
    this.y = y;
  }

  public tuple(){
    this.x = 0;
    this.y = 0;
  }

}

class EuclidException extends Exception{
  public static void main(String[] args){

  }

  public EuclidException(String s){
    super(s);
  }

  public EuclidException(){
    super("");
  }
}

class ArgumentsNotCoprimeException extends EuclidException{
  public ArgumentsNotCoprimeException(String s){
    super(s);
  }

  public ArgumentsNotCoprimeException(){
    super("");
  }
}

class OneOrMoreArgumentsIsZeroException extends EuclidException{
  public static void main(String[] args){

  }

  public OneOrMoreArgumentsIsZeroException(String s){
    super(s);
  }

  public OneOrMoreArgumentsIsZeroException(){
    super("");
  }
}
