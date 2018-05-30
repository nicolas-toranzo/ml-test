package context;

public enum TipoPeriodo {
	NINGUNO_EN_ESPECIAL(0), SEQUIA(1), LLUVIA(2), COPYT(3);

	private final int index;

	private TipoPeriodo(int index) {
		this.index = index;
	}
	
	/**
	 * Devuelve el índice para el elemento dado.
	 * 
	 * @return
	 */
	public int getIndex() {
		return this.index;
	}

	public static TipoPeriodo getByIndex(int index) {
		for (TipoPeriodo tp : TipoPeriodo.values()) {
			if (index == tp.getIndex()) {
				return tp;
			}
		}
		return null;
	}

}
