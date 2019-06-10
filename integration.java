
public class integration{
  public static void main(String[] args){
    integration A = new integration();
    System.out.println(f(4.5));
    System.out.println(integrate(0, 1, 8));
  }

  public double naive_integrate(double a, double b, int n){
    double sum = 0;
    double dx = (a + b)/n;
    for (int i=0;i<n;i++){
      sum += (f(i*dx + a)*dx);
    }
    return sum;
  }

  public double parallelagram_integrate(double a, double b, int n){
    double sum = 0;
    double dx = (a + b)/n;
    for (int i=0;i<n;i++){
      sum += ((f(i*dx + a)+f((i+1)*dx + a))*dx/2);
    }
    return sum;
  }

  public double integrate(double a, double b, int n){
    return parallelagram_integrate(a, b, n);
  }

  private double f(double x){
    /***
    Overwrite this function with your own function that you want to integrate.
    Right now it is set to x^2 for testing purposes
    ***/
    return x*x;
  }
















  //_____________end of class ______________
}
