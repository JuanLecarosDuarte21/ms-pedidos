
package com.tiendapc.pedidos.repository;

import com.tiendapc.pedidos.model.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Buscar todos los pedidos de un usuario
    List<Pedido> findByUsuarioId(Long usuarioId);

    // Buscar pedidos por estado
    List<Pedido> findByEstado(String estado);

    // Buscar pedidos de un usuario por estado
    List<Pedido> findByUsuarioIdAndEstado(
            Long usuarioId,
            String estado
    );
}
