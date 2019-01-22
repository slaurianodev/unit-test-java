import static org.junit.Assert.*;

import org.junit.Test;

public class LivroTest {

	@Test
	public void testGetTitulo() {
		Livro livro = new Livro("A Lua");
		String resultadoEsperado = "A Lua";
		
		String resultado = livro.getTitulo();
		
		assertEquals(resultadoEsperado,resultado);
	}

}
