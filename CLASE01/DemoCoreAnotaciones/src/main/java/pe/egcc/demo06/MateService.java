package pe.egcc.demo06;

import javax.inject.Named;

@Named
public class MateService {

  public int sumar(int n1, int n2){
    return (n1+n2);
  }
  
}
