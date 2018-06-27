/**	
 * Autor: Eduard Springer
 * 
 * Das ReservationWiederholterminServlet entnimmt die übergebene Liste mit besetzten/ freien Plätzen von listener.js,
 * die aus der CheckPlaceServlet stammt.
 * Dabei werden aus der Liste nur die freien Plätze, die das Attribut "datum" besitzen und nicht "datumBesetzt", entnommen.
 * Die Terminbezeichnung wird auf das Format "UTF-8" formatiert.
 * Verbucht den Wiederholtermin (Datum, Zeitraum, Platz-Nr., Terminbezeichnung) für den jeweiligen User.
 * Setzt den Status auf OK (200).
 */

package de.reserve;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javabeans.LoginBean;

@WebServlet("/reservationwiederholterminservlet")
public class ReservationWiederholterminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup = "jdbc/MyTHIPool")
	private DataSource ds;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		LoginBean user = (LoginBean) session.getAttribute("lb");
		String username = user.getUsername();
		
		JSONArray json;
				
		try {
			json = new JSONArray(request.getParameter("liste"));

			for(int i = 0; i < json.length(); i++) {
				JSONObject jsonObject = json.getJSONObject(i);
						
				if(jsonObject.has("datum")) {//Überprüfen, ob es die Plätze für Buchung sind (datum = freie Plätze; datumBesetzt = besetzte Plätze)
					String strDatum = jsonObject.getString("datum");
					String strZeitraum = jsonObject.getString("zeitraum");
					String strPlatznr = jsonObject.getString("platznr");
					String terminbezeichnung = jsonObject.getString("terminbezeichnung");
						
					String strTerminbezeichnung = new String(terminbezeichnung.getBytes("UTF-8"), "UTF-8");

					resTermin(strDatum, strZeitraum, strPlatznr, username, strTerminbezeichnung);
				}
			}
		} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	private void resTermin(String datum, String zeitraum, String platznr, String username, String terminbezeichnung) throws ServletException {
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("INSERT INTO thidb.platzreservierung (datum, zeitraum, platzid, username, terminbezeichnung) VALUES (?,?,?,?,?)")){
			
			pstmt.setString(1, datum);
			pstmt.setString(2, zeitraum);
			pstmt.setString(3, platznr);
			pstmt.setString(4, username);
			pstmt.setString(5, terminbezeichnung);
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

}
