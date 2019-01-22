import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

import static org.mockito.Mockito.*;

public class VendasTest {

	@Test
	public void testCheckoutClienteComLimiteCredito() {
		Cliente cliente = new Cliente("123","Joao");
		ICredito creditoService = mock(ICredito.class);
		when(creditoService.getLimite(cliente.getCpf())).thenReturn(2500d);
		Venda venda = new Venda(2000,cliente,creditoService);
		venda.setPagamentoAPrazo();
		
		boolean result = venda.checkout();
		
		assertTrue(result);
	}
	
	@Test
	public void testCheckoutClienteSemLimiteCredito() {
		Cliente cliente = new Cliente("123","Joao");
		ICredito creditoService = mock(ICredito.class);
		when(creditoService.getLimite(cliente.getCpf())).thenReturn(1500d);
		Venda venda = new Venda(2000,cliente,creditoService);
		venda.setPagamentoAPrazo();
		
		boolean result = venda.checkout();
		
		assertFalse(result);
	}
	
	@Test
	public void testCheckoutVendaAvistaNaoVerificaLimiteCredito() {
		Cliente cliente = new Cliente("123","Joao");
		ICredito creditoService = mock(ICredito.class);
		when(creditoService.getLimite(cliente.getCpf())).thenReturn(1500d);
		Venda venda = new Venda(2000,cliente,creditoService);
		
		verify(creditoService, never()).getLimite(cliente.getCpf());
	}
	
	@Test
	public void testCheckoutNaoEfetivaVendaSeCreditoServiceFalhar() {
		Cliente cliente = new Cliente("123","Joao");
		ICredito creditoService = mock(ICredito.class);
		when(creditoService.getLimite(anyString())).thenThrow(new RuntimeException());
		Venda venda = new Venda(2000,cliente,creditoService);
		
		boolean resultado = venda.checkout();
		
		assertFalse(resultado);
	}

}
