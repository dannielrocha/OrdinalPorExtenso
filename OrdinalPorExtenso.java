package br.danniel.util;

public class OrdinalPorExtenso {
	private final String SUFIXO = "ésim";
	private int ordinal_int;
	private Genero genero;
	private boolean maiuscula;

	/*
	 * Os valores-padrão são Genero.MASCULINO e maiscula = false.
	*/
	public OrdinalExtenso(int ordinal) {
		verify(ordinal);
		
		this.ordinal_int = ordinal;
		this.genero = Genero.MASCULINO;
		this.maiuscula = false;
	}

	/*
	 * O valor-padrão é Genero.MASCULINO.
	*/
	public OrdinalExtenso(int ordinal, boolean maiuscula) {
		verify(ordinal);
		
		this.ordinal_int = ordinal;
		this.genero = Genero.MASCULINO;
		this.maiuscula = maiuscula;
	}

	/*
	 * O valor-padrão é maiuscula = false.
	*/
	public OrdinalExtenso(int ordinal, Genero genero) {
		verify(ordinal);
		
		this.ordinal_int = ordinal;
		this.genero = genero;
		this.maiuscula = false;
	}
	
	/* @author Danniel Rocha
   * 
	 * @param ordinal: int que representa o número ordinal a ser convertido para extenso.
	 * @param genero: enum Genero.FEMININO ou Genero.MASCULINO. Gênero que será utilizado no texto por extenso.
	 * @param maiuscula: boolean que determina se o retorno será em letras todas maiúsculas (true) ou todas minúsculas (false).
	*/	
	public OrdinalExtenso(int ordinal, Genero genero, boolean maiuscula) {
		verify(ordinal);
		
		this.ordinal_int = ordinal;
		this.genero = genero;
		this.maiuscula = maiuscula;
	}

	private void verify(int ordinal) {
		if (ordinal > 999)
			throw new IllegalArgumentException("Não há implementação para números maiores que 999.");
		if (ordinal < 1)
			throw new IllegalArgumentException("Não há implementação para números negativos.");
	}
	
	public String toString() {
		char char_genero = (Character) (genero != null? (genero == Genero.MASCULINO? 'o' : 'a') : genero);
		String retorno = "";
    int posicao = -1;
		String[] ordinal;
		
		if (ordinal_int < 1000 && ordinal_int > 99) {
	        ordinal = new String[]{"", "cent", "ducent", "trecent", "quadringent", "quingent", "sexcent",
	            "septingent", "octingent", "noningent"};

	        posicao = (int) Math.floor(ordinal_int/100);
	        retorno = ordinal[posicao] + SUFIXO + char_genero;
	        
	        ordinal_int -= (posicao * 100);
	    }
		
		if (ordinal_int < 100 && ordinal_int > 9) {
			ordinal = new String[] {"", "décim", "vig", "trig", "quadrag", "quinquag", "sexag", "septuag",
			            "octog", "nonag"};
			posicao = (int) Math.floor(ordinal_int/10);
			
			retorno += " " + ordinal[posicao] + (posicao > 1 ? SUFIXO + char_genero : char_genero);
			
			ordinal_int -= (posicao * 10);
		}
		
		if (ordinal_int < 10 && ordinal_int > 0) {
			ordinal = new String[] {"", "primeir", "segund", "terceir", "quart", "quint", "sext", "sétim", "oitav", "non"};
			retorno += " " + ordinal[ordinal_int] + char_genero;
		}
		
		if (maiuscula)
			return retorno.toUpperCase();
		else
			return retorno.toLowerCase();
	}
	
	public static void main(String[] args) {
		OrdinalExtenso ordinal = new OrdinalExtenso(414);
		System.out.println(ordinal.toString());
    
    
		OrdinalExtenso ordinal = new OrdinalExtenso(414, true);
		System.out.println(ordinal.toString());
    
    OrdinalExtenso ordinal = new OrdinalExtenso(414, Genero.FEMININO);
		System.out.println(ordinal.toString());
    
    OrdinalExtenso ordinal = new OrdinalExtenso(414, Genero.FEMININO, true);
		System.out.println(ordinal.toString());
	} 

	public enum Genero {
		MASCULINO, FEMININO;
	}
}
