
package com.tiendapc.pedidos.service;

import com.tiendapc.pedidos.model.Pedido;
import com.tiendapc.pedidos.repository.PedidoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // Crear un nuevo pedido
    public Pedido crearPedido(Pedido pedido) {
        pedido.setId(null);
        pedido.setEstado("PENDIENTE");
        pedido.setFechaPedido(null);

        return pedidoRepository.save(pedido);
    }

    // Listar todos los pedidos
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // Buscar pedido por ID
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    // Buscar pedidos de un usuario
    public List<Pedido> buscarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    // Buscar pedidos por estado
    public List<Pedido> buscarPorEstado(String estado) {
        return pedidoRepository.findByEstado(estado.toUpperCase());
    }

    // Buscar pedidos de un usuario por estado
    public List<Pedido> buscarPorUsuarioYEstado(
            Long usuarioId,
            String estado
    ) {
        return pedidoRepository.findByUsuarioIdAndEstado(
                usuarioId,
                estado.toUpperCase()
        );
    }

    // Actualizar el estado de un pedido
    public Optional<Pedido> actualizarEstado(
            Long id,
            String estado
    ) {

        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException(
                    "El estado no puede estar vacío"
            );
        }

        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setEstado(estado.trim().toUpperCase());
            return pedidoRepository.save(pedido);
        });
    }

    // Eliminar un pedido
    public boolean eliminarPedido(Long id) {

        if (!pedidoRepository.existsById(id)) {
            return false;
        }

        pedidoRepository.deleteById(id);
        return true;
    }
}
