
package com.tiendapc.pedidos.controller;

import com.tiendapc.pedidos.model.Pedido;
import com.tiendapc.pedidos.service.PedidoService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // Crear un pedido
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(
            @Valid @RequestBody Pedido pedido) {

        Pedido guardado = pedidoService.crearPedido(pedido);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(guardado);
    }

    // Listar todos los pedidos
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    // Buscar pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(
            @PathVariable Long id) {

        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar pedidos de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public List<Pedido> buscarPorUsuario(
            @PathVariable Long usuarioId) {

        return pedidoService.buscarPorUsuario(usuarioId);
    }

    // Buscar pedidos por estado
    @GetMapping("/estado/{estado}")
    public List<Pedido> buscarPorEstado(
            @PathVariable String estado) {

        return pedidoService.buscarPorEstado(estado);
    }

    // Buscar pedidos de un usuario por estado
    @GetMapping("/usuario/{usuarioId}/estado/{estado}")
    public List<Pedido> buscarPorUsuarioYEstado(
            @PathVariable Long usuarioId,
            @PathVariable String estado) {

        return pedidoService.buscarPorUsuarioYEstado(
                usuarioId, estado
        );
    }

    // Actualizar estado de un pedido
    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(
            @PathVariable Long id,
            @RequestBody ActualizarEstadoRequest datos) {

        if (datos.estado() == null ||
                datos.estado().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        return pedidoService
                .actualizarEstado(id, datos.estado())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(
            @PathVariable Long id) {

        boolean eliminado = pedidoService.eliminarPedido(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Datos para actualizar el estado
    public record ActualizarEstadoRequest(
            String estado
    ) {
    }
}
