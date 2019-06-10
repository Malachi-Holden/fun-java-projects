import java.util.*;

public class Sieve{
  private int max, size;
  private List<Long> sieve;

  public Sieve(int max){
    this.max = max;
    this.sieve = buildlist(this.max);
    this.size = this.sieve.size();
  }

  private List<Long> buildlist(int max){
    List<Long> outlist = new ArrayList<>();
    if (max < 2){
      return outlist;
    }
    outlist.add((long)2);
    for (long i=3; i<max; i+=2){
      outlist.add(i);
    }
    return outlist;
  }

  private void strip(long factor){
    for (int i=0;i<this.sieve.size();i++){
      if ((this.sieve.get(i)%factor == 0)&&(this.sieve.get(i)!=factor)){
        this.sieve.set(i, (long)(-1));
      }
    }
  }

  private void consolidate(){
    List<Long> newsieve = new ArrayList<>();
    for (int i=0;i<this.sieve.size();i++){
      long item = this.sieve.get(i);
      if (item != (long)(-1)){
        newsieve.add(item);
      }
    }
    this.sieve = newsieve;
    this.size = this.sieve.size();
  }

  public void run(){
    int index = 1;
    long item = this.sieve.get(index);
    while (item <= (long)Math.round(Math.pow(max,0.5))){
      if(item!=-1){
      strip(item);
      }
      index += 1;
      try{
        item = this.sieve.get(index);
      }
      catch(IndexOutOfBoundsException e){
        System.out.println(getsieve());
        break;
      }
    }
    consolidate();
  }

  public int getmax(){
    return this.max;
  }

  public int getsize(){
    return this.size;
  }

  public List<Long> getsieve(){
    return this.sieve;
  }

  public long getprime(int index){
    return this.sieve.get(index);
  }










  public static void main(String[] args){
    Sieve a = new Sieve(100);
    a.run();
    System.out.println(a.getsieve());
  }
}
