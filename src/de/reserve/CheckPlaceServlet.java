package de.reserve;

//Helene Akulow

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import javabeans.ReservationBean;

/**
 * Servlet implementation class CheckPlaceServlet
 */
@WebServlet("/checkplaceservlet")
public class CheckPlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "jdbc/MyTHIPool")
	private DataSource ds;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String datum = request.getParameter("datum");
		String zeitraum = request.getParameter("zeitraum");
		String platznr = request.getParameter("platznr");
		String test = request.getParameter("terminbezeichnung");
		String bis = request.getParameter("bis");
		
		String terminbezeichnung = new String(test.getBytes("UTF-8"), "UTF-8");

		Object plaetze[] = checkPlace(datum, zeitraum, platznr, terminbezeichnung, bis);

		@SuppressWarnings("unchecked")
		List<ReservationBean> freiePlaetze = (List<ReservationBean>) plaetze[0];
		@SuppressWarnings("unchecked")
		List<ReservationBean> besetztePlaetze = (List<ReservationBean>) plaetze[1];

		request.setAttribute("freiePlaetze", freiePlaetze);
		request.setAttribute("besetztePlaetze", besetztePlaetze);
		
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("home/jsp/JSON/bookingWiederholtermine.jsp"); // JSON-Objekt
		dispatcher.forward(request, response);
	}

	private Object[] checkPlace(String datum, String zeitraum, String platznr, String terminbezeichnung, String bis)
			throws ServletException {

		List<ReservationBean> freiePlaetze = new ArrayList<ReservationBean>();
		List<ReservationBean> besetztePlaetze = new ArrayList<ReservationBean>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar firstDate = Calendar.getInstance();
		try {
			firstDate.setTime(sdf.parse(datum));

		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		Calendar secondDate = Calendar.getInstance();
		try {
			secondDate.setTime(sdf.parse(bis));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (Calendar i = firstDate; secondDate.after(i) || secondDate.equals(i); i.add(Calendar.DATE, 7)) {
			// DB-Zugriff
			try (Connection con = ds.getConnection();
					PreparedStatement pstmt = con.prepareStatement("SELECT * FROM thidb.platzreservierung"
							+ " WHERE datum = ? AND zeitraum = ? AND PlatzID = ?")) {

				java.util.Date utilDate = i.getTime();
				String dateString = sdf.format(utilDate);

				pstmt.setString(1, dateString);
				pstmt.setString(2, zeitraum);
				pstmt.setString(3, platznr);
				try (ResultSet rs = pstmt.executeQuery()) {

					if (rs == null || !(rs.next())) {
						ReservationBean rb = new ReservationBean();
						rb.setDatumString(dateString);
						rb.setZeitraum(zeitraum);
						rb.setPlatzID(Integer.parseInt(platznr));
						rb.setTerminbezeichnung(terminbezeichnung);
						freiePlaetze.add(rb);

					} else {
						ReservationBean rb = new ReservationBean();
						rb.setDatumString(dateString);
						rb.setZeitraum(zeitraum);
						rb.setPlatzID(Integer.parseInt(platznr));
						rb.setTerminbezeichnung(terminbezeichnung);
						besetztePlaetze.add(rb);
					}
				}
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
		}

		return new Object[] { freiePlaetze, besetztePlaetze };
	}
}
