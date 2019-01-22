import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class PilhaTest {

	IPilhaDAO pilhaDAO = mock(IPilhaDAO.class);
	
	
    @Test
    public void testRetiraUltimoLivro(){
        Pilha pilha = new Pilha(pilhaDAO);
    	Livro livro = new Livro("A Estrada dá Tudo que você precisa");
        pilha.push(livro);

        livro = new Livro("O Hobbit");
        pilha.push(livro);

        livro = new Livro("O Alquimista");
        pilha.push(livro);

        String resultadoEsperado = "O Alquimista";

        assertEquals(pilha.pop().getTitulo(), resultadoEsperado);
    }

    @Test(expected=ArrayStoreException.class)
    public void testNaoAdicionaLivroAlemLimite(){
        Pilha pilha = new Pilha(pilhaDAO);

        Livro livro1 = new Livro("A Estrada dá Tudo que você precisa");
        Livro livro2 = new Livro("O Hobbit");
        Livro livro3 = new Livro("O Alquimista");
        Livro livro4 = new Livro("A Espera de um Milagre");
        Livro livro5 = new Livro("O Principe");
        Livro livro6 = new Livro("Os espiões");

        pilha.push(livro1);
        pilha.push(livro2);
        pilha.push(livro3);
        pilha.push(livro4);
        pilha.push(livro5);
        pilha.push(livro6);

    }

    @Test
    public void  testNaoAdicionaLivroForaPadraoNome(){
        Pilha pilha = new Pilha(pilhaDAO);

        Livro livro1 = new Livro("O Hobbit");
        pilha.push(livro1);

        Livro livro2 = new Livro("Bellini e o Labirinto");
        pilha.push(livro2);

        String resultadoEsperado = "O Hobbit";

        assertEquals(pilha.pop().getTitulo(),resultadoEsperado);
    }
}
