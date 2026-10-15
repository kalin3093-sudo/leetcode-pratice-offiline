import java.util.*;
class Marker {
  public String defangIPaddr(String address) {
    return address.replace(".", "[.]");
  }

    public boolean isCorrect(String address, String output) {
        String expected = defangIPaddr(address);
        return expected.equals(output);
    }
}
