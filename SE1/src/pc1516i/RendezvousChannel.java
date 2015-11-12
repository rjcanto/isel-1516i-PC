package pc1516i;

public class RendezvousChannel<S,R> {
	
	////
	//
	// S service - descreve o pedido de serviço
	// Se o serviço for executado com sucesso por uma thread servidora o método 
	// devolve a resposta ao pedido de serviço (R).
	// Se o pedido não for aceite de imediato (sem thread servidora disponível)
	// a thread cliente fica bloqueada até que o pedido seja aceite, a thread seja interrompida
	// ou expire o limite de tempo especificado pelo parâmetro timeout.
	
	R request(S service, long timeout) {
		return null ;
	}
	
	Token<S> accept(long timeout) {
		return null;
	}
}

interface Token<T> {T value();}
