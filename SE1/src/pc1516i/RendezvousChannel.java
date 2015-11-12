package pc1516i;

public class RendezvousChannel<S,R> {
	
	////
	//
	// S service - descreve o pedido de servi�o
	// Se o servi�o for executado com sucesso por uma thread servidora o m�todo 
	// devolve a resposta ao pedido de servi�o (R).
	// Se o pedido n�o for aceite de imediato (sem thread servidora dispon�vel)
	// a thread cliente fica bloqueada at� que o pedido seja aceite, a thread seja interrompida
	// ou expire o limite de tempo especificado pelo par�metro timeout.
	
	R request(S service, long timeout) {
		return null ;
	}
	
	Token<S> accept(long timeout) {
		return null;
	}
}

interface Token<T> {T value();}
