package nawang.salira.edumom;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Date;

@IgnoreExtraProperties
public class IbuHamil implements Serializable {

    public String email;
    public String password;
    public Date tglHpht;
    public int siklus;
    public int usiaKehamilan;
    public String noHp;
    public Date tglHpl;

    public IbuHamil() {

    }

    public IbuHamil(String email, String password, Date tglHpht, int siklus, int usiaKehamilan, String noHp, Date tglHpl) {
        this.email = email;
        this.password = password;
        this.tglHpht = tglHpht;
        this.siklus = siklus;
        this.usiaKehamilan = usiaKehamilan;
        this.noHp = noHp;
        this.tglHpl = tglHpl;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTglHpht() {
        return tglHpht;
    }

    public void setTglHpht(Date tglHpht) {
        this.tglHpht = tglHpht;
    }

    public int getSiklus() {
        return siklus;
    }

    public void setSiklus(int siklus) {
        this.siklus = siklus;
    }

    public int getUsiaKehamilan() {
        return usiaKehamilan;
    }

    public void setUsiaKehamilan(int usiaKehamilan) {
        this.usiaKehamilan = usiaKehamilan;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public Date getTglHpl() {
        return tglHpl;
    }

    public void setTglHpl(Date tglHpl) {
        this.tglHpl = tglHpl;
    }

    @Override
    public String toString() {
        return " "+email+"\n" +
                " "+password+"\n" +
                " "+tglHpht+"\n" +
                " "+siklus+"\n" +
                " "+usiaKehamilan+"\n" +
                " "+noHp+"\n" +
                " "+tglHpl;
    }
}
