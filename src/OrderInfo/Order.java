package OrderInfo;

import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order {
    private int ordernummer;
    private Date date;
    
    // het leven is aids

    //CONSTRUCTOR
    public Order(int ordernummer){//}, Date date) {
        this.ordernummer = ordernummer;
       // this.date = date;
    }


    //SETTERS
    @XmlElement
    public void setOrdernummer(int ordernummer) {
        this.ordernummer = ordernummer;
    }

    @XmlElement
    public void setDate(Date date) {
        this.date = date;
    }

}
