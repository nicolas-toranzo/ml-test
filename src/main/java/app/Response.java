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
	private EstadoRespuesta status;
	private String errorMessage;

	/**
	 * Constructor para respuestas OK.
	 * 
	 * @param dia
	 * @param clima
	 */
	public Response (int dia, String clima) {
		this.status = EstadoRespuesta.OK;
		this.dia = dia;
		this.clima = clima;
	}

	/**
	 * Constructor para respuestas con error.
	 * 
	 * @param status
	 * @param errorMessage
	 */
	public Response (EstadoRespuesta status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
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


	/**
	 * @return the status
	 */
	public EstadoRespuesta getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EstadoRespuesta status) {
		this.status = status;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
