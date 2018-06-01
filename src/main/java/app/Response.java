/**
 * 
 */
package app;

/**
 * @author ntoranzo
 *
 */
public class Response {

	private int dia;
	private String clima;

	public Response (int dia, String clima) {
		this.dia = dia;
		this.clima = clima;
	}

	/**
	 * @return the dia
	 */
	public int getDia() {
		return dia;
	}
	/**
	 * @param dia the dia to set
	 */
	public void setDia(int dia) {
		this.dia = dia;
	}
	/**
	 * @return the clima
	 */
	public String getClima() {
		return clima;
	}
	/**
	 * @param clima the clima to set
	 */
	public void setClima(String clima) {
		this.clima = clima;
	}

}
