/**
 * 
 */
package app;

/**
 * @author ntoranzo
 *
 */
public class ResponseResumen {

	private String tipoPeriodo;
	private int contador;

	public ResponseResumen(String tipoPeriodo, int contador) {
		this.tipoPeriodo = tipoPeriodo;
		this.contador = contador;
	}

	/**
	 * @return the tipoPeriodo
	 */
	public String getTipoPeriodo() {
		return tipoPeriodo;
	}
	/**
	 * @param tipoPeriodo the tipoPeriodo to set
	 */
	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}
	/**
	 * @return the contador
	 */
	public int getContador() {
		return contador;
	}
	/**
	 * @param contador the contador to set
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}

}
