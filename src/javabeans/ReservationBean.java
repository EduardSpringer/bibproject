//Helene Akulow
package javabeans;

import java.io.Serializable;
import java.util.Date;

public class ReservationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int reservationID;
	private int placeID;
	private int repeatID;
	private String username;
	private Date datum;
	private String zeitraum;
	public int getReservationID() {
		return reservationID;
	}
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	public int getPlaceID() {
		return placeID;
	}
	public void setPlaceID(int placeID) {
		this.placeID = placeID;
	}
	public int getRepeatID() {
		return repeatID;
	}
	public void setRepeatID(int repeatID) {
		this.repeatID = repeatID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public String getZeitraum() {
		return zeitraum;
	}
	public void setZeitraum(String zeitraum) {
		this.zeitraum = zeitraum;
	}

}
