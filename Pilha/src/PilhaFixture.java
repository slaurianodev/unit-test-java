import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PilhaFixture {

	private static Pilha pilha;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pilha = new Pilha(new FakePilhaDAO());
		System.out.println("Executou o @BeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		pilha = null;
		System.out.println("Executou o @AfterClass");
	}

	@Before
	public void setUp() throws Exception {
        Livro livro1 = new Livro("A Estrada dá Tudo que você precisa");
        Livro livro2 = new Livro("O Hobbit");
        Livro livro3 = new Livro("O Alquimista");
        Livro livro4 = new Livro("A Espera de um Milagre");
        Livro livro5 = new Livro("O Principe");
        
        pilha.push(livro1);
        pilha.push(livro2);
        pilha.push(livro3);
        pilha.push(livro4);
        pilha.push(livro5);
        System.out.println("Executou @Before");
	}

	@After
	public void tearDown() throws Exception {
		int tamanho = pilha.count();
	 	while (tamanho > 0) {
	 		pilha.pop();
	 		tamanho--;
	 	}
	 	System.out.println("Executou @After");
	}

	@Test
	public void testNaoAdicionaAlemLimite() {
		try {
			pilha.push(new Livro("Os espiões"));
			fail();	
		}catch(ArrayStoreException e){
			assertTrue(true);
		}

		System.out.println("Executou testNaoAdicionaAlemLimite");
	}
	
	@Test(expected = ArrayStoreException.class)
	public void testNaoAdicionaAlemLimiteException() {
		pilha.push(new Livro("Os espiões"));

		System.out.println("Executou testNaoAdicionaAlemLimite");
	}
	
	@Test
	public void testNaoAdicionaLivroForaPadraoNome() {
		pilha.pop();
		pilha.push(new Livro("Bellini e o Labirinto"));
		Livro livro = pilha.pop();
		
		assertEquals("A Espera de um Milagre", livro.getTitulo());
		System.out.println("Executou testNaoAdicionaLivroForaPadraoNome");
	}

}
