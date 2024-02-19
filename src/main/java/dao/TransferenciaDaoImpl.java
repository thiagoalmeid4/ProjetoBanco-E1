package dao;

import java.util.List;

import models.Transferencia;

public class TransferenciaDaoImpl implements TransferenciaDao {

	private List<Transferencia> transferenciaFonte;

	public TransferenciaDaoImpl(List<Transferencia> transferenciaFonte) {
		this.transferenciaFonte = transferenciaFonte;
	}

	@Override
	public void salvar(Transferencia transferencia) {
		transferencia.setIdTransferencia(transferenciaFonte.size() + 1);
		transferenciaFonte.add(transferencia);
	}

	@Override
	public List<Transferencia> listarTodos() {
		return transferenciaFonte;
	}

	@Override
	public Transferencia retornarPorID(long idTransferencia) {
		for (Transferencia transferencia : transferenciaFonte) {
			if (idTransferencia == transferencia.getIdTransferencia()) {
				return transferencia;
			}
		}
		return null;
	}

}
